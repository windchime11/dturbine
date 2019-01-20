package com.airahm.dturbineapplist.model

import com.airahm.dturbineapplist.app.AppConstants
import org.json.JSONObject

class AppDetail(val productName: String,
                val averageRatingImageURL: String,
                val isRandomPick: Boolean,
                val clickProxyURL: String,
                val productId: Int,
                val campaignId: Int,
                val rating: String,
                val homeScreen: Boolean,
                val impressionTrackingURL: String,
                val bidRate: Double,
                val categoryName: String,
                val creativeId: String,
                val callToAction: String,
                val campaignDisplayOrder: Int,
                val billingTypeId: Int,
                val productThumbnail: String,
                val appId: String,
                val numberOfRatings: String,
                val campaignTypeId: Int,
                val productDescription: String,
                val lastUpdated: Int,
                val width: Int, val height: Int) {
    companion object {
        fun parseJsonObj(obj: JSONObject): AppDetail {
            return obj.run {
                AppDetail(
                    optString(AppConstants.PRODUCT_NAME),
                    optString(AppConstants.AVERAGE_RATING_IMAGEURL),
                    optBoolean(AppConstants.IS_RANDOM_PICK),
                    optString(AppConstants.CLICK_PROXYURL),
                    optInt(AppConstants.PRODUCT_ID),

                    optInt(AppConstants.CAMPAIGN_ID),
                    optString(AppConstants.RATING),
                    optBoolean(AppConstants.HOME_SCREEN),
                    optString(AppConstants.IMPRESSION_TRACKING_URL),
                    optDouble(AppConstants.BID_RATE),
                    optString(AppConstants.CATEGORY_NAME),
                    optString(AppConstants.CREATIVE_ID),
                    optString(AppConstants.CALL_TO_ACTION),
                    optInt(AppConstants.CAMPAIGN_DISPLAY_ORDER),
                    optInt(AppConstants.BILLING_TYPE_ID),
                    optString(AppConstants.PRODUCT_THUMBNAIL),
                    optString(AppConstants.APP_ID),
                    optString(AppConstants.NUMBER_OF_RATINGS),
                    optInt(AppConstants.CAMPAIGN_TYPE_ID),
                    optString(AppConstants.PRODUCT_DESCRIPTION),
                    optInt(AppConstants.LAST_UPDATED),
                    optInt(AppConstants.WIDTH, AppConstants.WIDTH_DEFAULT),
                    optInt(AppConstants.HEIGHT, AppConstants.HEIGHT_DEFAULT)
                )
            }
        }
    }
}