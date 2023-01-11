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
import prac.lineage2m.lineage2m.dto.ItemDictionaryCond;
import prac.lineage2m.lineage2m.dto.ItemDictionaryDto;
import prac.lineage2m.lineage2m.dto.ItemDictionaryPageableDto;
import prac.lineage2m.lineage2m.dto.Pagination;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoOptionsDto;
import prac.lineage2m.lineage2m.entity.ItemOption;
import prac.lineage2m.lineage2m.util.GlobalUtil;

import java.util.ArrayList;
import java.util.List;

import static prac.lineage2m.lineage2m.entity.QAttribute.*;
import static prac.lineage2m.lineage2m.entity.QEnchantLevel.enchantLevel1;
import static prac.lineage2m.lineage2m.entity.QItemInfo.itemInfo;
import static prac.lineage2m.lineage2m.entity.QItemOption.*;
import static prac.lineage2m.lineage2m.util.GlobalUtil.*;

@Repository
@RequiredArgsConstructor
public class ItemDictionaryRepositoryImpl implements ItemDictionaryRepository{
  private final JPAQueryFactory jpaQueryFactory;


  public ItemDictionaryPageableDto getItemListByCond(ItemDictionaryCond itemDictionaryCond, Pageable pageable){
    BooleanBuilder cond = booleanBuilderMaker(itemDictionaryCond);

    QueryResults<ItemDictionaryDto> infoQueryResults = jpaQueryFactory.select(Projections.constructor(ItemDictionaryDto.class, itemInfo, attribute, enchantLevel1))
            .from(itemInfo)
            .join(attribute).on(attribute.itemInfo.pk.eq(itemInfo.pk))
            .join(enchantLevel1).on(enchantLevel1.itemInfo.pk.eq(itemInfo.pk))
            .join(itemOption).on(itemOption.enchantLevel.pk.eq(enchantLevel1.pk))
            .where(cond)
            .distinct()
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();

    List<ItemDictionaryDto> itemInfoList = infoQueryResults.getResults();
    for (ItemDictionaryDto itemInfoDto : itemInfoList) {
      if(itemInfoDto.getEnchantLevel()!=null) {
        BooleanBuilder itemOptionCond = new BooleanBuilder();
        itemOptionCond.and(enchantLevel1.enchantLevel.eq(itemDictionaryCond.getEnchantLevel()));
        itemOptionCond.and(itemInfo.itemId.eq(itemInfoDto.getItemId()));

        List<ItemOption> findOptionList = jpaQueryFactory.select(itemOption)
                .from(itemInfo)
                .join(enchantLevel1).on(enchantLevel1.itemInfo.pk.eq(itemInfo.pk))
                .join(itemOption).on(itemOption.enchantLevel.pk.eq(enchantLevel1.pk))
                .where(itemOptionCond)
                .fetch();

        List<InfoOptionsDto> infoOptionsDto = new ArrayList<>();
        for (ItemOption itemOption : findOptionList) {
          infoOptionsDto.add(GlobalUtil.convertObjectBySameField(itemOption,new InfoOptionsDto()));
        }
        itemInfoDto.setItemOptions(infoOptionsDto);
      }
    }

    Pagination pagination = Pagination.builder()
            .total(infoQueryResults.getTotal())
            .offset(infoQueryResults.getOffset())
            .limit(infoQueryResults.getLimit())
            .build();

    ItemDictionaryPageableDto result = new ItemDictionaryPageableDto(itemInfoList,pagination);
    return result;
  }

  private static BooleanBuilder booleanBuilderMaker(ItemDictionaryCond itemDictionaryCond) {
    BooleanBuilder builder = new BooleanBuilder();

    if (itemDictionaryCond.getItemName() != null && !itemDictionaryCond.getItemName().equals("")){
      StringExpression concat = Expressions.asString("%").concat(itemDictionaryCond.getItemName()).concat("%");
      builder.and(itemInfo.itemName.like(concat));
    }
    builder.and(queryDslCondMaker(itemInfo.grade, itemDictionaryCond.getGrade()));
    builder.and(queryDslCondMaker(itemInfo.tradeCategoryName,itemDictionaryCond.getTradeCategoryName()));
    builder.and(queryDslCondMaker(enchantLevel1.enchantLevel,itemDictionaryCond.getEnchantLevel()));

    if (itemDictionaryCond.getOptionName() != null) {
      List<String> optionNameList = itemDictionaryCond.getOptionName();
      for (String optionName : optionNameList) {
        builder.and(queryDslCondMaker(itemOption.optionName,optionName));
      }
    }

    return builder;
  }

}
