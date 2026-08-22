/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.model;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity @Table(name="srm_supplier")
public class Supplier extends BaseEntity {
    public enum Status { ONBOARDING, ACTIVE, SUSPENDED }
    @Column(nullable=false,unique=true,length=24) private String supplierCode;
    @Column(nullable=false,length=100) private String supplierName;
    @Column(nullable=false,length=60) private String categoryName;
    @Column(length=40) private String contactName; @Column(length=30) private String contactPhone;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Status status;
    @Column(nullable=false) private int qualificationProgress;
    @Column(nullable=false,precision=4,scale=1) private BigDecimal rating;
    @Column(nullable=false,precision=5,scale=2) private BigDecimal onTimeRate;
    @Column(nullable=false,length=20) private String riskLevel;
    protected Supplier(){}
    public Supplier(String code,String name,String category,String contact,String phone,Status status,int progress,BigDecimal rating,BigDecimal onTimeRate,String risk){
        supplierCode=code;supplierName=name;categoryName=category;contactName=contact;contactPhone=phone;this.status=status;qualificationProgress=progress;this.rating=rating;this.onTimeRate=onTimeRate;riskLevel=risk;
    }
    public String getSupplierCode(){return supplierCode;} public String getSupplierName(){return supplierName;} public String getCategoryName(){return categoryName;}
    public String getContactName(){return contactName;} public String getContactPhone(){return contactPhone;} public Status getStatus(){return status;}
    public int getQualificationProgress(){return qualificationProgress;} public BigDecimal getRating(){return rating;} public BigDecimal getOnTimeRate(){return onTimeRate;} public String getRiskLevel(){return riskLevel;}
}
