/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.controller;
import cn.zhuatech.srm.common.ApiResponse;
import cn.zhuatech.srm.service.SupplierOnboardingGovernanceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/enterprise/suppliers")
public class SupplierOnboardingGovernanceController {
    private final SupplierOnboardingGovernanceService service;
    public SupplierOnboardingGovernanceController(SupplierOnboardingGovernanceService service) { this.service = service; }
    @PostMapping("/onboarding-gate")
    public ApiResponse<SupplierOnboardingGovernanceService.Assessment> assess(
            @Valid @RequestBody SupplierOnboardingGovernanceService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
