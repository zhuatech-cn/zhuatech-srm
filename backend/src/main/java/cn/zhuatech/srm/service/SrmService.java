/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.service;
import cn.zhuatech.srm.common.BusinessException; import cn.zhuatech.srm.dto.SrmDto.*; import cn.zhuatech.srm.model.*; import cn.zhuatech.srm.repository.*; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service @Transactional(readOnly=true)
public class SrmService {
    private final SupplierRepository suppliers; private final SourcingEventRepository sourcing; private final SupplierQuoteRepository quotes; private final PurchaseOrderRepository orders; private final QualityIssueRepository quality; private final CurrentUserService current;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public SrmService(SupplierRepository suppliers,SourcingEventRepository sourcing,SupplierQuoteRepository quotes,PurchaseOrderRepository orders,QualityIssueRepository quality,CurrentUserService current){this.suppliers=suppliers;this.sourcing=sourcing;this.quotes=quotes;this.orders=orders;this.quality=quality;this.current=current;}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Dashboard portalDashboard(){Supplier s=requireSupplier();var open=sourcing.findAll().stream().filter(e->e.getStatus()==SourcingEvent.Status.OPEN).toList();var os=orders.findBySupplierIdOrderByDeliveryDate(s.getId());var issues=quality.findBySupplierId(s.getId()).stream().filter(i->i.getStatus()!=QualityIssue.Status.CLOSED).toList();return new Dashboard(List.of(new Metric("待响应询价",String.valueOf(open.size()),"2 项将在 48 小时内截止","warning"),new Metric("执行中订单",String.valueOf(os.stream().filter(o->o.getStatus()!=PurchaseOrder.Status.COMPLETED).count()),"本月含税金额 ¥386,420","default"),new Metric("准时交付率",s.getOnTimeRate()+"%","较上月 +1.8%","positive"),new Metric("待关闭质量项",String.valueOf(issues.size()),"最早截止 08-02","danger")),List.of(new Activity("09:36","采购订单已变更","PO-202607-0184 交期调整至 08-12","order"),new Activity("昨天","报价已提交","RFQ-202607-021 报价等待评审","quote"),new Activity("07-27","质量整改待补充","8D 报告缺少永久纠正措施验证","quality")),Map.of("supplier",toSupplier(s),"orders",os.stream().limit(4).map(this::toOrder).toList(),"quality",issues.stream().limit(3).map(this::toQuality).toList()));}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Dashboard adminDashboard(){var allSuppliers=suppliers.findAll();var activeOrders=orders.findAll().stream().filter(o->o.getStatus()!=PurchaseOrder.Status.COMPLETED).toList();var openIssues=quality.findAll().stream().filter(i->i.getStatus()!=QualityIssue.Status.CLOSED).toList();return new Dashboard(List.of(new Metric("合作供应商",String.valueOf(allSuppliers.stream().filter(s->s.getStatus()==Supplier.Status.ACTIVE).count()),"本月新增 3 家","default"),new Metric("寻源中项目",String.valueOf(sourcing.findAll().stream().filter(e->e.getStatus()==SourcingEvent.Status.OPEN).count()),"预计金额 ¥1.86M","warning"),new Metric("订单准交率","96.4%","目标值 95%","positive"),new Metric("供应风险",String.valueOf(allSuppliers.stream().filter(s->!"LOW".equals(s.getRiskLevel())).count()),"2 家需重点跟进","danger")),List.of(new Activity("10:12","新供应商资料待审","苏州景澄精密已提交准入资料","supplier"),new Activity("09:48","询价即将截止","RFQ-202607-021 剩余 1 天 6 小时","quote"),new Activity("08:31","交付预警","PO-202607-0191 预计延期 2 天","risk")),Map.of("sourcing",sourcing.findAll().stream().limit(4).map(this::toSourcing).toList(),"orders",activeOrders.stream().limit(4).map(this::toOrder).toList(),"quality",openIssues.stream().limit(4).map(this::toQuality).toList()));}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<SupplierView> suppliers(){return suppliers.findAll().stream().map(this::toSupplier).toList();}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<SourcingView> sourcing(){return sourcing.findAll().stream().map(this::toSourcing).toList();}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<OrderView> portalOrders(){return orders.findBySupplierIdOrderByDeliveryDate(requireSupplier().getId()).stream().map(this::toOrder).toList();}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Transactional public void submitQuote(Long eventId,QuoteRequest request){Supplier supplier=requireSupplier();SourcingEvent event=sourcing.findById(eventId).orElseThrow(()->new BusinessException("询价项目不存在"));if(event.getStatus()!=SourcingEvent.Status.OPEN)throw new BusinessException("该询价已停止接收报价");quotes.save(new SupplierQuote(event,supplier,request.amount(),request.leadDays(),request.validUntil(),request.remark()));event.markResponded();}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Transactional public void acknowledge(Long orderId){PurchaseOrder order=orders.findById(orderId).orElseThrow(()->new BusinessException("采购订单不存在"));if(!Objects.equals(order.getSupplier().getId(),requireSupplier().getId()))throw new BusinessException("无权操作该订单");order.acknowledge();}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    private Supplier requireSupplier(){Supplier supplier=current.get().getSupplier();if(supplier==null)throw new BusinessException("当前账号未关联供应商");return supplier;}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    private SupplierView toSupplier(Supplier s){return new SupplierView(s.getId(),s.getSupplierCode(),s.getSupplierName(),s.getCategoryName(),s.getContactName(),s.getContactPhone(),s.getStatus().name(),s.getQualificationProgress(),s.getRating(),s.getOnTimeRate(),s.getRiskLevel());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    private SourcingView toSourcing(SourcingEvent e){return new SourcingView(e.getId(),e.getEventNo(),e.getTitle(),e.getCategoryName(),e.getOwnerName(),e.getDeadline(),e.getBudget(),e.getInvitedCount(),e.getRespondedCount(),e.getStatus().name());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    private OrderView toOrder(PurchaseOrder o){return new OrderView(o.getId(),o.getOrderNo(),o.getSupplier().getSupplierName(),o.getItemSummary(),o.getAmount(),o.getDeliveryDate(),o.getDeliveredPercent(),o.getStatus().name());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    private QualityView toQuality(QualityIssue q){return new QualityView(q.getId(),q.getIssueNo(),q.getSupplier().getSupplierName(),q.getTitle(),q.getSeverity(),q.getDueDate(),q.getOwnerName(),q.getStatus().name());}
}
