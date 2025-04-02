package com.hikarukimi.taimountain.entity

import kotlinx.serialization.Serializable
import utils.WindLevel
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
         * 露点温度（单位：℃）。
         */
        val dewPt:Double,

        /**
         * 湿度。
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
         * 风速（单位：m/s）。
         */
        val windSpd:Double,

        /**
         * 风向。
         */
        val pvdrWindDir:String,

        /**
         * 风力。
         */
        val pvdrWindSpd:String,


        /**
         * 气压（单位：hPa）。
         */
        val baro:Double,

        /**
         * 空气质量指数（AQI）。
         */
        val aqi:Double,

        /**
         * 能见度（单位：公里）。
         */
        val vis:Double,

        /**
         * 紫外线指数
         */
        val uv:Double,

        /**
         * 云量百分比（单位：%）。
         *
         * 示例：14.0
         */
        val cloudCover:Double,

        var rainAmount:Double?=null,

/**
         * 创建时间（ISO 8601 格式）。
         *
         * 使用 ISO 8601 标准格式来表示日期时间，以便于解析和处理。
         * 示例："2025-03-16T15:41:00+08:00"
         */
        val created: String,

        /**
         * 是否雾凇
         */
        var rime:Boolean?=null,

        /**
         * 是否雨凇
         */
        var freezingRain:Boolean?=null,

        /**
         * 是否云海
         */
        var seaOfClouds:Boolean?=null,

        /**
         * 是否暴雪
         */
        var blizzard:Boolean?=null,

        /**
         * 建议
         */
        var comment:String?=null,

        var isFreezing:Boolean?=null,

        var evaluateWindLevel: WindLevel?=null
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
        private fun isRime(): Boolean {
                return utils.isRime(this.temp, this.rh, this.dewPt,this.windSpd,this.cloudCover)
        }
        private fun isFreezingRain(): Boolean {
                return utils.isFreezingRain(this.temp,this.rainAmount!!, this.rh,this.dewPt,this.cloudCover)
        }
        private fun isSeaOfClouds(): Boolean {
                return utils.isSeaOfClouds(this.cloudCover)
        }
        private fun isBlizzard(): Boolean {
                return utils.isBlizzard(rainAmount!!,this.windSpd,this.rh,this.temp)
        }
        private fun isFreezing(): Boolean {
                return utils.isFreezing(this.temp,this.dewPt,this.rh)
        }
        private fun evaluateWindLevel(): WindLevel {
                return utils.evaluateWindLevel(this.windSpd)
        }
        fun buildOwn(){
                this.rime = isRime()
                this.freezingRain = isFreezingRain()
                this.seaOfClouds = isSeaOfClouds()
                this.blizzard = isBlizzard()
                this.isFreezing = isFreezing()
                this.evaluateWindLevel = evaluateWindLevel()
        }
        fun buildComment(){
                if(this.comment==null){
                        this.comment = "当前的温度为 ${this.temp}°C，体感为温度 ${this.feels}°C,请注意天气变化"
                }
        }
}