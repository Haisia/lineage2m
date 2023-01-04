package prac.lineage2m.lineage2m.repository.apikey;

import java.util.List;

public interface ApiKeyRepository {
  String findById(Long id);
  List<String> findAll();
}
