/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.controller;

import cn.zhuatech.srm.common.ApiResponse;
import cn.zhuatech.srm.service.SourcingAwardGovernanceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
@RestController
@RequestMapping("/api/enterprise/srm")
public class SourcingAwardGovernanceController {
    private final SourcingAwardGovernanceService service;

    /** 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
    public SourcingAwardGovernanceController(SourcingAwardGovernanceService service) {
        this.service = service;
    }

    /** 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
    @PostMapping("/sourcing-award")
    public ApiResponse<SourcingAwardGovernanceService.Assessment> assess(
            @Valid @RequestBody SourcingAwardGovernanceService.Request request) {
        return ApiResponse.ok("寻源定标评估完成", service.assess(request));
    }
}
