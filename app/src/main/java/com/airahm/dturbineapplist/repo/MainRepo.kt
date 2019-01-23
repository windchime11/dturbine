package com.airahm.dturbineapplist.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.airahm.dturbineapplist.db.dao.AppListDao
import com.airahm.dturbineapplist.db.db.DTurbineRoomDb
import com.airahm.dturbineapplist.db.model.AppListData
import com.airahm.dturbineapplist.model.AppDetail
import com.airahm.dturbineapplist.utils.JsonUtils
import org.json.JSONObject

class MainRepo (db: DTurbineRoomDb) {

    val mAppListDao: AppListDao?
    val mAppList: LiveData<List<AppListData>>?
    val mAppDetail: MutableLiveData<AppDetail>? = MutableLiveData<AppDetail>()

    init {
        mAppListDao = db.getAppListDao()
        mAppList = mAppListDao.getOnlyData()
    }

    fun updateAppList(s: String) {
        mAppListDao?.deleteAll()
        mAppListDao?.insert(AppListData("json", System.currentTimeMillis(), s))
    }

    fun updateAppDetailLiveData(appId: String) {
        val v = mAppList?.value
        v?.run {
            if(v.size > 0) {
                val lst = v.first().data
                val lt = JsonUtils.parseAppInfos(JSONObject(lst))
                for(a in lt) {
                    if(a.appId == appId) {
                        mAppDetail?.value = a
                    }
                }
            }
        }
    }
}