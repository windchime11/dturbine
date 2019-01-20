package com.airahm.dturbineapplist.adapter

import android.content.Context
import android.graphics.Typeface
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import com.airahm.dturbineapplist.R
import com.airahm.dturbineapplist.app.AppConstants
import com.airahm.dturbineapplist.model.AppDetail
import com.facebook.drawee.view.SimpleDraweeView
import kotlinx.android.synthetic.main.item_app.view.*

class AppListAdapter (act: Context) : RecyclerView.Adapter<AppListAdapter.AppItemViewHolder>() {

    var mThin: Typeface? = Typeface.createFromAsset(act.assets, AppConstants.FONT_PATH)
    var mClickListener: AppItemClickListener? = null
    var parentWidth = 0

    interface AppItemClickListener {
        open fun onItemClick(m: AppDetail, v: SimpleDraweeView, v2: TextView)
    }

    fun setOnClickListener(c: AppItemClickListener) {
        mClickListener = c
    }

    override fun onBindViewHolder(holder: AppItemViewHolder, position: Int) {
        val itm = mWthList.get(position)
        ViewCompat.setTransitionName(holder.item.itemDraweeView, itm.productName)
        ViewCompat.setTransitionName(holder.item.appNameTxv, itm.productName + AppConstants.TRANS_SUFFIX)
        val itemHeight = itm.height * parentWidth / AppConstants.CANVAS_WIDTH_TOTAL_UNIT
        val params = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, itemHeight)
        holder.item.layoutParams = params
        holder.refresh(itm)
        if (itm.width <= AppConstants.SMALL_WIDTH) {
            holder.item.appNameTxv.textSize = AppConstants.SMALL_WIDTH_FONT_SIZE
            holder.item.appRatingTxv.textSize = AppConstants.SMALL_WIDTH_FONT_SIZE
        } else {
            holder.item.appNameTxv.textSize = AppConstants.LARGER_WIDTH_FONT_SIZE
            holder.item.appRatingTxv.textSize = AppConstants.LARGER_WIDTH_FONT_SIZE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): AppItemViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_app, parent, false)
        if (parentWidth == 0) {
            parentWidth = parent.measuredWidth ?: 0
        }
        return AppItemViewHolder(this, parent.context, v, mThin)
    }

    val mWthList = mutableListOf<AppDetail>()

    fun update(ws: List<AppDetail>) {
        mWthList.clear()
        for (w in ws) {
            mWthList.add(w)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mWthList.size
    }

    class AppItemViewHolder(val m: AppListAdapter, val c: Context, val item: View, private val t: Typeface?) :
        RecyclerView.ViewHolder(item) {

        fun refresh(w: AppDetail) {
            item.itemDraweeView.setImageURI(w.productThumbnail)
            item.card_view.setOnClickListener { view ->
                m.mClickListener?.onItemClick(w, item.itemDraweeView, item.appNameTxv)
            }
            item.appNameTxv.typeface = t
            item.appNameTxv.text = w.productName

            item.appRatingTxv.text = w.rating
            item.appRatingTxv.typeface = t
        }
    }

}