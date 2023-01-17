package prac.lineage2m.lineage2m.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import prac.lineage2m.lineage2m.dto.ItemPriceStatsCond;
import prac.lineage2m.lineage2m.dto.ItemPriceStatsResultDto;
import prac.lineage2m.lineage2m.entity.ItemPriceStats;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static prac.lineage2m.lineage2m.entity.QItemInfo.*;
import static prac.lineage2m.lineage2m.entity.QItemPriceStats.*;
import static prac.lineage2m.lineage2m.util.GlobalUtil.queryDslCondMaker;

@RequiredArgsConstructor
@Repository
public class ItemPriceStatsRepositoryCustomImpl implements ItemPriceStatsRepositoryCustom{
  private final JPAQueryFactory jpaQueryFactory;


  public Optional<ItemPriceStats> findByCond(ItemPriceStatsCond itemPriceStatsCond){
    BooleanBuilder builder = getBooleanBuilder(itemPriceStatsCond);

    ItemPriceStats result = jpaQueryFactory.select(itemPriceStats)
            .from(itemPriceStats)
            .join(itemInfo).on(itemInfo.pk.eq(itemPriceStats.itemInfo.pk))
            .where(builder)
            .fetchOne();

    return result != null ? Optional.of(result) : Optional.empty();
  }

  public List<ItemPriceStatsResultDto> findListByCond(ItemPriceStatsCond itemPriceStatsCond) {
    BooleanBuilder builder = getBooleanBuilder(itemPriceStatsCond);

    return jpaQueryFactory.select(Projections.constructor(ItemPriceStatsResultDto.class, itemPriceStats))
            .from(itemPriceStats)
            .where(builder)
            .orderBy(itemPriceStats.createDate.desc())
            .limit(10)
            .fetch();
  }

  private static BooleanBuilder getBooleanBuilder(ItemPriceStatsCond itemPriceStatsCond) {
    BooleanBuilder builder = new BooleanBuilder();

    BooleanExpression between =
            itemPriceStats.createDate !=null && itemPriceStatsCond.getCreateDate() !=null ?
            itemPriceStats.createDate.between(itemPriceStatsCond.getCreateDate(), itemPriceStatsCond.getCreateDate()) : null;

    builder.and(queryDslCondMaker(itemInfo.itemId, itemPriceStatsCond.getItemId()));
    builder.and(between);
    builder.and(queryDslCondMaker(itemPriceStats.enchantLevel, itemPriceStatsCond.getEnchantLevel()));
    builder.and(queryDslCondMaker(itemPriceStats.worldId, itemPriceStatsCond.getWorldId()));
    return builder;
  }


}
