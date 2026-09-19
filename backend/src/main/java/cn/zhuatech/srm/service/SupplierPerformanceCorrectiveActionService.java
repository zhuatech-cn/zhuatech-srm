/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.service;
import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class SupplierPerformanceCorrectiveActionService{
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public Assessment assess(Request r){
  List<String> blockers=new ArrayList<>();List<String> actions=new ArrayList<>();
  if(!r.scorecardPeriodLocked())blockers.add("绩效评分周期尚未锁定");
  if(!r.sourceDataComplete())blockers.add("交付、质量、成本或服务数据不完整");
  if(r.otifScore()<0||r.qualityScore()<0||r.costScore()<0||r.serviceScore()<0)blockers.add("评分不得小于零");
  int weighted=(r.otifScore()*35+r.qualityScore()*35+r.costScore()*15+r.serviceScore()*15)/100;
  boolean correctionRequired=weighted<70||r.criticalBreach();
  if(correctionRequired&&!r.correctiveActionOwnerAssigned())blockers.add("低绩效供应商未指定整改责任人");
  if(correctionRequired&&!r.correctiveActionDueDateSet())blockers.add("整改计划未设置到期日");
  if(r.criticalBreach()&&!r.riskAndLegalReviewed())blockers.add("重大违约必须完成风险与法务复核");
  if(r.reviewerId().equals(r.approverId()))blockers.add("绩效评价人与最终审批人必须职责分离");
  if(!r.supplierAcknowledged())actions.add("获取供应商对评分及整改要求的确认");
  if(!r.auditEvidenceAttached())actions.add("补充指标来源、会议纪要和整改证据");
  if(correctionRequired&&!r.followUpReviewScheduled())actions.add("创建整改到期复核任务");
  RiskLevel risk=r.criticalBreach()||weighted<60?RiskLevel.CRITICAL:weighted<80?RiskLevel.HIGH:RiskLevel.NORMAL;
  Decision decision=!blockers.isEmpty()?Decision.BLOCKED:!actions.isEmpty()?Decision.REVIEW:correctionRequired?Decision.CORRECTIVE_ACTION:Decision.ACCEPT;
  String route=risk==RiskLevel.CRITICAL?"采购经理→质量/风控→供应链负责人":risk==RiskLevel.HIGH?"采购经理→质量负责人":"采购经理";
  return new Assessment(r.scorecardNo(),weighted,decision,risk,route,List.copyOf(blockers),List.copyOf(actions));
 }
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record Request(@NotBlank String scorecardNo,@NotBlank String reviewerId,@NotBlank String approverId,
  @Min(0)@Max(100)int otifScore,@Min(0)@Max(100)int qualityScore,@Min(0)@Max(100)int costScore,@Min(0)@Max(100)int serviceScore,
  boolean scorecardPeriodLocked,boolean sourceDataComplete,boolean criticalBreach,boolean correctiveActionOwnerAssigned,
  boolean correctiveActionDueDateSet,boolean riskAndLegalReviewed,boolean supplierAcknowledged,
  boolean auditEvidenceAttached,boolean followUpReviewScheduled){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record Assessment(String scorecardNo,int weightedScore,Decision decision,RiskLevel riskLevel,String approvalRoute,List<String> blockers,List<String> actions){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public enum Decision{ACCEPT,CORRECTIVE_ACTION,REVIEW,BLOCKED}/**
                                                               * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                               */
public enum RiskLevel{NORMAL,HIGH,CRITICAL}
}
