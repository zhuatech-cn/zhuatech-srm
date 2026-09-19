/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.repository; import cn.zhuatech.srm.model.PurchaseOrder; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Long>{/**
                                                                                    * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                    */
List<PurchaseOrder> findBySupplierIdOrderByDeliveryDate(Long supplierId);}
