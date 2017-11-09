package com.angcyo.xzhsdatams.iview

import android.os.Bundle
import android.view.LayoutInflater
import com.angcyo.library.utils.L
import com.angcyo.uiview.container.ContentLayout
import com.angcyo.xzhsdatams.R
import com.angcyo.xzhsdatams.bean.ProcBean
import com.angcyo.xzproducems.utils.DbUtil

/**
 * Copyright (C) 2016,深圳市红鸟网络科技股份有限公司 All rights reserved.
 * 项目名称：
 * 类的描述：
 * 创建人员：Robi
 * 创建时间：2017/11/09 10:26
 * 修改人员：Robi
 * 修改时间：2017/11/09 10:26
 * 修改备注：
 * Version: 1.0.0
 */
class MainUIView : BaseContentUIView() {
    override fun inflateContentLayout(baseContentLayout: ContentLayout?, inflater: LayoutInflater?) {
        inflate(R.layout.activity_main)
    }

    override fun onViewShow(bundle: Bundle?) {
        super.onViewShow(bundle)
        Thread {
            //DbUtil.test()
            L.e("测试: ${DbUtil.proc_add(ProcBean())}")
            L.e("查询: ${DbUtil.proc_search(0, 0, 0)}")
        }.start()
    }

}