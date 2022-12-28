package prac.lineage2m.lineage2m.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prac.lineage2m.lineage2m.domain.ApiKey;
import prac.lineage2m.lineage2m.repository.ApiKeyRepository;

@Service
@RequiredArgsConstructor
public class ApiKeyServiceImpl implements ApiKeyService{
  private final ApiKeyRepository apiKeyRepository;

  @Override
  public ApiKey findById(Long id){
    return apiKeyRepository.findById(id);
  }

}
