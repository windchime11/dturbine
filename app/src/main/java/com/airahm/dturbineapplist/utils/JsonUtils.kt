package com.airahm.dturbineapplist.utils

import com.airahm.dturbineapplist.model.AppDetail
import org.json.JSONArray
import org.json.JSONObject

class JsonUtils {
    companion object {
        fun parseAppInfos(jobj: JSONObject): List<AppDetail> {
            val list = mutableListOf<AppDetail>()
            val ads = jobj.get("ads") as JSONObject
            val jarry = ads.get("ad") as JSONArray
            for (i in 0 until jarry.length()) {
                list.add(AppDetail.parseJsonObj(jarry.get(i) as JSONObject))
            }
            return list
        }
    }
}