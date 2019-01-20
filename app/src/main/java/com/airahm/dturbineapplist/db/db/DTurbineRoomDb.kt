package com.airahm.dturbineapplist.db.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.airahm.dturbineapplist.app.AppConstants
import com.airahm.dturbineapplist.db.dao.AppListDao
import com.airahm.dturbineapplist.db.model.AppListData

@Database(entities = arrayOf(AppListData::class), version = 1, exportSchema = false)
abstract class DTurbineRoomDb : RoomDatabase() {

    abstract fun getAppListDao(): AppListDao

    companion object {
        @Volatile
        private var INSTANCE: DTurbineRoomDb? = null

        fun getDatabase(c: Context): DTurbineRoomDb? {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    c.getApplicationContext(),
                    DTurbineRoomDb::class.java, AppConstants.MAIN_DB_NAME
                ).build()
                INSTANCE
            }
        }
    }
}