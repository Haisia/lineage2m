package prac.lineage2m.lineage2m.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import prac.lineage2m.lineage2m.dto.ItemPriceStatsCond;
import prac.lineage2m.lineage2m.entity.ItemPriceStats;

import java.util.Date;
import java.util.Optional;

import static prac.lineage2m.lineage2m.entity.QItemInfo.*;
import static prac.lineage2m.lineage2m.entity.QItemPriceStats.*;
import static prac.lineage2m.lineage2m.util.GlobalUtil.queryDslCondMaker;

@RequiredArgsConstructor
@Repository
public class ItemPriceStatsRepositoryCustomImpl implements ItemPriceStatsRepositoryCustom{
  private final JPAQueryFactory jpaQueryFactory;


  public Optional<ItemPriceStats> findByCond(ItemPriceStatsCond itemPriceStatsCond){

    BooleanBuilder builder = new BooleanBuilder();
    builder.and(queryDslCondMaker(itemInfo.itemId, itemPriceStatsCond.getItemId()));
    builder.and(itemPriceStats.createDate.between(itemPriceStatsCond.getCreateDate(),itemPriceStatsCond.getCreateDate()));
    builder.and(queryDslCondMaker(itemPriceStats.enchantLevel, itemPriceStatsCond.getEnchantLevel()));
    builder.and(queryDslCondMaker(itemPriceStats.worldId, itemPriceStatsCond.getWorldId()));

    ItemPriceStats result = jpaQueryFactory.select(itemPriceStats)
            .from(itemPriceStats)
            .join(itemInfo).on(itemInfo.pk.eq(itemPriceStats.itemInfo.pk))
            .where(builder)
            .fetchOne();

    return result != null ? Optional.of(result) : Optional.empty();
  }


}
