package com.airahm.dturbineapplist.repo

import android.arch.lifecycle.LiveData
import android.content.Context
import com.airahm.dturbineapplist.db.dao.AppListDao
import com.airahm.dturbineapplist.db.db.DTurbineRoomDb
import com.airahm.dturbineapplist.db.model.AppListData
import com.airahm.dturbineapplist.model.AppDetail

class MainRepo (c: Context) {

    val mAppListDao: AppListDao?
    val mAppList: LiveData<List<AppListData>>?
    val mAppDetail: LiveData<AppDetail>? = null

    init {
        val db = DTurbineRoomDb.getDatabase(c)
        mAppListDao = db?.getAppListDao()
        mAppList = mAppListDao?.getOnlyData()
    }


    fun updateAppList(s: String) {
        mAppListDao?.deleteAll()
        mAppListDao?.insert(AppListData("json", System.currentTimeMillis(), s))
    }
}