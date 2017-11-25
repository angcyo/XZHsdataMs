package com.angcyo.xzhsdatams.iview

import android.content.pm.ActivityInfo
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.TextView
import com.angcyo.library.okhttp.Ok
import com.angcyo.library.utils.L
import com.angcyo.uiview.base.RPopupWindow
import com.angcyo.uiview.container.ContentLayout
import com.angcyo.uiview.dialog.UIFileSelectorDialog
import com.angcyo.uiview.dialog.UIInputDialog
import com.angcyo.uiview.model.TitleBarPattern
import com.angcyo.uiview.net.RException
import com.angcyo.uiview.net.RFunc
import com.angcyo.uiview.net.RSubscriber
import com.angcyo.uiview.net.Rx
import com.angcyo.uiview.recycler.RBaseViewHolder
import com.angcyo.uiview.recycler.RRecyclerView
import com.angcyo.uiview.recycler.adapter.RBaseAdapter
import com.angcyo.uiview.utils.RUtils
import com.angcyo.uiview.utils.T_
import com.angcyo.uiview.utils.string.SingleTextWatcher
import com.angcyo.uiview.widget.Button
import com.angcyo.uiview.widget.ExEditText
import com.angcyo.uiview.widget.GlideImageView
import com.angcyo.uiview.widget.TitleBarLayout
import com.angcyo.xzhsdatams.R
import com.angcyo.xzhsdatams.bean.ProcBean
import com.angcyo.xzhsdatams.utils.BitmapAndStringUtils
import com.angcyo.xzproducems.utils.DbUtil
import com.orhanobut.hawk.Hawk
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

    companion object {

        fun getSearchList(word: String = ""): List<String> {
            val history = Hawk.get<String>("history", "")
            val list = RUtils.split(history)
            if (word.isEmpty()) {
                return list
            } else {
                return list.filter { it.startsWith(word) }
            }
        }

        fun saveSearchHistory(text: String) {
            val history = Hawk.get<String>("history", "")
            if (history.contains(text) || text.isEmpty()) {

            } else {
                Hawk.put("history", "$history,$text")
            }
        }

        fun getSearchList1(word: String = ""): List<String> {
            val history = Hawk.get<String>("history1", "")
            val list = RUtils.split(history)
            if (word.isEmpty()) {
                return list
            } else {
                return list.filter { it.startsWith(word) }
            }
        }

        fun saveSearchHistory1(text: String) {
            val history = Hawk.get<String>("history1", "")
            if (history.contains(text) || text.isEmpty()) {

            } else {
                Hawk.put("history1", "$history,$text")
            }
        }


        fun getSearchList2(word: String = ""): List<String> {
            val history = Hawk.get<String>("history2", "")
            val list = RUtils.split(history)
            if (word.isEmpty()) {
                return list
            } else {
                return list.filter { it.startsWith(word) }
            }
        }

        fun saveSearchHistory2(text: String) {
            val history = Hawk.get<String>("history2", "")
            if (history.contains(text) || text.isEmpty()) {

            } else {
                Hawk.put("history2", "$history,$text")
            }
        }
    }

    override fun getTitleBar(): TitleBarPattern {
        return super.getTitleBar().setTitleStringLength(30)
                .addRightItem(TitleBarPattern.TitleBarItem("修改密码") {
                    startIView(object : ModifyPasswordDialog() {
                        override fun onModifyPassword(old: String, new: String) {
                            super.onModifyPassword(old, new)
                            Rx.base(
                                    object : RFunc<Boolean>() {
                                        override fun onFuncCall(): Boolean =
                                                DbUtil.proc_modipass(old, new)
                                    },
                                    object : RSubscriber<Boolean>() {
                                        override fun onSucceed(bean: Boolean) {
                                            super.onSucceed(bean)
                                            if (bean) {
                                                showTip("修改密码成功.")
                                            } else {
                                                showTip("修改密码失败.")
                                            }
                                        }

                                        override fun onEnd(isError: Boolean, isNoNetwork: Boolean, e: RException?) {
                                            super.onEnd(isError, isNoNetwork, e)
                                            if (isError) {
                                                showTip("修改密码失败:${e!!.msg}")
                                            }
                                        }
                                    }
                            )
                        }
                    })
                })
                .addRightItem(TitleBarPattern.TitleBarItem("切换方向") {
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

    private fun enableSaveButton(enable: Boolean) {
        view(R.id.save_button).isEnabled = enable
    }

    private fun enableDelButton(enable: Boolean) {
        view(R.id.delete_button).isEnabled = enable
    }

    private fun enableAddButton(enable: Boolean) {
        view(R.id.add_button).isEnabled = enable
    }

    private fun enableCancelButton(enable: Boolean) {
        view(R.id.cancel_button).isEnabled = enable
    }

    private fun enableModifyButton(enable: Boolean) {
        view(R.id.modify_button).isEnabled = enable
    }

    private fun enableSaveButtonOnly(enable: Boolean) {
        view(R.id.save_button).isEnabled = enable
    }

    private fun enableSelectorImageButton(enable: Boolean) {
        view(R.id.selector_image).isEnabled = enable
    }

    private fun enableSearchButton(enable: Boolean) {
        view(R.id.search_button).isEnabled = enable
    }

    private fun defaultButtonStyle() {
        selectorId = -1
        enableAddButton(true)
        enableSearchButton(true)
        enableSaveButton(false)
        enableModifyButton(false)
        enableDelButton(false)
        enableCancelButton(false)
        enableSelectorImageButton(false)
        enableAllEditText(false)
    }

    private fun addButtonStyle() {
        selectorId = -1
        enableAddButton(false)
        enableSearchButton(false)
        enableSaveButton(true)
        enableModifyButton(false)
        enableDelButton(false)
        enableCancelButton(true)
        enableSelectorImageButton(true)
        enableAllEditText(true)
    }

    private fun modifyButtonStyle() {
        enableAddButton(false)
        enableSearchButton(false)
        enableSaveButton(true)
        enableModifyButton(false)
        enableDelButton(true)
        enableCancelButton(true)
        enableSelectorImageButton(true)
        enableAllEditText(true)
    }

    override fun initOnShowContentLayout() {
        super.initOnShowContentLayout()

        defaultButtonStyle()

        //搜索
        click(R.id.search_button) {
            selectorId = -1
            onSearch(mViewHolder.v(R.id.focus_tip_view))
            enableSelectorImageButton(false)
        }

        //添加
        click(R.id.add_button) {
            selectorId = -1
            addButtonStyle()
            onCancelView()
            clearSelectorFile()
            clearImageView()
        }

        //清空界面数据
        click(R.id.cancel_button) {
            defaultButtonStyle()
            onCancelView()
            clearSelectorFile()
            clearImageView()
        }

        //修改
        click(R.id.modify_button) {
            modifyButtonStyle()
        }

        //删除
        click(R.id.delete_button) {
            if (selectorId <= 0) {
                T_.show("请先执行查询")
                showTip("请先执行查询.")
            } else {
                //T_.error("你还没有权限删除.")
                startIView(UIInputDialog().apply {
                    dialogConfig = object : UIInputDialog.UIInputDialogConfig() {
                        override fun onInitInputDialog(inputDialog: UIInputDialog, titleBarLayout: TitleBarLayout, textInputLayout: TextInputLayout, editText: ExEditText, okButton: Button) {
                            super.onInitInputDialog(inputDialog, titleBarLayout, textInputLayout, editText, okButton)
                            editText.hint = "请输入密码:"
                            editText.maxLines = 1
                            editText.setIsText(true)
                            editText.setSingleLine(true)

                            click(okButton) {
                                inputDialog.finishDialog {
                                    if (editText.isEmpty) {
                                    } else {
                                        Rx.base(
                                                object : RFunc<Boolean>() {
                                                    override fun onFuncCall(): Boolean =
                                                            DbUtil.proc_del(selectorId, editText.string())
                                                },
                                                object : RSubscriber<Boolean>() {
                                                    override fun onSucceed(bean: Boolean) {
                                                        super.onSucceed(bean)
                                                        if (bean) {
                                                            showTip("删除${selectorId}成功.")
                                                        } else {
                                                            showTip("删除${selectorId}失败.")
                                                        }
                                                    }

                                                    override fun onEnd(isError: Boolean, isNoNetwork: Boolean, e: RException?) {
                                                        super.onEnd(isError, isNoNetwork, e)
                                                        if (isError) {
                                                            showTip("删除${selectorId}失败:${e!!.msg}")
                                                        }
                                                    }
                                                }
                                        )
                                    }

                                }
                            }
                        }
                    }
                })
            }
        }

        //保存
        click(R.id.save_button) {
            val procBean = getViewData()
            if (procBean == null) {
                T_.error("请检查输入是否为空.")
            } else {
                if (selectorId == -1) {
                    L.i("添加:$procBean")
                    Rx.base(object : RFunc<Boolean>() {
                        override fun onFuncCall(): Boolean {
                            return DbUtil.proc_add(procBean)
                        }
                    }, object : RSubscriber<Boolean>() {
                        override fun onSucceed(bean: Boolean) {
                            super.onSucceed(bean)
                            if (bean) {
                                showTip("添加成功.")
                                view(R.id.cancel_button).callOnClick()
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
                } else {
                    L.i("修改:$selectorId")
                    Rx.base(object : RFunc<Boolean>() {
                        override fun onFuncCall(): Boolean {
                            return DbUtil.proc_modi(selectorId, procBean)
                        }
                    }, object : RSubscriber<Boolean>() {
                        override fun onSucceed(bean: Boolean) {
                            super.onSucceed(bean)
                            if (bean) {
                                showTip("修改${selectorId}成功.")
                                view(R.id.cancel_button).callOnClick()
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

        //输入历史
        val ProductTypeEditText: EditText = v(R.id.ProductType)
        ProductTypeEditText.setOnFocusChangeListener { view, b ->
            if (b) {
                showPopListWindow(ProductTypeEditText, ProductTypeEditText.text.toString())
            }
        }
        ProductTypeEditText.addTextChangedListener(object : SingleTextWatcher() {

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (p0.isEmpty() || !ProductTypeEditText.isFocused) {
                    return
                }
                showPopListWindow(ProductTypeEditText, p0.toString())
            }
        })

        //边长
        val bianchang: EditText = v(R.id.bianchang)
        bianchang.setOnFocusChangeListener { view, b ->
            if (b) {
                showPopListWindow1(bianchang, bianchang.text.toString())
            }
        }
        bianchang.addTextChangedListener(object : SingleTextWatcher() {

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (p0.isEmpty() || !bianchang.isFocused) {
                    return
                }
                showPopListWindow1(bianchang, p0.toString())
            }
        })


        //厘数
        val Lshou: EditText = v(R.id.Lshou)
        Lshou.setOnFocusChangeListener { view, b ->
            if (b) {
                showPopListWindow2(Lshou, Lshou.text.toString())
            }
        }
        Lshou.addTextChangedListener(object : SingleTextWatcher() {

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (p0.isEmpty() || !Lshou.isFocused) {
                    return
                }
                showPopListWindow2(Lshou, p0.toString())
            }
        })

        //列表
        val recyclerView: RRecyclerView = mViewHolder.v(R.id.recycler_view)
        adapter = object : RBaseAdapter<ProcBean>(mActivity) {
            override fun getItemLayoutId(viewType: Int): Int = R.layout.item_search_layoyt

            override fun onBindView(holder: RBaseViewHolder, position: Int, bean: ProcBean) {
                //holder.fillView(bean)
                holder.tv(R.id.ProductType_text).text = bean.ProductType
                holder.tv(R.id.Lshou_text).text = bean.Lshou
                holder.tv(R.id.bianchang_text).text = bean.bianchang

                //holder.itemView.setBackgroundColor(Color.RED)

                holder.clickItem {
                    mViewHolder.fillView(bean)
                }
            }
        }

        recyclerView.adapter = adapter
    }

    private lateinit var adapter: RBaseAdapter<ProcBean>

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

    fun String.toInt1(): Int {
        return if (this.isEmpty()) {
            -1
        } else {
            this.toInt()
        }
    }

    //搜索按钮事件
    private fun onSearch(searchView: View) {
        val ProductType: TextView = v(R.id.ProductType)
        val Lshou: TextView = v(R.id.Lshou)
        val bianchang: TextView = v(R.id.bianchang)

        if (ProductType.text.isEmpty()) {
            T_.error("请输入型号")
            return
        }

//        if (Lshou.text.isEmpty() && bianchang.text.isEmpty()) {
//        } else if (Lshou.text.isEmpty() || bianchang.text.isEmpty()) {
//            T_.error("请输入边长或厘数")
//            return
//        }

        searchView.isFocusable = true
        searchView.isFocusableInTouchMode = true
        searchView.requestFocus()

        Rx.base(
                object : RFunc<MutableList<ProcBean>>() {
                    override fun onFuncCall(): MutableList<ProcBean> {
                        /*return if (Lshou.text.isEmpty() || bianchang.text.isEmpty()) {
                           DbUtil.proc_search(ProductType.text.toString().toInt())
                       } else {*/
                        return DbUtil.proc_search(ProductType.text.toString(),
                                Lshou.text.toString(),
                                bianchang.text.toString())
                        /*}*/
                    }
                },
                object : RSubscriber<MutableList<ProcBean>>() {
                    override fun onSucceed(bean: MutableList<ProcBean>) {
                        super.onSucceed(bean)
                        if (bean.isEmpty()) {
                            showTip("没有查询到结果.")
                            view(R.id.selector_image).isEnabled = false
                        } else {
                            showTip("查询到结果 ${bean.size} 条.")
                            saveSearchHistory(ProductType.text.toString())//保存搜索历史
                            saveSearchHistory1(bianchang.text.toString())//
                            saveSearchHistory2(Lshou.text.toString())//

                            adapter.resetData(bean)
                            if (bean.size == 1) {
                                mViewHolder.fillView(bean[0])
                            }

                            selectorId = bean[0].Id
                            enableModifyButton(true)
                            enableAddButton(true)
                            enableCancelButton(false)
                            enableDelButton(true)

                            imageView?.let {
                                it.reset()
                                it.setImageBitmap(BitmapAndStringUtils.convertStringToIcon(bean[0].Pict01))
                            }
                        }
                    }

                    override fun onEnd(isError: Boolean, isNoNetwork: Boolean, e: RException?) {
                        super.onEnd(isError, isNoNetwork, e)
                        if (isError) {
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
        mViewHolder.tv(R.id.Memob).text = ""
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
                    if (f.name == "Memob" ||
                            f.name == "bianchang" ||
                            f.name == "Lshou" ||
                            f.name == "ProductType"
                            ) {
                        f.set(procBean, view.text.toString())
                    } else if (TextUtils.isEmpty(view.text) && f.name != "Memob") {
                        f.setInt(procBean, 0)
                        //return null
                    } else {
                        f.setInt(procBean, view.text.toString().toInt())
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

    private fun enableAllEditText(enable: Boolean) {
        val procBean = ProcBean()
        for (f in procBean.javaClass.declaredFields) {
            try {
                var view: View? = mViewHolder.viewByName(f.name)
                if (view == null) {
                    view = mViewHolder.viewByName(f.name + "_view")
                }
                if (view is EditText && view.tag == null) {
                    view.isEnabled = enable
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    var filterAdapter: RBaseAdapter<String>? = null
    var window: PopupWindow? = null
    private fun showPopListWindow(anchor: View, word: String) {
        val searchList = getSearchList(word)

        if (searchList.isEmpty()) {
            if (window == null) {
                return
            } else {
                window?.dismiss()
            }
        }

        if (filterAdapter != null) {
            filterAdapter?.resetData(searchList)
            return
        }

        RPopupWindow.build(mActivity)
                .layout(R.layout.layout_pop_list, object : RPopupWindow.OnInitLayout {
                    override fun onInitLayout(viewHolder: RBaseViewHolder, window: PopupWindow?) {
                        this@MainUIView.window = window
                        filterAdapter = object : RBaseAdapter<String>(mActivity, searchList) {
                            override fun getItemLayoutId(viewType: Int): Int {
                                return R.layout.item_single_text
                            }

                            override fun onBindView(holder: RBaseViewHolder, position: Int, bean: String?) {
                                holder.tv(R.id.text_view).text = bean

                                holder.clickItem {
                                    val editText: EditText = v(R.id.ProductType)
                                    editText.setText(bean)
                                    editText.setSelection(bean!!.length)
                                    window?.dismiss()
                                }
                            }
                        }

                        viewHolder.reV(R.id.recycler_view).adapter = filterAdapter
                    }

                    override fun onDismiss() {
                        filterAdapter = null
                        window = null
                    }
                })
                .showAsDropDown(anchor)
    }

    private fun showPopListWindow1(anchor: View, word: String) {
        val searchList = getSearchList1(word)

        if (searchList.isEmpty()) {
            if (window == null) {
                return
            } else {
                window?.dismiss()
            }
        }

        if (filterAdapter != null) {
            filterAdapter?.resetData(searchList)
            return
        }

        RPopupWindow.build(mActivity)
                .layout(R.layout.layout_pop_list, object : RPopupWindow.OnInitLayout {
                    override fun onInitLayout(viewHolder: RBaseViewHolder, window: PopupWindow?) {
                        this@MainUIView.window = window
                        filterAdapter = object : RBaseAdapter<String>(mActivity, searchList) {
                            override fun getItemLayoutId(viewType: Int): Int {
                                return R.layout.item_single_text
                            }

                            override fun onBindView(holder: RBaseViewHolder, position: Int, bean: String?) {
                                holder.tv(R.id.text_view).text = bean

                                holder.clickItem {
                                    val editText: EditText = v(R.id.bianchang)
                                    editText.setText(bean)
                                    editText.setSelection(bean!!.length)
                                    window?.dismiss()
                                }
                            }
                        }

                        viewHolder.reV(R.id.recycler_view).adapter = filterAdapter
                    }

                    override fun onDismiss() {
                        filterAdapter = null
                        window = null
                    }
                })
                .showAsDropDown(anchor)
    }

    private fun showPopListWindow2(anchor: View, word: String) {
        val searchList = getSearchList2(word)

        if (searchList.isEmpty()) {
            if (window == null) {
                return
            } else {
                window?.dismiss()
            }
        }

        if (filterAdapter != null) {
            filterAdapter?.resetData(searchList)
            return
        }

        RPopupWindow.build(mActivity)
                .layout(R.layout.layout_pop_list, object : RPopupWindow.OnInitLayout {
                    override fun onInitLayout(viewHolder: RBaseViewHolder, window: PopupWindow?) {
                        this@MainUIView.window = window
                        filterAdapter = object : RBaseAdapter<String>(mActivity, searchList) {
                            override fun getItemLayoutId(viewType: Int): Int {
                                return R.layout.item_single_text
                            }

                            override fun onBindView(holder: RBaseViewHolder, position: Int, bean: String?) {
                                holder.tv(R.id.text_view).text = bean

                                holder.clickItem {
                                    val editText: EditText = v(R.id.Lshou)
                                    editText.setText(bean)
                                    editText.setSelection(bean!!.length)
                                    window?.dismiss()
                                }
                            }
                        }

                        viewHolder.reV(R.id.recycler_view).adapter = filterAdapter
                    }

                    override fun onDismiss() {
                        filterAdapter = null
                        window = null
                    }
                })
                .showAsDropDown(anchor)
    }
}
