/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.controller;

import cn.zhuatech.srm.common.ApiResponse;
import cn.zhuatech.srm.service.SupplierRiskService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasAnyRole('BUYER','ADMIN')")
public class SupplierRiskController {
    private final SupplierRiskService service;
    public SupplierRiskController(SupplierRiskService service) { this.service = service; }

    @PostMapping("/supplier-risk")
    public ApiResponse<SupplierRiskService.Result> assess(@Valid @RequestBody SupplierRiskService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
