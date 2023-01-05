package prac.lineage2m.lineage2m.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prac.lineage2m.lineage2m.entity.ItemInfo;
import prac.lineage2m.lineage2m.entity.ItemOption;
import prac.lineage2m.lineage2m.repository.ItemInfoRepository;
import prac.lineage2m.lineage2m.repository.ItemOptionRepository;
import prac.lineage2m.lineage2m.service.AdminService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
  private final AdminService adminService;


  private final ItemInfoRepository itemInfoRepository;
  private final ItemOptionRepository itemOptionRepository;

  @GetMapping("/update/serverlist")
  public String saveServerAndWorldList(){
    if(adminService.saveWorldAndServerList()) return "ok";
    return "no";
  }

  @GetMapping("/test")
  public String test(){
    ItemInfo testItemInfo = ItemInfo.builder()
            .itemId(1L)
            .itemName("테스트 아이템")
            .enchantLevel(0L)
            .grade("epic")
            .gradeName("영웅")
            .image("https://assets.playnccdn.com/gamedata/powerbook/l2m/icon/Icon_128/Item/Icon_WP_Orb_G3_003.png")
            .tradeCategoryName("오브")
            .build();

    List<ItemOption> testOptions = new ArrayList<>();
    testOptions.add(ItemOption.builder()
            .optionName("무기 대미지")
            .display("+19")
            .extraDisplay("")
            .build());

    testOptions.add(ItemOption.builder()
            .optionName("명중")
            .display("+6")
            .extraDisplay("+13")
            .build());

    testOptions.add(ItemOption.builder()
            .optionName("마법 치명타")
            .display("+15%")
            .extraDisplay("")
            .build());

    itemInfoRepository.save(testItemInfo);
    itemOptionRepository.saveAll(testOptions);

    return "yes";
  }
}
