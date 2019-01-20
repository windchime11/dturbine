package com.airahm.dturbineapplist.ui.activity

import android.content.ComponentCallbacks2
import android.graphics.Typeface
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.airahm.dturbineapplist.app.AppConstants
import com.airahm.dturbineapplist.utils.LogUtils.Companion.makeLogTag
import java.lang.ref.WeakReference

open class BaseActivity : AppCompatActivity(), ComponentCallbacks2 {

    companion object {
        val TAG = makeLogTag(BaseActivity::class.java)
        val START_SHOWING_PROGRESS_BAR = 9
    }

    open var mTypefaceThin: Typeface? = null

    var mProgressBarLine: View? = null
    var mAvd: AnimatedVectorDrawable? = null
    var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mTypefaceThin = Typeface.createFromAsset(assets, AppConstants.FONT_PATH)
    }

    override fun onDestroy() {
        super.onDestroy()
        mAvd?.stop()
    }

    class BaseHandler(ba: BaseActivity) : Handler() {
        private val activity: WeakReference<BaseActivity> = WeakReference(ba)
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                START_SHOWING_PROGRESS_BAR -> activity.get()?.mAvd?.start()
                else -> {
                }
            }
            super.handleMessage(msg)
        }
    }

    fun showProgressBar() {
        mProgressBarLine?.visibility = View.VISIBLE
        handler = BaseHandler(this)
        handler?.sendEmptyMessage(START_SHOWING_PROGRESS_BAR)
    }

    fun hideProgressBar() {
        mProgressBarLine?.visibility = View.GONE
        handler = Handler()
    }

    override fun onTrimMemory(level: Int) {
        when (level) {
            ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN -> Log.d(TAG, "TRIM_MEMORY_UI_HIDDEN")
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE, ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW, ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL -> Log.d(
                TAG, "TRIM_MEMORY_RUNNING_MODERATE, LOW, CRITICAL"
            )
            ComponentCallbacks2.TRIM_MEMORY_BACKGROUND, ComponentCallbacks2.TRIM_MEMORY_MODERATE, ComponentCallbacks2.TRIM_MEMORY_COMPLETE -> Log.d(
                TAG, "TRIM_MEMORY_BACKGROUND, MODERATE, COMPLETE"
            )
            else -> Log.d(TAG, "TRIM_MEMORY GENERIC")
        }
    }

    override fun onLowMemory() {
    }

    open fun registerReceiversOnStart() {}

    open fun unRegisterReceiversOnStop() {}

    override fun onStart() {
        super.onStart()
        registerReceiversOnStart()
    }

    override fun onStop() {
        super.onStop()
        unRegisterReceiversOnStop()
        mAvd?.stop()
    }
}