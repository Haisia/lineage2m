package prac.lineage2m.lineage2m.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import prac.lineage2m.lineage2m.entity.ApiKey;
import prac.lineage2m.lineage2m.entity.EnchantLevel;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EnchantLevelRepositoryCustomImpl implements EnchantLevelRepositoryCustom{
  private final EntityManager em;

  public Optional<EnchantLevel> findByEnchantLevelAndItemInfoPk(Long enchantLevel, Long itemInfoPk) {
    String jpql = "select m From EnchantLevel m where m.enchantLevel = :enchantLevel and m.itemInfo.pk = :itemInfoPk";

    List<EnchantLevel> resultList = em.createQuery(jpql, EnchantLevel.class)
            .setParameter("enchantLevel", enchantLevel)
            .setParameter("itemInfoPk", itemInfoPk)
            .getResultList();

    return resultList.size() !=0 ? Optional.ofNullable(resultList.get(0)) : Optional.empty();
  }
}
