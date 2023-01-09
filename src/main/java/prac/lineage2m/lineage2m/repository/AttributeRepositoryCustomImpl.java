package prac.lineage2m.lineage2m.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import prac.lineage2m.lineage2m.entity.Attribute;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AttributeRepositoryCustomImpl implements AttributeRepositoryCustom{
  private final EntityManager em;

  public Optional<Attribute> findByItemInfoPk(Long itemInfoPk){
    String jpql = "select m from Attribute m where m.itemInfo.pk = :itemInfoPk";

    List<Attribute> list = em.createQuery(jpql, Attribute.class)
            .setParameter("itemInfoPk", itemInfoPk)
            .getResultList();

    return list.size() != 0 ? Optional.ofNullable(list.get(0)) : Optional.empty();
  }
}
