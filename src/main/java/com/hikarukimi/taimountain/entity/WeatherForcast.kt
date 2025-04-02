package com.hikarukimi.taimountain.entity

import kotlinx.serialization.Serializable
import utils.WindLevel
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * WeatherForecast 实体类。
 *
 * 此类表示天气预报信息，包含温度、湿度、降水量等数据点。
 *
 * @author Hikarukimi
 */
@Serializable
data class WeatherForecast(

    /**
     * 天气状况的描述。
     *
     * 示例："晴朗" 或 "多云"
     */
    val cap: String,

    /**
     * 体感温度（单位：℃）。
     *
     * 示例：8.0
     */
    val feels: Double,

    /**
     * 湿度。
     *
     * 示例：14.0
     */
    val rh: Double,


    /**
     * 降水概率。
     *
     * 示例：2.5
     */
    val precip: Double,

    /**
     * 温度（单位：℃）。
     *
     * 示例：8.0
     */
    val temp: Double,

    /**
     * 创建时间（ISO 8601 格式）。
     *
     * 建议使用 ISO 8601 标准格式来表示日期时间，以便于解析和处理。
     * 示例："2025-03-16T15:41:00+08:00"
     */
    val created: String,

    /**
     * 降雨总量（单位：毫米）。
     *
     * 示例：10.0
     */
    val rainAmount: Double,

    /**
     * 风速（单位：m/s）。
     */
    val windSpd:Double,

    /**
     * 过去24小时内累计降雨量（单位：毫米）。
     *
     * 示例：5.0
     */
    val raAccu: Double,
    /**
     * 日出时间（ISO 8601 格式）。
     *
     * 示例："2025-03-16T06:41:00+08:00"
     */
    var sunrise: String?= null,
    /**
     * 日落时间（ISO 8601 格式）。
     */
    var sunset: String?= null,

    /**
     * 云量百分比（单位：%）。
     *
     * 示例：14.0
     */
    val cloudCover:Double,

    /**
     * 露点温度（单位：℃）。
     */
    val dewPt:Double,
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
        return utils.isFreezingRain(this.temp,this.rainAmount, this.rh,this.dewPt,this.cloudCover)
    }
    private fun isSeaOfClouds(): Boolean {
        return utils.isSeaOfClouds(this.cloudCover)
    }
    private fun isBlizzard(): Boolean {
        return utils.isBlizzard(this.rainAmount,this.windSpd,this.rh,this.temp)
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
}