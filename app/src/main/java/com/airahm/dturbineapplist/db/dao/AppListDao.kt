package com.airahm.dturbineapplist.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.airahm.dturbineapplist.app.AppConstants
import com.airahm.dturbineapplist.db.model.AppListData

@Dao
interface AppListDao {

    @Query("SELECT * FROM ${AppConstants.APP_LIST_TABLE_NAME} LIMIT 1")
    fun getOnlyData(): LiveData<List<AppListData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(w: AppListData)

    @Query("DELETE FROM ${AppConstants.APP_LIST_TABLE_NAME}")
    fun deleteAll()
}