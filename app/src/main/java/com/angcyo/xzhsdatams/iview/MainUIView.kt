package com.angcyo.xzhsdatams.iview

import android.content.pm.ActivityInfo
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.angcyo.library.okhttp.Ok
import com.angcyo.library.utils.L
import com.angcyo.uiview.container.ContentLayout
import com.angcyo.uiview.dialog.UIFileSelectorDialog
import com.angcyo.uiview.model.TitleBarPattern
import com.angcyo.uiview.net.RException
import com.angcyo.uiview.net.RFunc
import com.angcyo.uiview.net.RSubscriber
import com.angcyo.uiview.net.Rx
import com.angcyo.uiview.utils.RUtils
import com.angcyo.uiview.utils.T_
import com.angcyo.uiview.widget.GlideImageView
import com.angcyo.xzhsdatams.R
import com.angcyo.xzhsdatams.bean.ProcBean
import com.angcyo.xzhsdatams.utils.BitmapAndStringUtils
import com.angcyo.xzproducems.utils.DbUtil
import java.io.File
import java.lang.reflect.Field
import java.text.SimpleDateFormat
import java.util.*

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


    override fun getTitleBar(): TitleBarPattern {
        return super.getTitleBar().addRightItem(TitleBarPattern.TitleBarItem("切换方向") {
            if (screenOrientation == ORIENTATION_PORTRAIT) {
                mActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
            } else {
                mActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
            }
        })
    }

    override fun inflateContentLayout(baseContentLayout: ContentLayout?, inflater: LayoutInflater?) {
        inflate(R.layout.activity_main)
    }

    override fun onViewShow(bundle: Bundle?) {
        super.onViewShow(bundle)
//        Thread {
//            //DbUtil.test()
//            L.e("测试: ${DbUtil.proc_add(ProcBean(1, "", "33333", "33333", "3333", 1, 1, 1,
//                    1, 1, 1, 1, 1, 1,
//                    1, 1, 1, 1, 1, 1,
//                    1, 1, 1, "memob", "pic"))}")
//            //L.e("查询: ${DbUtil.proc_search(0, 0, 0)[0]}")
//            //L.e("查询: ${DbUtil.proc_search(1, 1, 1)[0]}")
//        }.start()
    }

    private fun enableAddButton(enable: Boolean) {
        view(R.id.save_button).isEnabled = enable
        view(R.id.cancel_button).isEnabled = enable
        view(R.id.selector_image).isEnabled = enable
    }

    private fun enableDelButton(enable: Boolean) {
        view(R.id.delete_button).isEnabled = enable
        view(R.id.modify_button).isEnabled = enable
    }

    override fun initOnShowContentLayout() {
        super.initOnShowContentLayout()

        enableAddButton(false)
        enableDelButton(false)

        //搜索
        click(R.id.search_button) {
            selectorId = -1
            onSearch()
        }

        //添加
        click(R.id.add_button) {
            selectorId = -1
            enableAddButton(true)
            enableDelButton(false)
        }

        //清空界面数据
        click(R.id.cancel_button) {
            selectorId = -1
            onCancelView()
            enableAddButton(false)
            enableDelButton(false)
            clearSelectorFile()
            clearImageView()
        }

        //修改
        click(R.id.modify_button) {
            if (selectorId <= 0) {
                T_.show("请先执行查询")
                showTip("请先执行查询.")
            } else {
                val procBean = getViewData()
                if (procBean == null) {
                    T_.error("请检查输入是否为空.")
                } else {
                    L.i("修改id:$selectorId")
                    Rx.base(
                            object : RFunc<Boolean>() {
                                override fun onFuncCall(): Boolean {
                                    return DbUtil.proc_modi(selectorId, procBean)
                                }
                            },
                            object : RSubscriber<Boolean>() {
                                override fun onSucceed(bean: Boolean) {
                                    super.onSucceed(bean)
                                    if (bean) {
                                        showTip("修改${selectorId}成功.")
                                    } else {
                                        showTip("修改${selectorId}失败.")
                                    }
                                }

                                override fun onEnd(isError: Boolean, isNoNetwork: Boolean, e: RException?) {
                                    super.onEnd(isError, isNoNetwork, e)
                                    if (isError) {
                                        showTip("修改${selectorId}失败:${e!!.msg}")
                                    }
                                }
                            }
                    )
                }
            }
        }

        //删除
        click(R.id.delete_button) {
            if (selectorId <= 0) {
                T_.show("请先执行查询")
                showTip("请先执行查询.")
            } else {
                T_.error("你还没有权限删除.")

//                Rx.base(
//                        object : RFunc<Boolean>() {
//                            override fun onFuncCall(): Boolean {
//                                return DbUtil.proc_del(selectorId, 123456)
//                            }
//                        },
//                        object : RSubscriber<Boolean>() {
//                            override fun onSucceed(bean: Boolean) {
//                                super.onSucceed(bean)
//                                if (bean) {
//                                    showTip("删除${selectorId}成功.")
//                                } else {
//                                    showTip("删除${selectorId}失败.")
//                                }
//                            }
//
//                            override fun onEnd(isError: Boolean, isNoNetwork: Boolean, e: RException?) {
//                                super.onEnd(isError, isNoNetwork, e)
//                                if (isError) {
//                                    showTip("删除${selectorId}失败:${e!!.msg}")
//                                }
//                            }
//                        }
//                )
            }
        }

        //保存
        click(R.id.save_button) {
            selectorId = -1
            val procBean = getViewData()
            if (procBean == null) {
                T_.error("请检查输入是否为空.")
            } else {
                L.i("添加:$procBean")
                Rx.base(
                        object : RFunc<Boolean>() {
                            override fun onFuncCall(): Boolean {
                                return DbUtil.proc_add(procBean)
                            }
                        },
                        object : RSubscriber<Boolean>() {
                            override fun onSucceed(bean: Boolean) {
                                super.onSucceed(bean)
                                if (bean) {
                                    showTip("添加成功.")
                                } else {
                                    showTip("添加失败.")
                                }
                            }

                            override fun onEnd(isError: Boolean, isNoNetwork: Boolean, e: RException?) {
                                super.onEnd(isError, isNoNetwork, e)
                                if (isError) {
                                    showTip("添加失败.${e!!.msg}")
                                }
                            }
                        }
                )
            }
        }

        imageView = v(R.id.image_view)

        //选择图片
        click(R.id.selector_image) {
            startIView(UIFileSelectorDialog {
                Ok.instance().type(it.absolutePath, object : Ok.OnImageTypeListener {
                    override fun onLoadStart() {

                    }

                    override fun onImageType(imageUrl: String?, imageType: Ok.ImageType) {
                        if (imageType == Ok.ImageType.UNKNOWN) {
                            showTip("请选择正确的图片文件.")
                        } else {
                            selectorFile = it
                            imageView?.let {
                                it.reset()
                                it.url = selectorFile?.absolutePath
                            }
                        }
                    }
                })
            })
        }
    }

    private var selectorFile: File? = null
    private var imageView: GlideImageView? = null

    private val dateFormat by lazy {
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS")
    }

    private fun showTip(tip: String) {
        mViewHolder.tv(R.id.tip_view).text = "${dateFormat.format(Date())}-> $tip"
    }

    /**选中的id*/
    private var selectorId: Int = -1

    private fun clearSelectorFile() {
        selectorFile = null
    }

    private fun clearImageView() {
        imageView?.reset()
    }

    //搜索按钮事件
    private fun onSearch() {
        val ProductType: TextView = v(R.id.ProductType)
        val Lshou: TextView = v(R.id.Lshou)
        val bianchang: TextView = v(R.id.bianchang)

        if (ProductType.text.isEmpty()) {
            T_.error("请输入型号")
            return
        }

        /*if (Lshou.text.isEmpty() && bianchang.text.isEmpty()) {
        } else*/ if (Lshou.text.isEmpty() || bianchang.text.isEmpty()) {
            T_.error("请输入边长或厘数")
            return
        }

        Rx.base(
                object : RFunc<MutableList<ProcBean>>() {
                    override fun onFuncCall(): MutableList<ProcBean> {
                        return if (Lshou.text.isEmpty() || bianchang.text.isEmpty()) {
                            DbUtil.proc_search(ProductType.text.toString().toInt())
                        } else {
                            DbUtil.proc_search(ProductType.text.toString().toInt(),
                                    Lshou.text.toString().toInt(),
                                    bianchang.text.toString().toInt()
                            )
                        }
                    }
                },
                object : RSubscriber<MutableList<ProcBean>>() {
                    override fun onSucceed(bean: MutableList<ProcBean>) {
                        super.onSucceed(bean)
                        if (bean.isEmpty()) {
                            showTip("没有查询到结果.")
                            enableDelButton(false)
                            view(R.id.selector_image).isEnabled = false
                        } else {
                            showTip("查询到结果 ${bean.size} 条.")
                            mViewHolder.fillView(bean[0])
                            selectorId = bean[0].Id
                            enableDelButton(true)

                            post {
                                view(R.id.selector_image).isEnabled = true
                                view(R.id.cancel_button).isEnabled = true
                            }

                            imageView?.let {
                                it.reset()
                                it.setImageBitmap(BitmapAndStringUtils.convertStringToIcon(bean[0].Pict01))
                            }
                        }
                    }

                    override fun onEnd(isError: Boolean, isNoNetwork: Boolean, e: RException?) {
                        super.onEnd(isError, isNoNetwork, e)
                        enableAddButton(false)
                        if (isError) {
                            enableDelButton(false)
                            view(R.id.selector_image).isEnabled = false

                            selectorId = -1
                            showTip("查询失败, 请重试")
                        }
                    }
                }
        )
    }

    private fun onCancelView() {
        fillView(ProcBean::class.java, ProcBean())
    }

    private fun fillView(clz: Class<*>?, bean: Any?) {
        if (bean == null) {
            return
        }
        val fields: Array<Field>
        if (clz == null) {
            fields = bean.javaClass.declaredFields
        } else {
            fields = clz.declaredFields
        }
        for (f in fields) {
            f.isAccessible = true
            val name = f.name
            try {
                var view: View? = mViewHolder.viewByName(name)
                if (view == null) {
                    view = mViewHolder.viewByName(name + "_view")
                }

                if (view != null) {
                    var value: String? = null
                    try {
                        value = f.get(bean).toString()
                    } catch (e: Exception) {
                        L.w("the clz=" + clz + " bean=" + bean.javaClass.simpleName + " field=" + name + " is null")
                    }
                    if (view is TextView) {
                        view.text = ""
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    private fun getViewData(): ProcBean? {
        val procBean = ProcBean()
        for (f in procBean.javaClass.declaredFields) {
            f.isAccessible = true
            try {
                var view: View? = mViewHolder.viewByName(f.name)
                if (view == null) {
                    view = mViewHolder.viewByName(f.name + "_view")
                }
                if (view is EditText) {
                    if (TextUtils.isEmpty(view.text) && f.name != "Memob") {
                        return null
                    } else {
                        if (f.name == "Memob" ||
                                f.name == "bianchang" ||
                                f.name == "Lshou" ||
                                f.name == "ProductType"
                                ) {
                            f.set(procBean, view.text.toString())
                        } else {
                            f.setInt(procBean, view.text.toString().toInt())
                        }
//                        L.w("1->${view.text}")
//                        f.set(procBean, view.text)
//                        L.w("2->${f.get(procBean)}")
                    }
                } else if (view is TextView) {
                    f.set(procBean, view.text)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        procBean.Pict01 = getFileString()
        return procBean
    }

    private fun getFileString(): String {
        return if (selectorFile == null) {
            "no_pic"
        } else {
            BitmapAndStringUtils.convertIconToString(RUtils.compressBitmap(BitmapFactory.decodeFile(selectorFile!!.absolutePath), 2))
        }
    }

}
