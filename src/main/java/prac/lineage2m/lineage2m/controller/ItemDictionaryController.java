package prac.lineage2m.lineage2m.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prac.lineage2m.lineage2m.dto.ItemDictionaryCond;
import prac.lineage2m.lineage2m.dto.ItemDictionaryDto;
import prac.lineage2m.lineage2m.service.ItemDictionaryService;

import java.util.List;

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
  public List<ItemDictionaryDto> getItemListByCond(@ModelAttribute ItemDictionaryCond itemDictionaryCond){
    if (itemDictionaryCond.getEnchantLevel() == null) itemDictionaryCond.setEnchantLevel(0L);
    return itemDictionaryService.getItemListByCond(itemDictionaryCond);
  }

}
