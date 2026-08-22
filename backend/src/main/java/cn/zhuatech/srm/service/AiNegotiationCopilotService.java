/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.service;

import cn.zhuatech.srm.ai.OpenAiCompatibleGateway;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AiNegotiationCopilotService {
    private final OpenAiCompatibleGateway gateway;
    public AiNegotiationCopilotService(OpenAiCompatibleGateway gateway) { this.gateway = gateway; }

    public Result prepare(Request request) {
        int dependencyRisk = 10;
        int leverage = 50;
        List<String> strategy = new ArrayList<>();
        if (Boolean.TRUE.equals(request.singleSource())) { dependencyRisk += 35; leverage -= 20; strategy.add("把替代供应源开发纳入交换条件和时间表"); }
        if (request.priceIncreaseRate().compareTo(BigDecimal.valueOf(5)) > 0) { dependencyRisk += 15; strategy.add("要求提供原材料、人工和汇率的可验证成本拆解"); }
        if (request.onTimeDeliveryRate().compareTo(BigDecimal.valueOf(90)) < 0) { dependencyRisk += 20; leverage += 10; strategy.add("用交付未达标事实换取价格、库存或账期补偿"); }
        if (request.defectRate().compareTo(BigDecimal.valueOf(2)) > 0) { dependencyRisk += 20; leverage += 10; strategy.add("将质量整改与付款节点、索赔条款联动"); }
        if (request.contractDaysRemaining() <= 45) { dependencyRisk += 10; strategy.add("在合同到期前锁定过渡供货与退出保障"); }
        if (request.alternativeSupplierCount() >= 2) { leverage += 20; strategy.add("使用双源报价形成有依据的目标价格区间"); }
        dependencyRisk = Math.min(100, dependencyRisk);
        leverage = Math.max(0, Math.min(100, leverage));
        if (strategy.isEmpty()) strategy.add("维持合作并以年度降本和联合改善为主要议题");

        String context = "供应商=%s，年采购额=%s，单一来源=%s，涨价=%s%%，准交=%s%%，不良=%s%%，策略=%s"
            .formatted(request.supplierName(), request.annualSpend(), request.singleSource(), request.priceIncreaseRate(),
                request.onTimeDeliveryRate(), request.defectRate(), strategy);
        var enhanced = gateway.complete("你是采购谈判助手，请生成目标、底线、交换条件、提问清单和风险提醒。", context);
        var metadata = gateway.metadata();
        return new Result(dependencyRisk, leverage, dependencyRisk >= 70 ? "DEFENSIVE" : leverage >= 70 ? "COMPETITIVE" : "COLLABORATIVE",
            List.copyOf(strategy), enhanced.orElse("首要策略：" + strategy.getFirst()),
            enhanced.isPresent() ? "EXTERNAL_MODEL" : "LOCAL_RULES", metadata.provider(), metadata.model());
    }

    public record Request(@NotBlank String supplierName, @DecimalMin("0") BigDecimal annualSpend,
                          @NotNull Boolean singleSource, @NotNull BigDecimal priceIncreaseRate,
                          @DecimalMin("0") @DecimalMax("100") BigDecimal onTimeDeliveryRate,
                          @DecimalMin("0") @DecimalMax("100") BigDecimal defectRate,
                          @Min(0) int contractDaysRemaining, @Min(0) int alternativeSupplierCount) {}
    public record Result(int dependencyRisk, int negotiationLeverage, String posture, List<String> strategy,
                         String negotiationBrief, String aiMode, String provider, String model) {}
}
