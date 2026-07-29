# 数据模型

> 上海如静知华信息科技有限公司 · ZhuaTech SRM Community Source Edition

| 表 | 用途 | 关键关系 |
| --- | --- | --- |
| `srm_supplier` | 供应商主数据、资质、绩效与风险 | 被用户、报价、订单、质量问题引用 |
| `srm_user` | 登录账号与角色 | 供应商用户关联 `supplier_id` |
| `srm_sourcing_event` | RFQ/RFP 寻源事件 | 拥有多条供应商报价 |
| `srm_quote` | 正式报价、交期和有效期 | 关联寻源事件与供应商 |
| `srm_purchase_order` | 采购订单与交付进度 | 关联供应商 |
| `srm_quality_issue` | NCR/CAR 整改问题 | 关联供应商 |

数据库迁移入口为 `backend/src/main/resources/db/migration`。新增结构请始终创建新的版本化迁移文件，不要修改已经进入共享环境的历史迁移。
