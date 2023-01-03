package prac.lineage2m.lineage2m.service;

import prac.lineage2m.lineage2m.dto.ServerListSearch.ServerListResultDto;

import java.util.List;
import java.util.Map;

public interface ServerListService {
  List<ServerListResultDto> getServerListToObject();
}
