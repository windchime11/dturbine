package com.airahm.dturbineapplist

import com.airahm.dturbineapplist.utils.JsonUtils
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Test

import org.junit.Assert.*

class JsonUtilsUnitTest {
    @Test
    fun parseAppInfos_isCorrect() {
        val outerobj = JSONObject()
        val obj = JSONObject()
        val ads = JSONArray()
        val ad0 = JSONObject()
        val ratingUrl = "https://cdn1.appia.com/cdn/adpub/appwallv1/rated-5-5.png"
        ad0.put("averageRatingImageURL", ratingUrl)
        ad0.put("isRandomPick", false)
        ad0.put("categoryName", "Puzzle")
        ad0.put("billingTypeId", 1)
        ads.put(ad0)
        obj.put("ad", ads)
        outerobj.put("ads", obj)
        val lt = JsonUtils.parseAppInfos(outerobj)
        assertEquals(1, lt.size)
        assertEquals(ratingUrl, lt.get(0).averageRatingImageURL)
        assertEquals(false, lt.get(0).isRandomPick)
        assertEquals("Puzzle", lt.get(0).categoryName)
        assertEquals(1, lt.get(0).billingTypeId)
    }
}
