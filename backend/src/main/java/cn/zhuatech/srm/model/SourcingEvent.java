/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.model;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDateTime;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Entity @Table(name="srm_sourcing_event")
public class SourcingEvent extends BaseEntity {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public enum Status { OPEN, EVALUATING, AWARDED }
    @Column(nullable=false,unique=true,length=32) private String eventNo; @Column(nullable=false,length=120) private String title;
    @Column(nullable=false,length=60) private String categoryName; @Column(nullable=false,length=40) private String ownerName;
    @Column(nullable=false) private LocalDateTime deadline; @Column(nullable=false,precision=14,scale=2) private BigDecimal budget;
    @Column(nullable=false) private int invitedCount; @Column(nullable=false) private int respondedCount;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Status status;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    protected SourcingEvent(){}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public SourcingEvent(String no,String title,String category,String owner,LocalDateTime deadline,BigDecimal budget,int invited,int responded,Status status){eventNo=no;this.title=title;categoryName=category;ownerName=owner;this.deadline=deadline;this.budget=budget;invitedCount=invited;respondedCount=responded;this.status=status;}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String getEventNo(){return eventNo;} /**
                                                 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                 */
public String getTitle(){return title;} /**
                                                                                         * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                         */
public String getCategoryName(){return categoryName;} /**
                                                                                                                                               * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                               */
public String getOwnerName(){return ownerName;} /**
                                                                                                                                                                                               * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                               */
public LocalDateTime getDeadline(){return deadline;} /**
                                                                                                                                                                                                                                                    * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                    */
public BigDecimal getBudget(){return budget;} /**
                                                                                                                                                                                                                                                                                                  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                  */
public int getInvitedCount(){return invitedCount;} /**
                                                                                                                                                                                                                                                                                                                                                     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                                                     */
public int getRespondedCount(){return respondedCount;} /**
                                                                                                                                                                                                                                                                                                                                                                                                            * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                                                                                                            */
public Status getStatus(){return status;}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public void markResponded(){respondedCount=Math.min(invitedCount,respondedCount+1);}
}
