package com.airahm.dturbineapplist.ui.fragment

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airahm.dturbineapplist.R
import com.airahm.dturbineapplist.adapter.AppListAdapter
import com.airahm.dturbineapplist.app.AppConstants
import com.airahm.dturbineapplist.db.model.AppListData
import com.airahm.dturbineapplist.ui.activity.MainActivity
import com.airahm.dturbineapplist.ui.aux.AppItemDecoration
import com.airahm.dturbineapplist.utils.JsonUtils
import com.airahm.dturbineapplist.utils.LogUtils.Companion.makeLogTag
import kotlinx.android.synthetic.main.fragment_app_list.*
import org.json.JSONObject

class AppListFragment : Fragment() {

    companion object {
        val TAG = makeLogTag(AppListFragment::class.java)
    }

    var mAdapter: AppListAdapter? = null
    var mAct: MainActivity? = null

    val ob = Observer<List<AppListData>> {
        if (it != null && !it.isEmpty() && mAct != null) {
            val s = it.first().data
            mAdapter?.setOnClickListener(mAct!!)
            val lt = JsonUtils.parseAppInfos(JSONObject(s))
            val sm = GridLayoutManager(context, AppConstants.CANVAS_WIDTH_TOTAL_UNIT)
            sm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(p: Int): Int {
                    return lt.get(p).width
                }
            }
            recyclerView?.layoutManager = sm
            mAdapter?.update(lt)
        } else {
            Log.d(TAG, "list of movie data is null or empty")
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")
        mAct = activity as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_app_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        titleTxv.typeface = mAct?.mTypefaceThin
        mAdapter = AppListAdapter(context!!)

        recyclerView?.run {
            setHasFixedSize(true)
            adapter = mAdapter
            addItemDecoration(AppItemDecoration())
        }

        mAct?.run {
            vmList.appList?.observe(this, ob)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
        mAct?.run {
            vmList.appList?.removeObserver(ob)
        }
        mAdapter = null
    }

    override fun onDetach() {
        super.onDetach()
        mAdapter = null
    }
}