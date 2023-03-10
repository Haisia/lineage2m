package prac.lineage2m.lineage2m.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prac.lineage2m.lineage2m.dto.ItemInfoIncludeAttributeItemOptionsDto;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoParamDto;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoParamForRepositoryDto;
import prac.lineage2m.lineage2m.entity.Attribute;
import prac.lineage2m.lineage2m.entity.EnchantLevel;
import prac.lineage2m.lineage2m.entity.ItemInfo;
import prac.lineage2m.lineage2m.entity.ItemOption;
import prac.lineage2m.lineage2m.repository.AttributeRepository;
import prac.lineage2m.lineage2m.repository.EnchantLevelRepository;
import prac.lineage2m.lineage2m.repository.ItemInfoRepository;
import prac.lineage2m.lineage2m.repository.ItemOptionRepository;
import prac.lineage2m.lineage2m.repository.apikey.ApiKeyRepository;
import prac.lineage2m.lineage2m.repository.ncapi.NCApiRepository;
import prac.lineage2m.lineage2m.util.GlobalUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * TODO:
 * 1. 파라미터 조건으로 DB 에서 아이템검색
 * 2. 검색결과가 존재하면 -> 그거 반환
 * 3. 존재하지 않으면 -> NC API 콜해서 DB에 Insert 하고 그거 반환
 * 4. db에 등록하기 전에 NC API 로 부터 받은 결과가 item_id 가 0이 아닌지 확인해야 됨.
 * 0이면 검색결과가 진짜 없는거임
 */
@Service
@RequiredArgsConstructor
public class ItemInfoSearchServiceImpl implements ItemInfoSearchService {
  private final ApiKeyRepository apiKeyRepository;
  private final NCApiRepository ncApiRepository;
  private final ItemInfoRepository itemInfoRepository;
  private final AttributeRepository attributeRepository;
  private final ItemOptionRepository itemOptionRepository;
  private final EnchantLevelRepository enchantLevelRepository;
  private final String apiKey;
  private static String baseUrl = "https://dev-api.plaync.com/l2m/v1.0/market/items/";

  public ItemInfoIncludeAttributeItemOptionsDto getItemInfoToObject(InfoParamDto infoParamDto) {
    ItemInfoIncludeAttributeItemOptionsDto findAtDb = itemInfoRepository.findByIdAndEnchantLevel(infoParamDto.getItem_id(), infoParamDto.getEnchant_level());
    if (findAtDb.getItemId() != 0L) return findAtDb;

    String newBaseUrl = baseUrl + infoParamDto.getItem_id() + "/?";
    InfoParamForRepositoryDto infoParamForRepositoryDto = new InfoParamForRepositoryDto(infoParamDto.getEnchant_level());

    Map<String, String> options = new HashMap<>() {
      {
        put("baseUrl", newBaseUrl);
        put("Authorization", apiKey);
      }
    };

    ItemInfoIncludeAttributeItemOptionsDto itemInfoDto = ncApiRepository.getItemInfoToObject(infoParamForRepositoryDto, options);

    insertItemInfoAttributeAndOptions(itemInfoDto);

    return itemInfoDto;
  }

  public void insertItemInfoAttributeAndOptions(ItemInfoIncludeAttributeItemOptionsDto itemInfoDto) {
    if (itemInfoDto.getItemId() == 0) return;

    Optional<ItemInfo> itemInfoOptional = itemInfoRepository.findByItemId(itemInfoDto.getItemId());

    Attribute attribute = itemInfoDto.getAttribute();
    List<ItemOption> options = itemInfoDto.getOptions();
    ItemInfo itemInfo = GlobalUtil.convertObjectBySameField(itemInfoDto, new ItemInfo());
    itemInfo.setAttribute(null);

    if(itemInfoOptional.isEmpty()) {
      itemInfo = itemInfoRepository.save(itemInfo);
    }else {
      itemInfo = itemInfoOptional.get();
    }


    attribute.setItemInfo(itemInfo);
    for (ItemOption option : options) {
      Long elCond = itemInfoDto.getEnchantLevel();
      Long iipkCond = itemInfo.getPk();
      Optional<EnchantLevel> findEnchantLevel = enchantLevelRepository.findByEnchantLevelAndItemInfoPk(elCond, iipkCond);
      if (elCond!=null && iipkCond!=null && findEnchantLevel.isEmpty()){
        EnchantLevel enchantLevel = EnchantLevel.builder()
                .enchantLevel(itemInfoDto.getEnchantLevel())
                .itemInfo(itemInfo).build();
        option.setEnchantLevel(enchantLevelRepository.save(enchantLevel));
      }else {
        option.setEnchantLevel(findEnchantLevel.get());
      }
    }
    if (attributeRepository.findByItemInfoPk(attribute.getItemInfo().getPk()).isEmpty()) attributeRepository.save(attribute);
    itemOptionRepository.saveAllAndFlush(options);
  }

}
