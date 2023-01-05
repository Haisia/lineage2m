package prac.lineage2m.lineage2m.repository;

import prac.lineage2m.lineage2m.dto.ItemInfoIncludeAttributeItemOptionsDto;

public interface ItemInfoRepositoryCustom {
  ItemInfoIncludeAttributeItemOptionsDto findByIdAndEnchantLevel(Long itemId, Long enchantLevel);
}
