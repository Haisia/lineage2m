package prac.lineage2m.lineage2m.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import prac.lineage2m.lineage2m.domain.ApiKey;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class ApiKeyRepositoryImpl implements ApiKeyRepository{
  private final EntityManager em;

  @Override
  public ApiKey findById(Long pk) {
    String jpql = "select m From ApiKey m where m.pk = :pk";
    return em.createQuery(jpql, ApiKey.class)
            .setParameter("pk",pk)
            .getSingleResult();
  }
}
