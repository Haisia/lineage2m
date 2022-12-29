package prac.lineage2m.lineage2m.repository;

import org.springframework.stereotype.Repository;
import prac.lineage2m.lineage2m.domain.ApiKey;

import java.util.List;

@Repository
public interface ApiKeyRepository {
  String findById(Long id);
  List<String> findAll();
}
