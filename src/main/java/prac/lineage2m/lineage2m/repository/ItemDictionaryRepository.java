package prac.lineage2m.lineage2m.repository;

import org.springframework.data.domain.Pageable;
import prac.lineage2m.lineage2m.dto.ItemDictionaryCond;
import prac.lineage2m.lineage2m.dto.ItemDictionaryDto;
import prac.lineage2m.lineage2m.dto.PageRequest;

import java.util.List;

public interface ItemDictionaryRepository {
  List<ItemDictionaryDto> getItemListByCond(ItemDictionaryCond itemDictionaryCond, Pageable pageable);
}
