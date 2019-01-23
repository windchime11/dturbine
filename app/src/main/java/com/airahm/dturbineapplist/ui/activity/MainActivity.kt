package com.airahm.dturbineapplist.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.util.Log
import android.widget.TextView
import com.airahm.dturbineapplist.R
import com.airahm.dturbineapplist.adapter.AppListAdapter
import com.airahm.dturbineapplist.app.AppConstants
import com.airahm.dturbineapplist.model.AppDetail
import com.airahm.dturbineapplist.service.WebDataService
import com.airahm.dturbineapplist.ui.fragment.AppDetailFragment
import com.airahm.dturbineapplist.ui.fragment.AppListFragment
import com.airahm.dturbineapplist.viewmodel.AppDetailViewModel
import com.airahm.dturbineapplist.viewmodel.AppListViewModel
import com.facebook.drawee.view.SimpleDraweeView
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), AppListAdapter.AppItemClickListener {

    val mVmList: AppListViewModel by viewModel()
    val mVmDetail: AppDetailViewModel by viewModel()
    val mService: WebDataService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm = supportFragmentManager
        val fg = AppListFragment()
        fm.beginTransaction()
            .replace(R.id.frag_container, fg).commit()
        fm.executePendingTransactions()

        mService.enqueueWork(this, Intent())
    }

    override fun onItemClick(m: AppDetail, v: SimpleDraweeView, v2: TextView) {
        val appDetailFragment = AppDetailFragment()
        Log.d(TAG, "m.appId = ${m.appId}")
        appDetailFragment.arguments = Bundle().apply {
            putString(AppConstants.APP_ID, m.appId)
        }
        val tag = "details_frag"
        supportFragmentManager
            .beginTransaction()
            .addSharedElement(v, ViewCompat.getTransitionName(v)!!)
            .addSharedElement(v2, ViewCompat.getTransitionName(v2)!!)
            .addToBackStack(tag)
            .replace(R.id.frag_container, appDetailFragment)
            .commit()
        supportFragmentManager.executePendingTransactions()
    }
}
