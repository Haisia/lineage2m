package prac.lineage2m.lineage2m.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prac.lineage2m.lineage2m.service.AdminService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
  private final AdminService adminService;

  @GetMapping("/update/serverlist")
  public String updateServerAndWorldList(){
    if(adminService.saveWorldAndServerList()) return "ok";
    return "no";
  }

  @GetMapping("/update/itemList")
  public String updateItemList(){
    if(adminService.updateItemList()) return "아이템 리스트 업데이트 성공";
    else return "에러";
  }

  @GetMapping("/update/itemListByItemId")
  public String updateItemListByItemId(){
    if(adminService.updateItemListByItemId()) return "아이템 리스트 업데이트 성공";
    else return "에러";
  }

}
