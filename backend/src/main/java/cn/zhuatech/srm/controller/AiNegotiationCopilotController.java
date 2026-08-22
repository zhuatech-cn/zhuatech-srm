/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.controller;
import cn.zhuatech.srm.common.ApiResponse;
import cn.zhuatech.srm.service.AiNegotiationCopilotService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/srm/ai")
public class AiNegotiationCopilotController {
    private final AiNegotiationCopilotService service;
    public AiNegotiationCopilotController(AiNegotiationCopilotService service) { this.service = service; }
    @PostMapping("/negotiation-copilot")
    public ApiResponse<AiNegotiationCopilotService.Result> prepare(@Valid @RequestBody AiNegotiationCopilotService.Request request) {
        return ApiResponse.ok(service.prepare(request));
    }
}
