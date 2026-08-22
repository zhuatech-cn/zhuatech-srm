/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.controller;

import cn.zhuatech.srm.common.ApiResponse;
import cn.zhuatech.srm.service.SupplierContinuityService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/srm/insights")
public class SupplierContinuityController {
    private final SupplierContinuityService service;
    public SupplierContinuityController(SupplierContinuityService service) { this.service = service; }

    @PostMapping("/continuity")
    public ApiResponse<SupplierContinuityService.Result> assess(@Valid @RequestBody SupplierContinuityService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
