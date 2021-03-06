package com.angcyo.xzhsdatams.iview

import android.graphics.Bitmap
import android.graphics.Color
import android.view.LayoutInflater
import android.view.animation.Animation
import com.angcyo.uiview.container.ContentLayout
import com.angcyo.uiview.model.AnimParam
import com.angcyo.uiview.model.TitleBarPattern
import com.angcyo.uiview.resources.AnimUtil
import com.angcyo.xzhsdatams.R
import com.github.chrisbanes.photoview.PhotoView

/**
 * Created by angcyo on 2017-11-26.
 */
class ImagePreviewUIView(val bitmap: Bitmap) : BaseContentUIView() {

    override fun getTitleBar(): TitleBarPattern =
            super.getTitleBar().setFloating(true).setTitleBarBGColor(Color.TRANSPARENT).setTitleString("")

    override fun inflateContentLayout(baseContentLayout: ContentLayout, inflater: LayoutInflater) {
        inflate(R.layout.image_preview_layout)
    }

    override fun getDefaultBackgroundColor(): Int = getColor(R.color.transparent_dark80)

    override fun initOnShowContentLayout() {
        super.initOnShowContentLayout()
        val bitmapView: PhotoView = v(R.id.photo_view)
        bitmapView.setImageBitmap(bitmap)
    }

    override fun loadStartAnimation(animParam: AnimParam): Animation = AnimUtil.createClipEnterAnim(0.2f)
    override fun loadFinishAnimation(animParam: AnimParam): Animation = AnimUtil.createClipExitAnim(0.2f)
    override fun loadOtherEnterAnimation(animParam: AnimParam): Animation = AnimUtil.createOtherEnterNoAnim()
    override fun loadOtherExitAnimation(animParam: AnimParam): Animation = AnimUtil.createOtherExitNoAnim()

}
