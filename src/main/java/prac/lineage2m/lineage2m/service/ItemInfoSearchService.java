package prac.lineage2m.lineage2m.service;

import prac.lineage2m.lineage2m.dto.ItemInfoIncludeAttributeItemOptionsDto;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoParamDto;

public interface ItemInfoSearchService {
  ItemInfoIncludeAttributeItemOptionsDto getItemInfoToObject(InfoParamDto infoParamDto);
}
