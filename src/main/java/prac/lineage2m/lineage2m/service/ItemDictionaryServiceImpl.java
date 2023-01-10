package prac.lineage2m.lineage2m.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import prac.lineage2m.lineage2m.dto.ItemDictionaryCond;
import prac.lineage2m.lineage2m.dto.ItemDictionaryDto;
import prac.lineage2m.lineage2m.repository.ItemDictionaryRepository;
import prac.lineage2m.lineage2m.repository.ItemInfoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemDictionaryServiceImpl implements ItemDictionaryService{
  private final ItemDictionaryRepository itemDictionaryRepository;

  public List<ItemDictionaryDto> getItemListByCond(ItemDictionaryCond itemDictionaryCond){
    return itemDictionaryRepository.getItemListByCond(itemDictionaryCond);
  }

}
