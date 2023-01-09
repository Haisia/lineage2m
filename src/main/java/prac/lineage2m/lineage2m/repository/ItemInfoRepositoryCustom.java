package prac.lineage2m.lineage2m.repository;

import prac.lineage2m.lineage2m.dto.ItemInfoIncludeAttributeItemOptionsDto;
import prac.lineage2m.lineage2m.entity.ItemInfo;

import java.util.Optional;

public interface ItemInfoRepositoryCustom {
  ItemInfoIncludeAttributeItemOptionsDto findByIdAndEnchantLevel(Long itemId, Long enchantLevel);
  Optional<ItemInfo> findByItemId(Long itemId);
}
