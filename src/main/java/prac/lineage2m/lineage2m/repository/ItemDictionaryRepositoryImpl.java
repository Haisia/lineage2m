package prac.lineage2m.lineage2m.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import prac.lineage2m.lineage2m.dto.*;
import prac.lineage2m.lineage2m.entity.*;

import java.util.ArrayList;
import java.util.List;

import static prac.lineage2m.lineage2m.entity.QEnchantLevel.enchantLevel1;
import static prac.lineage2m.lineage2m.entity.QItemInfo.itemInfo;
import static prac.lineage2m.lineage2m.util.GlobalUtil.*;

@Repository
@RequiredArgsConstructor
public class ItemDictionaryRepositoryImpl implements ItemDictionaryRepository{
  private final JPAQueryFactory jpaQueryFactory;
  public ItemDictionaryPageableDto getItemListByCond(ItemDictionaryCond itemDictionaryCond, Pageable pageable){
    BooleanBuilder cond = booleanBuilderMaker(itemDictionaryCond);
    QueryResults<ItemInfo> infoQueryResults = jpaQueryFactory.select(itemInfo)
            .from(itemInfo)
            .leftJoin(itemInfo.enchantLevelList,enchantLevel1)
            .where(cond)
            .distinct()
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();

    List<ItemInfo> itemInfoList = infoQueryResults.getResults();
    List<ItemDictionaryDto> itemDictionaryDtoList = new ArrayList<>();
    for (ItemInfo info : itemInfoList) {
      if (info.getEnchantLevelList().size()>0) {
        EnchantLevel enchantLevel = info.getEnchantLevelList().get(Math.toIntExact(itemDictionaryCond.getEnchantLevel()));
        itemDictionaryDtoList.add(new ItemDictionaryDto(info, info.getAttribute(), enchantLevel, enchantLevel.getItemOptionList()));
      }else {
        itemDictionaryDtoList.add(new ItemDictionaryDto((info), info.getAttribute()));
      }
    }

    Pagination pagination = new Pagination(infoQueryResults.getTotal(), infoQueryResults.getOffset(), infoQueryResults.getLimit());
    return new ItemDictionaryPageableDto(itemDictionaryDtoList, pagination);
  }

  private static BooleanBuilder booleanBuilderMaker(ItemDictionaryCond itemDictionaryCond) {
    BooleanBuilder builder = new BooleanBuilder();

    if (itemDictionaryCond.getItemName() != null && !itemDictionaryCond.getItemName().equals("")){
      StringExpression concat = Expressions.asString("%").concat(itemDictionaryCond.getItemName()).concat("%");
      builder.and(itemInfo.itemName.like(concat));
    }
    builder.and(queryDslCondMaker(itemInfo.itemId, itemDictionaryCond.getItemId()));
    builder.and(queryDslCondMaker(itemInfo.grade, itemDictionaryCond.getGrade()));
    builder.and(queryDslCondMaker(itemInfo.tradeCategoryName,itemDictionaryCond.getTradeCategoryName()));
    builder.and(queryDslCondMaker(enchantLevel1.enchantLevel, itemDictionaryCond.getEnchantLevel()).or(enchantLevel1.enchantLevel.isNull()));

    return builder;
  }

}
