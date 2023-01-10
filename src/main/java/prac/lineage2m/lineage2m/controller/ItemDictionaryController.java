package prac.lineage2m.lineage2m.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import prac.lineage2m.lineage2m.dto.ItemDictionaryCond;
import prac.lineage2m.lineage2m.dto.ItemDictionaryDto;
import prac.lineage2m.lineage2m.dto.PageRequest;
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
  public List<ItemDictionaryDto> getItemListByCond(@ModelAttribute ItemDictionaryCond itemDictionaryCond, PageRequest pageRequest){
    if (itemDictionaryCond.getEnchantLevel() == null) itemDictionaryCond.setEnchantLevel(0L);
//    if (paginationCond.getPage() == null) paginationCond.setPage(1L);
//    if (paginationCond.getSize() == null) paginationCond.setSize(15L);
    Pageable pageable = pageRequest.of();
    System.out.println("pageRequest = " + pageRequest.toString());

    return itemDictionaryService.getItemListByCond(itemDictionaryCond, pageable);
  }

}
