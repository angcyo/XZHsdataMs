package com.angcyo.xzhsdatams.iview

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.angcyo.uiview.base.UIIDialogImpl
import com.angcyo.uiview.utils.T_
import com.angcyo.uiview.widget.ExEditText
import com.angcyo.xzhsdatams.R

/**
 * Created by angcyo on 2017-11-21.
 */
open class ModifyPasswordDialog : UIIDialogImpl() {
    override fun getGravity(): Int = Gravity.TOP

    override fun inflateDialogView(dialogRootLayout: FrameLayout, inflater: LayoutInflater): View =
            inflate(R.layout.modify_password_layout)


    override fun initDialogContentView() {
        super.initDialogContentView()
        val ps1: ExEditText = v(R.id.password_view1)
        val ps2: ExEditText = v(R.id.password_view2)

        click(R.id.save_button) {
            when {
                ps1.isEmpty -> {
                    finishDialog()
                }
                ps2.isEmpty -> T_.error("请输入新密码")
                else -> {
                    finishDialog {
                        onModifyPassword(ps1.string(), ps2.string())
                    }
                }
            }
        }
    }

    open fun onModifyPassword(old: String, new: String) {

    }
}
