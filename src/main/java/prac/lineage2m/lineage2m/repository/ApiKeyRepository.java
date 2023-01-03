package prac.lineage2m.lineage2m.repository;

import java.util.List;

public interface ApiKeyRepository {
  String findById(Long id);
  List<String> findAll();
}
