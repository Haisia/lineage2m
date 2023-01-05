package prac.lineage2m.lineage2m.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItemInfoRepositoryCustomImplTest {
  private final ItemInfoRepository itemInfoRepository;

  @Test
  public void test() throws Exception{
    //given
    itemInfoRepository.findByIdAndEnchantLevel(100630002L, 1L);
    //when

    //then

  }


  @Autowired
  public ItemInfoRepositoryCustomImplTest(ItemInfoRepository itemInfoRepository) {
    this.itemInfoRepository = itemInfoRepository;
  }
}