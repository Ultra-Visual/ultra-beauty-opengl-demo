package com.almosting.ultrabeauty

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import android.util.Log
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class MyGLSurfaceView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : GLSurfaceView(context, attributeSet) {
    private var mGLRender: MyGLRender
    private var mNativeRender: MyNativeRender

    companion object {
        private const val IMAGE_FORMAT_RGBA = 0x01
        private const val IMAGE_FORMAT_NV21 = 0x02
        private const val IMAGE_FORMAT_NV12 = 0x03
        private const val IMAGE_FORMAT_I420 = 0x04
        private const val TAG = "MyGLSurfaceView"
    }


    init {
        setEGLContextClientVersion(3)
        mNativeRender = MyNativeRender()
        mGLRender = MyGLRender(mNativeRender)
        setRenderer(mGLRender)
        renderMode = RENDERMODE_CONTINUOUSLY
    }

    class MyGLRender(val nativeRender: MyNativeRender) : Renderer {


        override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
            Log.d(TAG, "onSurfaceCreated: ")
            nativeRender.native_OnSurfaceCreated()
        }

        override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
            Log.d(TAG, "onSurfaceChanged: ")
            nativeRender.native_OnSurfaceChanged(width, height)
        }

        override fun onDrawFrame(gl: GL10?) {
            Log.d(TAG, "onDrawFrame: ")
            nativeRender.native_OnDrawFrame()
        }

    }
}