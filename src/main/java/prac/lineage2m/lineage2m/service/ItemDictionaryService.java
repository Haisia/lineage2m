package prac.lineage2m.lineage2m.service;

import org.springframework.data.domain.Pageable;
import prac.lineage2m.lineage2m.dto.ItemDictionaryCond;
import prac.lineage2m.lineage2m.dto.ItemDictionaryDto;
import prac.lineage2m.lineage2m.dto.PageRequest;

import java.util.List;

public interface ItemDictionaryService {
  List<ItemDictionaryDto> getItemListByCond(ItemDictionaryCond itemDictionaryCond, Pageable pageable);
}