package com.airahm.dturbineapplist.db.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.airahm.dturbineapplist.app.AppConstants

@Entity(tableName = "${AppConstants.APP_LIST_TABLE_NAME}")
data class AppListData(@ColumnInfo(name = "mime") val mime: String,
                       @ColumnInfo(name = "at") val at: Long,
                       @ColumnInfo(name = "data") val data: String) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_ID")
    var _ID: Long = 0
}