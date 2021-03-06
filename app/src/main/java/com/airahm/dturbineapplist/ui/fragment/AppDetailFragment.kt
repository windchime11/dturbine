package com.airahm.dturbineapplist.ui.fragment

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airahm.dturbineapplist.R
import com.airahm.dturbineapplist.app.AppConstants
import com.airahm.dturbineapplist.model.AppDetail
import com.airahm.dturbineapplist.ui.activity.MainActivity
import com.airahm.dturbineapplist.utils.LogUtils.Companion.makeLogTag
import kotlinx.android.synthetic.main.fragment_app_details.*

class AppDetailFragment : Fragment() {

    companion object {
        val TAG = makeLogTag(AppDetailFragment::class.java)
    }

    var mAct: MainActivity? = null
    var mAppId : String = ""

    var ob = Observer<AppDetail> {
        it?.run {

            appThumbnailDraweeView.setImageURI(productThumbnail)

            ratingDraweeView.setImageURI(averageRatingImageURL)
            txvAvgRatingImgUrl.text = averageRatingImageURL
            txvClickProxyUrl.text = clickProxyURL
            txvImpressionTrackingUrl.text = impressionTrackingURL
            txvProductName.text = productName
            txvCategory.text = categoryName

            txvProductId.text = productId.toString()
            txvCampaignId.text = campaignId.toString()
            txvCampaignTypeId.text = campaignTypeId.toString()
            txvCreativeId.text = creativeId
            txvBillingTypeId.text = billingTypeId.toString()

            txvCampaignDisplayOrder.text = campaignDisplayOrder.toString()
            txvIsRandomPick.text =  if(isRandomPick) "true" else "false"
            txvHomeScreen.text = if(homeScreen) "true" else "false"
            txvRating.text = rating
            txvDetails.text = productDescription

            txvNumberOfRatings.text = numberOfRatings.toString()
            txvBidRate.text = String.format("%.3f", bidRate)
            txvCallToAction.text = callToAction
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mAct = activity as MainActivity
    }

    override fun onCreate(b: Bundle?) {
        super.onCreate(b)
        mAppId = arguments?.getString(AppConstants.APP_ID) ?: ""
        postponeEnterTransition()
        sharedElementEnterTransition = TransitionInflater
            .from(activity).inflateTransition(R.transition.app_thumbnail)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_app_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val name = arguments?.getString(AppConstants.PRODUCT_NAME)
        appThumbnailDraweeView.transitionName = name
        txvProductName.transitionName = name + AppConstants.TRANS_SUFFIX
        val act = activity as MainActivity
        val t = act.mTypefaceThin

        txvAvgRatingImgUrlLabel_0.typeface = t
        txvClickProxyUrlLabel_1.typeface = t
        txvImpressionTrackingUrlLabel_2.typeface = t
        txvProductNameLabel_3.typeface = t
        txvCategoryLabel_4.typeface = t

        txvProductIdLabel_5.typeface = t
        txvCampaignIdLabel_6.typeface = t
        txvCampaignTypeIdLabel_7.typeface = t
        txvCreativeIdLabel_8.typeface = t
        txvBillingTypeIdLabel_9.typeface = t

        txvCampaignDisplayOrderLabel_10.typeface = t
        txvIsRandomPickLabel_11.typeface = t
        txvHomeScreenLabel_12.typeface = t
        txvRatingLabel_13.typeface = t
        txvDetailsLabel_14.typeface = t

        txvNumberOfRatingsLabel_15.typeface = t
        txvBidRateLabel_16.typeface = t
        txvCallToAction.typeface = t

        txvProductName.typeface = t
        txvDetails.typeface = t
        txvRating.typeface = t
        txvCategory.typeface = t
        txvProductId.typeface = t
        txvProductId.typeface = t

        txvAvgRatingImgUrl.typeface = t
        txvClickProxyUrl.typeface = t
        txvImpressionTrackingUrl.typeface = t
        txvProductName.typeface = t
        txvCategory.typeface = t

        txvProductId.typeface = t
        txvCampaignId.typeface = t
        txvCampaignTypeId.typeface = t
        txvCreativeId.typeface = t
        txvBillingTypeId.typeface = t

        txvCampaignDisplayOrder.typeface = t
        txvIsRandomPick.typeface = t
        txvHomeScreen.typeface = t
        txvRating.typeface = t
        txvDetails.typeface = t

        txvNumberOfRatings.typeface = t
        txvBidRate.typeface = t
        txvCallToAction.typeface = t

        imvClose.setOnClickListener {
            mAct?.run {
                supportFragmentManager
                    .popBackStack()
                supportFragmentManager.executePendingTransactions()
            }
        }

        mAct?.run {
            mVmDetail.update(mAppId)
            mVmDetail.appDetail?.observe(this, ob)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mAct?.run {
            mVmDetail.appDetail?.removeObserver(ob)
        }
    }
}