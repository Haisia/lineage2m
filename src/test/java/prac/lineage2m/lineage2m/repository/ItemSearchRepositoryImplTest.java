package prac.lineage2m.lineage2m.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemSearchRepositoryImplTest {
  ItemSearchRepositoryImpl itemSearchRepository = new ItemSearchRepositoryImpl();

  @Test
  void getItemStocks() throws IOException {
    itemSearchRepository.getItemStocks();
  }

}