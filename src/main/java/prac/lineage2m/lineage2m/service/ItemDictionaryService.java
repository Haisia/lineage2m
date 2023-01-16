package prac.lineage2m.lineage2m.service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;
import prac.lineage2m.lineage2m.dto.*;

import java.util.List;

public interface ItemDictionaryService {
  ItemDictionaryPageableDto getItemListByCond(ItemDictionaryCond itemDictionaryCond, Pageable pageable);
}
