package com.airahm.dturbineapplist.utils

import com.airahm.dturbineapplist.app.AppConstants

class LogUtils {
    companion object {
        val MAX_LOG_TAG_LENGTH = 23

        fun makeLogTag(str: String): String {
            return if (str.length > MAX_LOG_TAG_LENGTH - AppConstants.LOG_PREFIX_LENGTH) {
                AppConstants.LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - AppConstants.LOG_PREFIX_LENGTH - 1)
            } else AppConstants.LOG_PREFIX + str
        }

        fun makeLogTag(cls: Class<*>): String {
            return makeLogTag(cls.simpleName)
        }
    }
}