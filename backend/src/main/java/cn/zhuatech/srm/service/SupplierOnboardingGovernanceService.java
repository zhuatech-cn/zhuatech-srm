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
public class SupplierOnboardingGovernanceService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Assessment assess(Request request) {
        List<String> blockers = new ArrayList<>();
        List<String> conditions = new ArrayList<>();
        if (!request.sanctionsScreened()) blockers.add("制裁与黑名单筛查未完成");
        if (!request.beneficialOwnerVerified()) blockers.add("受益所有人未核验");
        if (!request.bankAccountVerified()) blockers.add("收款账户未完成双人复核");
        if (request.criticalCategory() && !request.qualityAgreementSigned()) blockers.add("关键品类缺少质量协议");
        if (request.riskScore() >= 80) blockers.add("供应商风险评分达到禁止准入阈值");
        else if (request.riskScore() >= 60) conditions.add("需季度复评并设置采购额度");
        if (request.criticalCategory() && request.riskScore() >= 40) conditions.add("关键品类需准备第二供应源");
        String decision = !blockers.isEmpty() ? "BLOCKED" : !conditions.isEmpty() ? "CONDITIONAL" : "APPROVED";
        return new Assessment(request.supplierCode(), decision, 100 - request.riskScore(),
                List.copyOf(blockers), List.copyOf(conditions),
                List.of("SANCTIONS", "UBO", "BANK_DUAL_CONTROL", "QUALITY_GATE"));
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(@NotBlank String supplierCode, @Min(0) @Max(100) int riskScore,
                          boolean criticalCategory, boolean sanctionsScreened,
                          boolean beneficialOwnerVerified, boolean bankAccountVerified,
                          boolean qualityAgreementSigned) {
        /**
         * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
         */
        public Request {
            if (supplierCode == null || supplierCode.isBlank()) throw new IllegalArgumentException("supplierCode is required");
            if (riskScore < 0 || riskScore > 100) throw new IllegalArgumentException("riskScore must be 0..100");
        }
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Assessment(String supplierCode, String decision, int trustScore,
                             List<String> blockers, List<String> conditions, List<String> controlsChecked) {}
}
