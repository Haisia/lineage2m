package prac.lineage2m.lineage2m.repository;

import prac.lineage2m.lineage2m.dto.ItemDictionaryCond;
import prac.lineage2m.lineage2m.dto.ItemDictionaryDto;

import java.util.List;

public interface ItemDictionaryRepository {
  List<ItemDictionaryDto> getItemListByCond(ItemDictionaryCond itemDictionaryCond);
}
