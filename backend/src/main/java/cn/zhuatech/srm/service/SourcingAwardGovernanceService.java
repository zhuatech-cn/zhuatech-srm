/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.service;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 寻源定标前校验竞争充分性、评审完整性、供应商合规、预算和集中度风险。
 *
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class SourcingAwardGovernanceService {
    /** 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
    public Assessment assess(Request request) {
        List<String> blockers = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        if (request.compliantBidCount() < 2 && !request.singleSourceApproved()) blockers.add("有效报价少于2家且未批准单一来源");
        if (!request.technicalEvaluationComplete() || !request.commercialEvaluationComplete()) {
            blockers.add("技术或商务评审尚未完成");
        }
        if (!request.selectedSupplierQualified()) blockers.add("中标供应商准入、资质或财务评审未通过");
        if (request.sanctionsHit()) blockers.add("中标供应商命中制裁或禁止交易名单");
        if (request.conflictOfInterestOpen()) blockers.add("存在未解决的利益冲突申报");
        if (request.awardAmount().compareTo(request.approvedBudget()) > 0 && !request.overBudgetApproved()) {
            blockers.add("定标金额超过批准预算且未获例外批准");
        }
        if (request.spendConcentrationPercent().compareTo(request.concentrationLimitPercent()) > 0
                && !request.contingencyPlanReady()) {
            actions.add("供应商集中度超限，补充备供和连续性方案");
        }
        if (!request.negotiationRecordAttached()) actions.add("归档澄清、谈判和最终报价记录");
        if (!request.evaluationEvidenceAttached()) actions.add("归档评分表、定标报告和审批轨迹");
        Decision decision = !blockers.isEmpty() ? Decision.BLOCKED
                : !actions.isEmpty() ? Decision.REVIEW : Decision.AWARD;
        String route = request.awardAmount().compareTo(new BigDecimal("1000000")) >= 0
                ? "采购负责人→业务负责人→财务→定标委员会"
                : "采购负责人→业务负责人→财务";
        return new Assessment(request.eventNo(), request.selectedSupplierCode(), decision, route,
                List.copyOf(blockers), List.copyOf(actions));
    }

    /** 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
    public record Request(@NotBlank String eventNo, @NotBlank String selectedSupplierCode,
                          @Min(0) int compliantBidCount, boolean singleSourceApproved,
                          boolean technicalEvaluationComplete, boolean commercialEvaluationComplete,
                          boolean selectedSupplierQualified, boolean sanctionsHit,
                          boolean conflictOfInterestOpen,
                          @NotNull @DecimalMin("0.00") BigDecimal awardAmount,
                          @NotNull @DecimalMin("0.00") BigDecimal approvedBudget,
                          boolean overBudgetApproved,
                          @NotNull @DecimalMin("0.00") @DecimalMax("100.00") BigDecimal spendConcentrationPercent,
                          @NotNull @DecimalMin("0.00") @DecimalMax("100.00") BigDecimal concentrationLimitPercent,
                          boolean contingencyPlanReady, boolean negotiationRecordAttached,
                          boolean evaluationEvidenceAttached) {}

    /** 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
    public record Assessment(String eventNo, String selectedSupplierCode, Decision decision,
                             String approvalRoute, List<String> blockers, List<String> actions) {}

    /** 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
    public enum Decision { AWARD, REVIEW, BLOCKED }
}
