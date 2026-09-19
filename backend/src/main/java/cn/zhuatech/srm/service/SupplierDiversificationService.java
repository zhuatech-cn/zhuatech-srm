/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class SupplierDiversificationService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Result assess(Request request) {
        double totalShare = request.suppliers().stream().mapToDouble(SupplierExposure::share).sum();
        double concentrationIndex = request.suppliers().stream()
            .mapToDouble(item -> item.share() * item.share()).sum();
        SupplierExposure largest = request.suppliers().stream()
            .max(Comparator.comparingDouble(SupplierExposure::share)).orElseThrow();
        int highRiskSuppliers = (int) request.suppliers().stream()
            .filter(item -> item.countryRiskScore() >= 70 || item.leadTimeDays() >= 60).count();

        String decision = largest.share() >= .60 || concentrationIndex >= .50 ? "DIVERSIFY"
            : concentrationIndex >= .35 || highRiskSuppliers > 0 ? "WATCH" : "BALANCED";
        List<String> actions = new ArrayList<>();
        if (Math.abs(totalShare - 1.0) > .01) actions.add("修正供应份额数据，确保合计为 100%");
        if (largest.share() >= .60) actions.add("将" + largest.supplierName() + "的目标份额降至 45% 以下");
        if (highRiskSuppliers > 0) actions.add("为高风险区域或长交期供应商建立替代来源");
        if (actions.isEmpty()) actions.add("维持双源策略并按季度复核供应集中度");
        return new Result(request.materialCode(), round(concentrationIndex), largest.supplierName(),
            round(largest.share()), highRiskSuppliers, decision, actions);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    private double round(double value) {
        return Math.round(value * 10_000D) / 10_000D;
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(@NotBlank String materialCode, @Min(1) long annualDemand,
                          @NotEmpty List<@Valid SupplierExposure> suppliers) {}

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record SupplierExposure(@NotBlank String supplierName,
                                   @DecimalMin("0") @DecimalMax("1") double share,
                                   @Min(0) int countryRiskScore, @Min(0) int leadTimeDays) {}

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Result(String materialCode, double concentrationIndex, String largestSupplier,
                         double largestShare, int highRiskSuppliers, String decision,
                         List<String> actions) {}
}
