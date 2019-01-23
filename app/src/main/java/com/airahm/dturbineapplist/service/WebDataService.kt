package com.airahm.dturbineapplist.service

import android.content.Context
import android.content.Intent
import android.support.v4.app.JobIntentService
import com.airahm.dturbineapplist.app.AppConstants
import com.airahm.dturbineapplist.repo.MainRepo
import com.airahm.dturbineapplist.utils.JsonUtils.Companion.convertXmlToJsonStr
import com.airahm.dturbineapplist.utils.LogUtils.Companion.makeLogTag
import org.koin.android.ext.android.inject
import java.net.URL

class WebDataService : JobIntentService() {
    val mRepo : MainRepo by inject()
    companion object {
        const val JOB_ID = 1000
        val TAG = makeLogTag(WebDataService::class.java)
    }

    fun enqueueWork(context: Context, work: Intent) {
        enqueueWork(context, WebDataService::class.java, JOB_ID, work)
    }

    override fun onHandleWork(p0: Intent) {
        val xmlString = URL(AppConstants.URL).readText()
        mRepo.updateAppList(convertXmlToJsonStr(xmlString))
    }
}