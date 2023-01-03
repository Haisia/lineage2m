package prac.lineage2m.lineage2m.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import prac.lineage2m.lineage2m.service.AdminService;

@RestController
@RequiredArgsConstructor
public class AdminController {
  private final AdminService adminService;

  @GetMapping("/test")
  public String saveServerAndWorldList(){
    if(adminService.saveWorldAndServerList()) return "ok";
    return "no";
  }
}
