/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.controller;
import cn.zhuatech.srm.common.ApiResponse;
import cn.zhuatech.srm.service.AiNegotiationCopilotService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/srm/ai")
public class AiNegotiationCopilotController {
    private final AiNegotiationCopilotService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public AiNegotiationCopilotController(AiNegotiationCopilotService service) { this.service = service; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/negotiation-copilot")
    public ApiResponse<AiNegotiationCopilotService.Result> prepare(@Valid @RequestBody AiNegotiationCopilotService.Request request) {
        return ApiResponse.ok(service.prepare(request));
    }
}
