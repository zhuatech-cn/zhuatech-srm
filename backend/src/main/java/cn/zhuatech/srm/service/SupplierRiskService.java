/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.service;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierRiskService {
    public Result assess(Request request) {
        int score = Math.min(100, (int) Math.round((100 - request.qualityScore()) * .35
            + (1 - request.onTimeRate()) * 30 + Math.min(20, request.overdueDays())
            + Math.min(10, request.unresolvedIssues() * 3) + (request.singleSource() ? 15 : 0)));
        String level = score >= 75 ? "CRITICAL" : score >= 50 ? "HIGH" : score >= 25 ? "WATCH" : "LOW";
        List<String> actions = new ArrayList<>();
        if (request.qualityScore() < 80) actions.add("发起供应商质量改善计划");
        if (request.onTimeRate() < .85 || request.overdueDays() > 0) actions.add("复核交付恢复计划与承诺日期");
        if (request.singleSource()) actions.add("启动第二来源寻源评估");
        if (actions.isEmpty()) actions.add("保持季度绩效复盘");
        return new Result(request.supplierName(), score, level, score >= 50, actions);
    }

    public record Request(@NotBlank String supplierName, @Min(0) @Max(100) int qualityScore,
                          @DecimalMin("0") @DecimalMax("1") double onTimeRate,
                          @Min(0) int overdueDays, @Min(0) int unresolvedIssues,
                          boolean singleSource) {}
    public record Result(String supplierName, int riskScore, String level,
                         boolean buyerReview, List<String> actions) {}
}
