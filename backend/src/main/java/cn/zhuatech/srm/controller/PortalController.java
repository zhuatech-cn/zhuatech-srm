/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.controller;
import cn.zhuatech.srm.common.ApiResponse; import cn.zhuatech.srm.dto.SrmDto.*; import cn.zhuatech.srm.service.SrmService; import jakarta.validation.Valid; import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/portal") @PreAuthorize("hasRole('SUPPLIER')") public class PortalController {
    private final SrmService service;public PortalController(SrmService service){this.service=service;}
    @GetMapping("/dashboard") public ApiResponse<Dashboard> dashboard(){return ApiResponse.ok(service.portalDashboard());}
    @GetMapping("/rfqs") public ApiResponse<List<SourcingView>> rfqs(){return ApiResponse.ok(service.sourcing());}
    @PostMapping("/rfqs/{id}/quotes") public ApiResponse<Void> quote(@PathVariable Long id,@Valid @RequestBody QuoteRequest request){service.submitQuote(id,request);return ApiResponse.ok("报价已提交",null);}
    @GetMapping("/orders") public ApiResponse<List<OrderView>> orders(){return ApiResponse.ok(service.portalOrders());}
    @PostMapping("/orders/{id}/acknowledge") public ApiResponse<Void> acknowledge(@PathVariable Long id){service.acknowledge(id);return ApiResponse.ok("订单已确认",null);}
}
