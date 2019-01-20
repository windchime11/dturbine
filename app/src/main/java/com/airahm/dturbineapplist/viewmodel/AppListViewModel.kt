package com.airahm.dturbineapplist.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.airahm.dturbineapplist.db.model.AppListData
import com.airahm.dturbineapplist.repo.MainRepo

class AppListViewModel(val r: MainRepo) : ViewModel() {
    val appList: LiveData<List<AppListData>>?

    init {
        appList = r.mAppList
    }
}