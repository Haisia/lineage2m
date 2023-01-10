package prac.lineage2m.lineage2m.service;

import prac.lineage2m.lineage2m.dto.ItemDictionaryCond;
import prac.lineage2m.lineage2m.dto.ItemDictionaryDto;

import java.util.List;

public interface ItemDictionaryService {
  List<ItemDictionaryDto> getItemListByCond(ItemDictionaryCond itemDictionaryCond);
}
