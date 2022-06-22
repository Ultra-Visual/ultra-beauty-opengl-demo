package com.almosting.ultrabeauty

import android.hardware.SensorManager
import android.opengl.GLSurfaceView.RENDERMODE_CONTINUOUSLY
import android.opengl.GLSurfaceView.RENDERMODE_WHEN_DIRTY
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.almosting.adapter.MyAdapter
import java.util.*


class MainActivity : AppCompatActivity(), ViewTreeObserver.OnGlobalLayoutListener {
    private lateinit var mGLSurfaceView: MyGLSurfaceView
    private lateinit var mRootView: ViewGroup
    private lateinit var mSensorManager: SensorManager
    private val mGLRender by lazy { MyGLRender() }

    private val SAMPLE_TITLES = arrayOf(
        "DrawTriangle",
        "TextureMap",
        "YUV Rendering",
        "VAO&VBO",
        "FBO Offscreen Rendering",
        "EGL Background Rendering",
        "FBO Stretching",
        "Coordinate System",
        "Basic Lighting",
        "Transform Feedback",
        "Complex Lighting",
        "Depth Testing",
        "Instancing",
        "Stencil Testing",
        "Blending",
        "Particles",
        "SkyBox",
        "Assimp Load 3D Model",
        "PBO",
        "Beating Heart",
        "Cloud",
        "Time Tunnel",
        "Bezier Curve",
        "Big Eyes",
        "Face Slender",
        "Big Head",
        "Rotary Head",
        "Visualize Audio",
        "Scratch Card",
        "3D Avatar",
        "Shock Wave",
        "MRT",
        "FBO Blit",
        "Texture Buffer",
        "Uniform Buffer",
        "RGB to YUYV",
        "Multi-Thread Render",
        "Text Render",
        "Portrait stay color",
        "GL Transitions_1",
        "GL Transitions_2",
        "GL Transitions_3",
        "GL Transitions_4",
        "RGB to NV21",
        "RGB to I420",
        "RGB to I444",
        "Copy Texture",
        "Blit Frame Buffer"
    )

    private var mSampleSelectedIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRootView = findViewById(R.id.rootView)
        mRootView.viewTreeObserver.addOnGlobalLayoutListener(this)
        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        mGLRender.init()
    }

    override fun onGlobalLayout() {
        mRootView.viewTreeObserver.removeOnGlobalLayoutListener(this)
        val lp: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        ).apply { addRule(RelativeLayout.CENTER_IN_PARENT) }
        mGLSurfaceView = MyGLSurfaceView(this, mGLRender)
        mRootView.addView(mGLSurfaceView, lp)
        mGLSurfaceView.renderMode = RENDERMODE_CONTINUOUSLY
    }


    override fun onDestroy() {
        super.onDestroy()
        mGLRender.unInit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_sample) {
            showGLSampleDialog()
        }
        return true
    }

    private fun showGLSampleDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this)
        val rootView = inflater.inflate(R.layout.sample_selected_layout, null)
        val dialog = builder.create()

        val confirmBtn = rootView.findViewById<Button>(R.id.confirm_btn)
        confirmBtn.setOnClickListener {
            dialog.cancel()
        }

        val resolutionsListView = rootView.findViewById<RecyclerView>(R.id.resolution_list_view)

        val myPreviewSizeViewAdapter = MyAdapter(this, SAMPLE_TITLES.toList())
        myPreviewSizeViewAdapter.mSelectIndex = mSampleSelectedIndex
        myPreviewSizeViewAdapter.addOnItemClickListener(object : MyAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                mRootView.removeView(mGLSurfaceView)
                val lp = RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                )
                lp.addRule(RelativeLayout.CENTER_IN_PARENT)
                mGLSurfaceView = MyGLSurfaceView(this@MainActivity, mGLRender)
                mRootView.addView(mGLSurfaceView, lp)
                val selectIndex: Int = myPreviewSizeViewAdapter.mSelectIndex
                myPreviewSizeViewAdapter.notifyItemChanged(selectIndex)
                myPreviewSizeViewAdapter.notifyItemChanged(position)
                mSampleSelectedIndex = position
                mGLSurfaceView.renderMode = RENDERMODE_WHEN_DIRTY

                mGLRender.setParamsInt(0, position, 0)
                mGLSurfaceView.requestRender()
                dialog.cancel()
            }

        })

        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        resolutionsListView.layoutManager = manager

        resolutionsListView.adapter = myPreviewSizeViewAdapter

        dialog.show()
        dialog.window?.setContentView(rootView)

    }
}