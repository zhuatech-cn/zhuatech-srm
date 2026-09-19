/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.controller;

import cn.zhuatech.srm.common.ApiResponse;
import cn.zhuatech.srm.service.SupplierScorecardService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/srm/insights")
public class SupplierScorecardController {
    private final SupplierScorecardService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public SupplierScorecardController(SupplierScorecardService service) { this.service = service; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/supplier-scorecard")
    public ApiResponse<SupplierScorecardService.Result> evaluate(
        @Valid @RequestBody SupplierScorecardService.Request request) {
        return ApiResponse.ok(service.evaluate(request));
    }
}
