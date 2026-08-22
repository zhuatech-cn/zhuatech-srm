/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.service;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierContinuityService {
    public Result assess(Request request) {
        int riskScore = 0;
        if (request.inventoryCoverageDays() < request.leadTimeDays()) riskScore += 30;
        if (request.alternativeSupplierCount() == 0) riskScore += 25;
        else if (request.alternativeSupplierCount() == 1) riskScore += 12;
        riskScore += Math.min(20, request.singleSourcePercent() / 5);
        riskScore += Math.min(15, request.recoveryTimeDays() / 3);
        riskScore += request.financialRiskScore() / 10;
        riskScore = Math.min(100, riskScore);

        String riskLevel = riskScore >= 65 ? "CRITICAL" : riskScore >= 35 ? "WATCH" : "RESILIENT";
        int recommendedBufferDays = Math.max(0, request.leadTimeDays() - request.inventoryCoverageDays())
            + (riskLevel.equals("CRITICAL") ? 7 : riskLevel.equals("WATCH") ? 3 : 0);
        List<String> actions = new ArrayList<>();
        if (request.alternativeSupplierCount() == 0) actions.add("启动备选供应商寻源与样品认证");
        if (request.singleSourcePercent() >= 70) actions.add("制定份额分散目标并设置单一来源上限");
        if (request.inventoryCoverageDays() < request.leadTimeDays()) actions.add("补足关键物料安全库存覆盖");
        if (request.recoveryTimeDays() > 30) actions.add("与供应商联合演练业务连续性恢复计划");
        if (actions.isEmpty()) actions.add("保持季度连续性审查和替代来源有效性验证");
        return new Result(request.supplierCode(), riskScore, riskLevel, recommendedBufferDays, actions);
    }

    public record Request(@NotBlank String supplierCode,
                          @Min(0) int leadTimeDays, @Min(0) int inventoryCoverageDays,
                          @Min(0) int alternativeSupplierCount,
                          @Min(0) @Max(100) int singleSourcePercent,
                          @Min(0) int recoveryTimeDays,
                          @Min(0) @Max(100) int financialRiskScore) {}
    public record Result(String supplierCode, int riskScore, String riskLevel,
                         int recommendedBufferDays, List<String> actions) {}
}
