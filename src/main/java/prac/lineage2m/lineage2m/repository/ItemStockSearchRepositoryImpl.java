package prac.lineage2m.lineage2m.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import prac.lineage2m.lineage2m.dto.ItemStockSearchRecommendCond;
import prac.lineage2m.lineage2m.dto.ItemStockSearchRecommendDto;

import java.util.List;

import static prac.lineage2m.lineage2m.entity.QItemInfo.itemInfo;

@RequiredArgsConstructor
@Repository
public class ItemStockSearchRepositoryImpl implements ItemStockSearchRepository{
  private final JPAQueryFactory jpaQueryFactory;

  public List<ItemStockSearchRecommendDto> getRecommendKeyword(@ModelAttribute ItemStockSearchRecommendCond itemStockSearchRecommendCond, Pageable pageable) {
    return jpaQueryFactory.select(Projections.constructor(ItemStockSearchRecommendDto.class, itemInfo))
            .from(itemInfo)
            .where(itemInfo.itemName.like("%" + itemStockSearchRecommendCond.getSearch_keyword() + "%"))
            .limit(pageable.getPageSize())
            .fetch();

  }
}
