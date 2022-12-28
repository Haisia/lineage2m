package prac.lineage2m.lineage2m.repository;

import prac.lineage2m.lineage2m.domain.ApiKey;

public interface ApiKeyRepository {
  ApiKey findById(Long id);
}
