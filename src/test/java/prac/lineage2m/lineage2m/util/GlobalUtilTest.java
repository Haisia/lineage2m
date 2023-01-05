package prac.lineage2m.lineage2m.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import prac.lineage2m.lineage2m.dto.ItemInfoIncludeAttributeItemOptionsDto;
import prac.lineage2m.lineage2m.entity.Attribute;
import prac.lineage2m.lineage2m.entity.ItemInfo;
import prac.lineage2m.lineage2m.entity.ItemOption;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GlobalUtilTest {

  @Test
  public void test() throws Exception{
    //given
    ItemInfoIncludeAttributeItemOptionsDto dto = new ItemInfoIncludeAttributeItemOptionsDto(1L, "테스트", 5L, "testGrade", "testGradeName", "imageURL", "test", new Attribute(), new ArrayList<ItemOption>());
    ItemInfo entity = new ItemInfo();
    ItemInfo itemInfo = GlobalUtil.convertObjectBySameField(dto, entity);
    ItemInfoIncludeAttributeItemOptionsDto itemInfo2 = GlobalUtil.convertObjectBySameField(entity, dto);

    System.out.println("itemInfo = " + itemInfo);
    System.out.println("itemInfo2 = " + itemInfo2);

    //when

    //then

  }

}