/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.controller;
import cn.zhuatech.srm.common.ApiResponse; import cn.zhuatech.srm.dto.SrmDto.*; import cn.zhuatech.srm.service.SrmService; import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.web.bind.annotation.*; import java.util.List;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/admin") @PreAuthorize("hasAnyRole('ADMIN','BUYER','AUDITOR')") public class AdminController {
    private final SrmService service;/**
                                      * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                      */
public AdminController(SrmService service){this.service=service;}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @GetMapping("/dashboard") public ApiResponse<Dashboard> dashboard(){return ApiResponse.ok(service.adminDashboard());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @GetMapping("/suppliers") public ApiResponse<List<SupplierView>> suppliers(){return ApiResponse.ok(service.suppliers());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @GetMapping("/sourcing") public ApiResponse<List<SourcingView>> sourcing(){return ApiResponse.ok(service.sourcing());}
}
