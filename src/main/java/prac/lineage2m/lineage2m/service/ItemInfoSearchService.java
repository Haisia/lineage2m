package prac.lineage2m.lineage2m.service;

import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoParamDto;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoResultDto;

public interface ItemInfoSearchService {
  InfoResultDto getItemInfoToObject(InfoParamDto infoParamDto);
}
