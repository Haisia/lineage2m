package prac.lineage2m.lineage2m.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import prac.lineage2m.lineage2m.dto.*;
import prac.lineage2m.lineage2m.repository.ItemDictionaryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemDictionaryServiceImpl implements ItemDictionaryService{
  private final ItemDictionaryRepository itemDictionaryRepository;

  public ItemDictionaryPageableDto getItemListByCond(ItemDictionaryCond itemDictionaryCond, Pageable pageable){
    return itemDictionaryRepository.getItemListByCond(itemDictionaryCond, pageable);
  }

}
