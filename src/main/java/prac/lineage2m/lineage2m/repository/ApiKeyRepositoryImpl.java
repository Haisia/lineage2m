package prac.lineage2m.lineage2m.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import prac.lineage2m.lineage2m.entity.ApiKey;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class ApiKeyRepositoryImpl implements ApiKeyRepository{
  private final EntityManager em;

  @Override
  public String findById(Long pk) {
    String jpql = "select m From ApiKey m where m.pk = :pk";
    return em.createQuery(jpql, ApiKey.class)
            .setParameter("pk",pk)
            .getSingleResult().getKey();
  }

  public List<String> findAll() {
    String jpql = "select m From ApiKey m";
    return em.createQuery(jpql, ApiKey.class)
            .getResultList().stream()
            .map(ApiKey::getKey).collect(Collectors.toList());
  }
}
