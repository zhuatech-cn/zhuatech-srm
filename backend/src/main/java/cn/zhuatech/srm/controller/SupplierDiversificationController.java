/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.srm.controller;

import cn.zhuatech.srm.common.ApiResponse;
import cn.zhuatech.srm.service.SupplierDiversificationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/srm/insights")
public class SupplierDiversificationController {
    private final SupplierDiversificationService service;

    public SupplierDiversificationController(SupplierDiversificationService service) {
        this.service = service;
    }

    @PostMapping("/supplier-diversification")
    public ApiResponse<SupplierDiversificationService.Result> assess(
        @Valid @RequestBody SupplierDiversificationService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
