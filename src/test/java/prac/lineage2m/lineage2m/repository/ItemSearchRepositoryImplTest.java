package prac.lineage2m.lineage2m.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prac.lineage2m.lineage2m.dto.ItemDto;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class ItemSearchRepositoryImplTest {
  private final ItemSearchRepositoryImpl itemSearchRepository = new ItemSearchRepositoryImpl();



  @Test
  void getItemStocks() throws IOException {
    itemSearchRepository.getItemStocks();
  }

  @Test
  void DTD받기() throws IOException {
    List<ItemDto> itemStocksToItemDto = itemSearchRepository.getItemStocksToItemDto();
    System.out.println(itemStocksToItemDto);
//        System.out.println(itemStocksToItemDto.get(0).toString());
  }

}