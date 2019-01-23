package com.airahm.dturbineapplist.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.airahm.dturbineapplist.model.AppDetail
import com.airahm.dturbineapplist.repo.MainRepo
import com.airahm.dturbineapplist.utils.LogUtils.Companion.makeLogTag

class AppDetailViewModel(val repo: MainRepo) : ViewModel() {
    companion object {
        val TAG = makeLogTag(AppDetailViewModel::class.java)
    }

    val appDetail: LiveData<AppDetail>?

    init {
        appDetail = repo.mAppDetail
    }

    fun update(appId: String) {
        Log.d(TAG, "appId = ${appId}")
        repo.updateAppDetailLiveData(appId)
    }
}