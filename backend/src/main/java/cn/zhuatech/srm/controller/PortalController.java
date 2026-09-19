/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.controller;
import cn.zhuatech.srm.common.ApiResponse; import cn.zhuatech.srm.dto.SrmDto.*; import cn.zhuatech.srm.service.SrmService; import jakarta.validation.Valid; import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.web.bind.annotation.*; import java.util.List;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/portal") @PreAuthorize("hasRole('SUPPLIER')") public class PortalController {
    private final SrmService service;/**
                                      * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                      */
public PortalController(SrmService service){this.service=service;}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @GetMapping("/dashboard") public ApiResponse<Dashboard> dashboard(){return ApiResponse.ok(service.portalDashboard());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @GetMapping("/rfqs") public ApiResponse<List<SourcingView>> rfqs(){return ApiResponse.ok(service.sourcing());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/rfqs/{id}/quotes") public ApiResponse<Void> quote(@PathVariable Long id,@Valid @RequestBody QuoteRequest request){service.submitQuote(id,request);return ApiResponse.ok("报价已提交",null);}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @GetMapping("/orders") public ApiResponse<List<OrderView>> orders(){return ApiResponse.ok(service.portalOrders());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/orders/{id}/acknowledge") public ApiResponse<Void> acknowledge(@PathVariable Long id){service.acknowledge(id);return ApiResponse.ok("订单已确认",null);}
}
