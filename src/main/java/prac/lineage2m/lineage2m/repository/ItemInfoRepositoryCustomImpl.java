package prac.lineage2m.lineage2m.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import prac.lineage2m.lineage2m.dto.ItemInfoIncludeAttributeItemOptionsDto;
import prac.lineage2m.lineage2m.entity.*;

import java.util.List;

import static prac.lineage2m.lineage2m.entity.QAttribute.*;
import static prac.lineage2m.lineage2m.entity.QItemInfo.*;
import static prac.lineage2m.lineage2m.entity.QItemOption.*;

@Repository
@RequiredArgsConstructor
public class ItemInfoRepositoryCustomImpl implements ItemInfoRepositoryCustom {
  private final JPAQueryFactory jpaQueryFactory;

  public ItemInfoIncludeAttributeItemOptionsDto findByIdAndEnchantLevel(Long itemId, Long enchantLevel) {
    BooleanBuilder builder = getBooleanBuilderByItemIdAndEnchantLevel(itemId, enchantLevel);

    ItemInfoIncludeAttributeItemOptionsDto result = getItemInfoAndAttribute(builder);

    List<ItemOption> itemOptionList = getOptions(builder);

    result.setOptions(itemOptionList);
    return result;
  }

  private static BooleanBuilder getBooleanBuilderByItemIdAndEnchantLevel(Long itemId, Long enchantLevel) {
    BooleanBuilder builder = new BooleanBuilder();

    builder.and(itemInfo.itemId.eq(itemId));
    builder.and(itemInfo.enchantLevel.eq(enchantLevel));
    return builder;
  }

  private List<ItemOption> getOptions(BooleanBuilder builder) {
    List<ItemOption> itemOptionList = jpaQueryFactory.select(itemOption)
            .from(itemOption)
            .join(itemInfo).on(itemOption.itemInfo.pk.eq(itemInfo.pk))
            .join(attribute).on(itemInfo.pk.eq(attribute.itemInfo.pk))
            .where(builder)
            .fetch();
    return itemOptionList;
  }

  /**
   * Attribute 가 포함된 ItemInfo DTO 를 반환한다.
   * 조건에 맞는 결과가 없으면 itemId를 0으로 반환한다.
   * @param builder
   * @return options 가 포함되지 않은 ItemInfoIncludeAttributeItemOptionsDto
   */
  private ItemInfoIncludeAttributeItemOptionsDto getItemInfoAndAttribute(BooleanBuilder builder) {
      ItemInfoIncludeAttributeItemOptionsDto result = jpaQueryFactory.select(Projections.constructor(ItemInfoIncludeAttributeItemOptionsDto.class, itemInfo, attribute))
              .from(itemInfo)
              .join(itemOption)
              .on(itemInfo.pk.eq(itemOption.itemInfo.pk))
              .join(attribute)
              .on(itemInfo.pk.eq(attribute.itemInfo.pk))
              .where(builder)
              .fetchFirst();
    if(result == null) result = new ItemInfoIncludeAttributeItemOptionsDto(0L);
    return result;
  }

}
