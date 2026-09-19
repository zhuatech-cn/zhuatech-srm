/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.model;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDate;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Entity @Table(name="srm_purchase_order")
public class PurchaseOrder extends BaseEntity {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public enum Status { PENDING_ACK, PRODUCING, PART_DELIVERED, COMPLETED }
    @Column(nullable=false,unique=true,length=32) private String orderNo; @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="supplier_id") private Supplier supplier;
    @Column(nullable=false,length=150) private String itemSummary; @Column(nullable=false,precision=14,scale=2) private BigDecimal amount;
    @Column(nullable=false) private LocalDate deliveryDate; @Column(nullable=false) private int deliveredPercent;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=24) private Status status;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    protected PurchaseOrder(){}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public PurchaseOrder(String no,Supplier supplier,String items,BigDecimal amount,LocalDate date,int percent,Status status){orderNo=no;this.supplier=supplier;itemSummary=items;this.amount=amount;deliveryDate=date;deliveredPercent=percent;this.status=status;}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String getOrderNo(){return orderNo;} /**
                                                 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                 */
public Supplier getSupplier(){return supplier;} /**
                                                                                                 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                 */
public String getItemSummary(){return itemSummary;} /**
                                                                                                                                                     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                     */
public BigDecimal getAmount(){return amount;} /**
                                                                                                                                                                                                   * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                   */
public LocalDate getDeliveryDate(){return deliveryDate;} /**
                                                                                                                                                                                                                                                            * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                            */
public int getDeliveredPercent(){return deliveredPercent;} /**
                                                                                                                                                                                                                                                                                                                       * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                       */
public Status getStatus(){return status;}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public void acknowledge(){if(status==Status.PENDING_ACK)status=Status.PRODUCING;}
}
