package prac.lineage2m.lineage2m.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import prac.lineage2m.lineage2m.dto.ItemDictionaryCond;
import prac.lineage2m.lineage2m.dto.ItemDictionaryDto;
import prac.lineage2m.lineage2m.dto.PageRequest;
import prac.lineage2m.lineage2m.repository.ItemDictionaryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemDictionaryServiceImpl implements ItemDictionaryService{
  private final ItemDictionaryRepository itemDictionaryRepository;

  public List<ItemDictionaryDto> getItemListByCond(ItemDictionaryCond itemDictionaryCond, Pageable pageable){
    return itemDictionaryRepository.getItemListByCond(itemDictionaryCond, pageable);
  }

}
