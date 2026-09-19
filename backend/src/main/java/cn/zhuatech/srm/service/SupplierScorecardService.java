/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.service;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class SupplierScorecardService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Result evaluate(Request request) {
        int total = (int) Math.round(request.qualityScore() * 0.30 + request.deliveryScore() * 0.30
            + request.costScore() * 0.15 + request.serviceScore() * 0.15
            + (100 - request.riskExposure()) * 0.10);
        String tier = total >= 85 && request.riskExposure() <= 30 ? "STRATEGIC"
            : total >= 70 ? "PREFERRED" : total >= 55 ? "CONDITIONAL" : "EXIT_REVIEW";
        List<String> actions = new ArrayList<>();
        if (request.qualityScore() < 70) actions.add("发起质量改善计划并跟踪批次不良率");
        if (request.deliveryScore() < 70) actions.add("建立交付纠偏周报和产能预警机制");
        if (request.costScore() < 65) actions.add("开展成本构成复核与年度降本协商");
        if (request.riskExposure() > 60) actions.add("启用备选供应源并下调新增份额");
        if ("STRATEGIC".equals(tier)) actions.add("纳入联合创新和长期产能协同计划");
        if (actions.isEmpty()) actions.add("保持优选供应商资格并执行季度绩效复盘");
        return new Result(request.supplierCode(), total, tier, 100 - request.riskExposure(), actions);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(@NotBlank String supplierCode,
                          @Min(0) @Max(100) int qualityScore,
                          @Min(0) @Max(100) int deliveryScore,
                          @Min(0) @Max(100) int costScore,
                          @Min(0) @Max(100) int serviceScore,
                          @Min(0) @Max(100) int riskExposure) {}

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Result(String supplierCode, int totalScore, String tier,
                         int resilienceScore, List<String> improvementActions) {}
}
