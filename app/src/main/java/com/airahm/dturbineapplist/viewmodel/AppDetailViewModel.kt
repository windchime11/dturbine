package com.airahm.dturbineapplist.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.airahm.dturbineapplist.model.AppDetail
import com.airahm.dturbineapplist.repo.MainRepo

class AppDetailViewModel(val r: MainRepo) : ViewModel() {
    val appDetail: LiveData<AppDetail>?

    init {
        appDetail = r.mAppDetail
    }
}