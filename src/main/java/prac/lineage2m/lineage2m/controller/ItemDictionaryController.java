package prac.lineage2m.lineage2m.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import prac.lineage2m.lineage2m.dto.*;
import prac.lineage2m.lineage2m.service.ItemDictionaryService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequiredArgsConstructor
@RequestMapping("/itemDictionary")
public class ItemDictionaryController {
  private final ItemDictionaryService itemDictionaryService;

  @GetMapping("/all")
  public ItemDictionaryDto getItemListAll(){

    return null;
  }

  // http://localhost:8080/itemDictionary/search?
  @GetMapping("/search")
  public ItemDictionaryPageableDto getItemListByCond(@ModelAttribute ItemDictionaryCond itemDictionaryCond, PageRequest pageRequest){
    if (itemDictionaryCond.getEnchantLevel() == null) itemDictionaryCond.setEnchantLevel(0L);
    Pageable pageable = pageRequest.of();
    return itemDictionaryService.getItemListByCond(itemDictionaryCond, pageable);
  }

}
