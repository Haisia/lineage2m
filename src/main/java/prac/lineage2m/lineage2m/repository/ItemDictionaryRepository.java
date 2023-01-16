package prac.lineage2m.lineage2m.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;
import prac.lineage2m.lineage2m.dto.*;

import java.util.List;

public interface ItemDictionaryRepository {
  ItemDictionaryPageableDto getItemListByCond(ItemDictionaryCond itemDictionaryCond, Pageable pageable);
  }
