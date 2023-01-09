package prac.lineage2m.lineage2m.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import prac.lineage2m.lineage2m.dto.ItemInfoIncludeAttributeItemOptionsDto;
import prac.lineage2m.lineage2m.entity.*;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static prac.lineage2m.lineage2m.entity.QAttribute.*;
import static prac.lineage2m.lineage2m.entity.QEnchantLevel.enchantLevel1;
import static prac.lineage2m.lineage2m.entity.QItemInfo.*;
import static prac.lineage2m.lineage2m.entity.QItemOption.*;

@Repository
@RequiredArgsConstructor
public class ItemInfoRepositoryCustomImpl implements ItemInfoRepositoryCustom {
  private final JPAQueryFactory jpaQueryFactory;
  private final EntityManager em;

  public ItemInfoIncludeAttributeItemOptionsDto findByIdAndEnchantLevel(Long itemId, Long enchantLevel) {
    BooleanBuilder builder = getBooleanBuilderByItemIdAndEnchantLevel(itemId, enchantLevel);

    ItemInfoIncludeAttributeItemOptionsDto result = getItemInfoAndAttribute(builder);

    List<ItemOption> itemOptionList = getOptions(builder);

    result.setOptions(itemOptionList);
    return result;
  }

  public Optional<ItemInfo> findByItemId(Long itemId) {
    String jpql = "select m from ItemInfo m where m.itemId = :itemId";

    List<ItemInfo> list = em.createQuery(jpql, ItemInfo.class)
            .setParameter("itemId", itemId)
            .getResultList();

    return list.size() != 0 ? Optional.ofNullable(list.get(0)) : Optional.empty();
  }

  private static BooleanBuilder getBooleanBuilderByItemIdAndEnchantLevel(Long itemId, Long enchantLevel) {
    BooleanBuilder builder = new BooleanBuilder();

    builder.and(itemInfo.itemId.eq(itemId));
    builder.and(enchantLevel1.enchantLevel.eq(enchantLevel));
    return builder;
  }

  /**
   * itemId, enchantLevel 로 option 찾기
   * @param builder
   * @return
   */
  private List<ItemOption> getOptions(BooleanBuilder builder) {
    List<ItemOption> itemOptionList = jpaQueryFactory.select(itemOption)
            .from(itemOption)
            .join(enchantLevel1).on(itemOption.enchantLevel.pk.eq(enchantLevel1.pk))
            .join(itemInfo).on(enchantLevel1.itemInfo.pk.eq(itemInfo.pk))
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
      ItemInfoIncludeAttributeItemOptionsDto result = jpaQueryFactory.select(Projections.constructor(ItemInfoIncludeAttributeItemOptionsDto.class, itemInfo, attribute, enchantLevel1))
              .from(itemInfo)
              .join(enchantLevel1)
              .on(itemInfo.pk.eq(enchantLevel1.itemInfo.pk))
              .join(attribute)
              .on(itemInfo.pk.eq(attribute.itemInfo.pk))
              .where(builder)
              .fetchFirst();
    if(result == null) result = new ItemInfoIncludeAttributeItemOptionsDto(0L);
    return result;
  }

}
