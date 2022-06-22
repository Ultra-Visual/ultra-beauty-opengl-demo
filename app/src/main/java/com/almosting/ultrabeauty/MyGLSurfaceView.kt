package com.almosting.ultrabeauty

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet

class MyGLSurfaceView : GLSurfaceView {
    private var mGLRender: MyGLRender

    constructor(context: Context, glRender: MyGLRender) : this(context,glRender,null)

    constructor(ctx: Context, glRender: MyGLRender, attrs: AttributeSet?) : super(ctx, attrs) {
        mGLRender = glRender
        setEGLContextClientVersion(2)
        setRenderer(mGLRender)
        renderMode = RENDERMODE_CONTINUOUSLY
    }

    companion object {
        private const val IMAGE_FORMAT_RGBA = 0x01
        private const val IMAGE_FORMAT_NV21 = 0x02
        private const val IMAGE_FORMAT_NV12 = 0x03
        private const val IMAGE_FORMAT_I420 = 0x04
        private const val TAG = "MyGLSurfaceView"
    }
}