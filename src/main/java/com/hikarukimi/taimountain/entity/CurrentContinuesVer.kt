package com.hikarukimi.taimountain.entity

import kotlinx.serialization.Serializable
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * 当前天气实体类。
 *
 * 此类代表了某个时间点的天气状况，包含温度、湿度、体感温度等信息。
 *
 * @author Hikarukimi
 */
@Serializable
data class CurrentWeather(

        /**
         * 天气状况的中文描述。
         *
         * 示例："晴朗" 或 "多云"
         */
        val cap: String,

        /**
         * 昼夜标识符，"d" 表示白天，"n" 表示夜晚。
         *
         * 示例："d" 或 "n"
         */
        val daytime: String,

        /**
         * 体感温度（单位：℃）。
         *
         * 示例：8.0
         */
        val feels: Double,

        /**
         * 相对湿度百分比（单位：%）。
         *
         * 示例：14.0
         */
        val rh: Double,

        /**
         * 当前温度（单位：℃）。
         *
         * 示例：8.0
         */
        val temp: Double,

        /**
         * 创建时间（ISO 8601 格式）。
         *
         * 使用 ISO 8601 标准格式来表示日期时间，以便于解析和处理。
         * 示例："2025-03-16T15:41:00+08:00"
         */
        val created: String,
) {
        /**
         * 将 [created] 字符串转换为 [ZonedDateTime] 对象的方法。
         * 这有助于在程序中更容易地处理时间信息。
         *
         * @return ZonedDateTime 转换后的日期时间对象
         */
        fun getCreatedAsZonedDateTime(): ZonedDateTime {
                return ZonedDateTime.parse(created, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        }
}