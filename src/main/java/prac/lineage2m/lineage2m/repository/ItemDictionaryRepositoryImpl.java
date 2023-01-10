package prac.lineage2m.lineage2m.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.group.Group;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import prac.lineage2m.lineage2m.dto.ItemDictionaryCond;
import prac.lineage2m.lineage2m.dto.ItemDictionaryDto;
import prac.lineage2m.lineage2m.dto.QItemDictionaryDto;
import prac.lineage2m.lineage2m.dto.itemInfoSearch.InfoOptionsDto;
import prac.lineage2m.lineage2m.entity.ItemInfo;
import prac.lineage2m.lineage2m.entity.ItemOption;
import prac.lineage2m.lineage2m.entity.QItemInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.set;
import static com.querydsl.core.types.Projections.list;
import static prac.lineage2m.lineage2m.entity.QAttribute.*;
import static prac.lineage2m.lineage2m.entity.QEnchantLevel.enchantLevel1;
import static prac.lineage2m.lineage2m.entity.QItemInfo.itemInfo;
import static prac.lineage2m.lineage2m.entity.QItemOption.*;
import static prac.lineage2m.lineage2m.util.GlobalUtil.*;

@Repository
@RequiredArgsConstructor
public class ItemDictionaryRepositoryImpl implements ItemDictionaryRepository{
  private final JPAQueryFactory jpaQueryFactory;


  public List<ItemDictionaryDto> getItemListByCond(ItemDictionaryCond itemDictionaryCond){
    BooleanBuilder cond = booleanBuilderMaker(itemDictionaryCond);

    return jpaQueryFactory
            .from(itemInfo)
            .join(attribute).on(attribute.itemInfo.pk.eq(itemInfo.pk))
            .join(enchantLevel1).on(enchantLevel1.itemInfo.pk.eq(itemInfo.pk))
            .join(itemOption).on(itemOption.enchantLevel.pk.eq(enchantLevel1.pk))
            .where(cond)
            .transform(groupBy(enchantLevel1.pk)
                    .list(Projections.constructor(ItemDictionaryDto.class,
                            itemInfo, attribute, enchantLevel1, GroupBy.set(itemOption))));
  }

  private static BooleanBuilder booleanBuilderMaker(ItemDictionaryCond itemDictionaryCond) {
    BooleanBuilder builder = new BooleanBuilder();

    builder.and(itemInfo.itemName.like("%"+itemDictionaryCond.getItemName()+"%"));
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
