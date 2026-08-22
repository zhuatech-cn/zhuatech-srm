/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.srm;
import cn.zhuatech.srm.ai.OpenAiCompatibleGateway;
import cn.zhuatech.srm.service.AiNegotiationCopilotService;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;
class AiNegotiationCopilotServiceTests {
    private final AiNegotiationCopilotService service = new AiNegotiationCopilotService(
        new OpenAiCompatibleGateway("local", "https://api.deepseek.com", "deepseek-chat", ""));
    @Test void usesDefensivePostureForSingleSourceRisk() {
        var result = service.prepare(new AiNegotiationCopilotService.Request("核心器件供应商", new BigDecimal("8000000"),
            true, new BigDecimal("12"), new BigDecimal("82"), new BigDecimal("4"), 20, 0));
        assertThat(result.posture()).isEqualTo("DEFENSIVE");
        assertThat(result.strategy()).hasSizeGreaterThan(3);
    }
}
