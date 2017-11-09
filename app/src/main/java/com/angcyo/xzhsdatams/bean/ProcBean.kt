package com.angcyo.xzhsdatams.bean

/**
 * Copyright (C) 2016=0,深圳市红鸟网络科技股份有限公司 All rights reserved.
 * 项目名称：
 * 类的描述：
 * 创建人员：Robi
 * 创建时间：2017/11/09 11:12
 * 修改人员：Robi
 * 修改时间：2017/11/09 11:12
 * 修改备注：
 * Version: 1.0.0
 */

data class ProcBean(
        var ProductType: Int = 0, // nvarchar=0=0, --型号
        var Lshou: Int = 0, // nvarchar =0,  --厘数
        var bianchang: Int = 0, // nvarchar =0, --边长
        var MainTemp1: Int = 0, // decimal =0, --主机温度1
        var MainTemp2: Int = 0, // decimal =0,--主机温度2
        var MainTemp3: Int = 0, // decimal =0,--主机温度3
        var MainTemp4: Int = 0, // decimal =0,--主机温度4
        var MainTemp5: Int = 0, // decimal =0,--主机温度5
        var Mainspeed: Int = 0, // decimal =0,--主机转速
        var SUPPTemp11: Int = 0, // decimal =0,--辅机1温度1
        var SUPPTemp12: Int = 0, // decimal =0,--辅机1温度2
        var SUPPTemp13: Int = 0, // decimal =0,--辅机1温度3
        var SUPPTemp14: Int = 0, // decimal =0,--辅机1温度4
        var SUPPTemp15: Int = 0, // decimal =0,--辅机1温度5
        var SUPPTemp21: Int = 0, // decimal =0,--辅机2温度1
        var SUPPTemp22: Int = 0, // decimal =0,--辅机2温度2
        var SUPPTemp23: Int = 0, // decimal =0,--辅机2温度3
        var SUPPTemp24: Int = 0, // decimal =0,--辅机2温度4
        var SUPPTemp25: Int = 0, // decimal =0,--辅机2温度5
        var SUPPspeed1: Int = 0, // decimal =0,--辅机1转速
        var SUPPspeed2: Int = 0, // decimal =0,--辅机2转速
        var Memob: String = "", // text =0,  --备注
        var Pict01: String = "" // text  --图片
)