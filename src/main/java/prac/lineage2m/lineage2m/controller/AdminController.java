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

  @GetMapping("/update/serverlist")
  public String saveServerAndWorldList(){
    if(adminService.saveWorldAndServerList()) return "ok";
    return "no";
  }
}
