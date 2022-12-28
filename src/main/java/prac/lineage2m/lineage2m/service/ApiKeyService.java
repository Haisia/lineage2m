package prac.lineage2m.lineage2m.service;

import prac.lineage2m.lineage2m.domain.ApiKey;

import java.util.Optional;

public interface ApiKeyService {
  ApiKey findById(Long l);
}
