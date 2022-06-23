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
}