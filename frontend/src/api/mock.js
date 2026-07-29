/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
export const suppliers=[
 {code:'SUP-2025-0048',name:'上海华扬工业组件有限公司',category:'工业连接器',owner:'陈屿',status:'合作中',grade:'A',score:'91.5',delivery:'96.8%',risk:'低'},
 {code:'SUP-2026-0126',name:'苏州景澄精密制造有限公司',category:'精密机加工',owner:'韩思远',status:'准入审核',grade:'—',score:'84.0',delivery:'92.4%',risk:'中'},
 {code:'SUP-2024-0017',name:'无锡云帆包装科技有限公司',category:'包装材料',owner:'陆琪',status:'合作中',grade:'A',score:'94.2',delivery:'98.1%',risk:'低'},
 {code:'SUP-2025-0083',name:'常州鑫恒电子科技有限公司',category:'电子元器件',owner:'陈屿',status:'整改中',grade:'C',score:'78.6',delivery:'88.3%',risk:'高'},
 {code:'SUP-2024-0061',name:'宁波科锐自动化有限公司',category:'自动化设备',owner:'韩思远',status:'合作中',grade:'B',score:'88.9',delivery:'95.2%',risk:'低'}]
export const rfqs=[
 {no:'RFQ-202607-021',title:'年度工业连接器框架采购',category:'工业连接器',owner:'陈屿',deadline:'07-30 16:00',budget:'¥680,000',progress:'6 / 8',status:'报价中'},
 {no:'RFQ-202607-018',title:'三季度线束组件集中询价',category:'线束组件',owner:'韩思远',deadline:'08-02 12:00',budget:'¥420,000',progress:'4 / 6',status:'报价中'},
 {no:'RFP-202607-009',title:'智能仓储周转箱联合开发',category:'物流容器',owner:'陆琪',deadline:'08-06 18:00',budget:'¥760,000',progress:'3 / 5',status:'澄清中'},
 {no:'RFQ-202606-052',title:'定制机柜附件采购',category:'钣金件',owner:'陈屿',deadline:'06-25 15:00',budget:'¥315,000',progress:'7 / 7',status:'已定标'}]
export const orders=[
 {no:'PO-202607-0184',item:'M12 防水连接器 × 2,400',amount:'¥186,420',date:'08-12',progress:35,status:'生产中'},
 {no:'PO-202607-0198',item:'重载矩形连接器 × 860',amount:'¥126,800',date:'08-19',progress:0,status:'待确认'},
 {no:'PO-202606-0162',item:'工业航空插头 × 1,200',amount:'¥73,200',date:'08-04',progress:80,status:'部分交付'},
 {no:'PO-202606-0145',item:'信号端子组件 × 4,000',amount:'¥94,600',date:'07-28',progress:100,status:'已完成'}]
export const quality=[{no:'NCR-202607-014',title:'连接器锁紧扭矩抽检偏低',severity:'主要',due:'08-02',owner:'章皓',status:'原因分析'},{no:'CAR-202607-006',title:'包装标签批次字段缺失',severity:'一般',due:'08-07',owner:'谢雯',status:'效果验证'},{no:'NCR-202607-017',title:'模块耐压测试结果波动',severity:'严重',due:'07-31',owner:'章皓',status:'待响应'}]
export const portalMetrics=[['待响应询价','3','2 项将在 48 小时内截止','warn'],['执行中订单','3','本月含税金额 ¥386,420','blue'],['准时交付率','96.8%','较上月 +1.8%','green'],['待关闭质量项','2','最早截止 08-02','red']]
export const adminMetrics=[['合作供应商','128','本月新增 3 家','blue'],['寻源中项目','7','预计金额 ¥1.86M','warn'],['订单准交率','96.4%','目标值 95%','green'],['供应风险','4','2 家需重点跟进','red']]
