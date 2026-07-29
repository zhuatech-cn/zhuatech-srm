/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.srm.model;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDate;
@Entity @Table(name="srm_purchase_order")
public class PurchaseOrder extends BaseEntity {
    public enum Status { PENDING_ACK, PRODUCING, PART_DELIVERED, COMPLETED }
    @Column(nullable=false,unique=true,length=32) private String orderNo; @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="supplier_id") private Supplier supplier;
    @Column(nullable=false,length=150) private String itemSummary; @Column(nullable=false,precision=14,scale=2) private BigDecimal amount;
    @Column(nullable=false) private LocalDate deliveryDate; @Column(nullable=false) private int deliveredPercent;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=24) private Status status;
    protected PurchaseOrder(){}
    public PurchaseOrder(String no,Supplier supplier,String items,BigDecimal amount,LocalDate date,int percent,Status status){orderNo=no;this.supplier=supplier;itemSummary=items;this.amount=amount;deliveryDate=date;deliveredPercent=percent;this.status=status;}
    public String getOrderNo(){return orderNo;} public Supplier getSupplier(){return supplier;} public String getItemSummary(){return itemSummary;} public BigDecimal getAmount(){return amount;} public LocalDate getDeliveryDate(){return deliveryDate;} public int getDeliveredPercent(){return deliveredPercent;} public Status getStatus(){return status;}
    public void acknowledge(){if(status==Status.PENDING_ACK)status=Status.PRODUCING;}
}
