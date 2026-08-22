/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.controller;
import cn.zhuatech.srm.common.ApiResponse; import cn.zhuatech.srm.dto.SrmDto.*; import cn.zhuatech.srm.service.SrmService; import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/admin") @PreAuthorize("hasAnyRole('ADMIN','BUYER','AUDITOR')") public class AdminController {
    private final SrmService service;public AdminController(SrmService service){this.service=service;}
    @GetMapping("/dashboard") public ApiResponse<Dashboard> dashboard(){return ApiResponse.ok(service.adminDashboard());}
    @GetMapping("/suppliers") public ApiResponse<List<SupplierView>> suppliers(){return ApiResponse.ok(service.suppliers());}
    @GetMapping("/sourcing") public ApiResponse<List<SourcingView>> sourcing(){return ApiResponse.ok(service.sourcing());}
}
