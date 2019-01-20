package com.airahm.dturbineapplist.ui.fragment

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.transition.TransitionInflater
import android.util.Log
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

    var ob = Observer<AppDetail> {
        it?.run {
            txvTitle.text = productName
            txvCategory.text = categoryName
            txvRating.text = rating

        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mAct = activity as MainActivity
    }

    override fun onCreate(b: Bundle?) {
        super.onCreate(b)
        Log.d(TAG, "onCreate")
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
        txvTitle.transitionName = name + AppConstants.TRANS_SUFFIX
        val act = activity as MainActivity
        val t = act.mTypefaceThin
        txvTitle.typeface = t
        txvDetails.typeface = t
        txvRating.typeface = t

        imvClose.setOnClickListener {
            mAct?.run {
                supportFragmentManager
                    .popBackStack()
                supportFragmentManager.executePendingTransactions()
            }
        }

        mAct?.run {
            viewModel2.appDetail?.observe(this, ob)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
        mAct?.run {
            viewModel2.appDetail?.removeObserver(ob)
        }
    }
}