package com.airahm.dturbineapplist.ui.activity

import android.graphics.Typeface
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.airahm.dturbineapplist.app.AppConstants
import com.airahm.dturbineapplist.utils.LogUtils.Companion.makeLogTag
import java.lang.ref.WeakReference

abstract class BaseActivity : AppCompatActivity() {

    companion object {
        val TAG = makeLogTag(BaseActivity::class.java)
        val START_SHOWING_PROGRESS_BAR = 9
    }

    open var mTypefaceThin: Typeface? = null
    var mProgressBarLine: View? = null
    var mAvd: AnimatedVectorDrawable? = null
    var mHandler: Handler? = null

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
        mHandler = BaseHandler(this)
        mHandler?.sendEmptyMessage(START_SHOWING_PROGRESS_BAR)
    }

    fun hideProgressBar() {
        mProgressBarLine?.visibility = View.GONE
        mHandler = Handler()
    }
}