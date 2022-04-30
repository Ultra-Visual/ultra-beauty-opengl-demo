package com.almosting.ultrabeauty

import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), ViewTreeObserver.OnGlobalLayoutListener {
    private lateinit var mGLSurfaceView: MyGLSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mGLSurfaceView = MyGLSurfaceView(this)
        setContentView(mGLSurfaceView)
//        setContentView(R.layout.activity_main)
    }

    override fun onGlobalLayout() {

    }
}