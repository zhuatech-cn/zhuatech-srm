/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.srm.repository; import cn.zhuatech.srm.model.QualityIssue; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface QualityIssueRepository extends JpaRepository<QualityIssue,Long>{List<QualityIssue> findBySupplierId(Long supplierId);}
