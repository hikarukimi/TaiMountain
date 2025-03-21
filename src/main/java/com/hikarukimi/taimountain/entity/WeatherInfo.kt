import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.*

/**
 * WeatherInfo 实体类。
 *
 * 此类表示天气信息，包含温度、湿度、风速、空气质量等数据点。
 *
 * @author Hikarukimi
 */
@Serializable
data class WeatherInfo(

    /**
     * 华氏温度。
     *
     * 示例："45"
     */
    @SerialName("tempf")
    val tempF: String,

    /**
     * 日期，格式如 "03月08日(星期六)"。
     *
     * 示例："03月08日(星期六)"
     */
    @SerialName("date")
    val date: String,

    /**
     * 当前是否有降雨，"0" 表示没有雨。
     *
     * 示例："0"
     */
    @SerialName("rain")
    val rain: String,

    /**
     * 可能是指车辆限行编号，这里为空字符串。
     *
     * 示例：""
     */
    @SerialName("limitnumber")
    val limitNumber: String,

    /**
     * 摄氏温度。
     *
     * 示例："7"
     */
    @SerialName("temp")
    val tempC: String,

    /**
     * 城市代码，例如 "101120801"。
     *
     * 示例："101120801"
     */
    @SerialName("city")
    val cityCode: String,

    /**
     * 城市名称，例如 "泰安"。
     *
     * 示例："泰安"
     */
    @SerialName("cityname")
    val cityName: String,

    /**
     * 风速英文描述，例如 "5km/h"。
     *
     * 示例："5km/h"
     */
    @SerialName("wse")
    val windSpeedEnglish: String,

    /**
     * 大气压强，单位可能是 hPa。
     *
     * 示例："1015"
     */
    @SerialName("qy")
    val pressure: String,

    /**
     * PM2.5 的空气质量指数。
     *
     * 示例："35"
     */
    @SerialName("aqi_pm25")
    val aqiPm25: String,

    /**
     * 能见度，例如 "8km"。
     *
     * 示例："8km"
     */
    @SerialName("njd")
    val visibility: String,

    /**
     * 城市英文名，例如 "taian"。
     *
     * 示例："taian"
     */
    @SerialName("nameen")
    val nameEn: String,

    /**
     * 英文天气描述，例如 "Cloudy"。
     *
     * 示例："Cloudy"
     */
    @SerialName("weathere")
    val weatherEn: String,

    /**
     * 风向描述，例如 "西南风"。
     *
     * 示例："西南风"
     */
    @SerialName("WD")
    val windDirection: String,

    /**
     * 相对湿度百分比，例如 "39%"。
     *
     * 示例："39%"
     */
    @SerialName("SD")
    val humidityPercentage: String,

    /**
     * 同 SD，相对湿度百分比，例如 "39%"。
     *
     * 示例："39%"
     */
    @SerialName("sd")
    val humidity: String,

    /**
     * 过去24小时内的降雨量，"0" 表示没有雨。
     *
     * 示例："0"
     */
    @SerialName("rain24h")
    val rainLast24Hours: String,

    /**
     * 天气代码，例如 "d01" 可能用于内部逻辑或图标显示。
     *
     * 示例："d01"
     */
    @SerialName("weathercode")
    val weatherCode: String,

    /**
     * 空气质量指数 (AQI)。
     *
     * 示例："50"
     */
    @SerialName("aqi")
    val aqi: String,

    /**
     * 中文天气描述，例如 "多云"。
     *
     * 示例："多云"
     */
    @SerialName("weather")
    val weatherZh: String,

    /**
     * 英文风向缩写，例如 "SW"。
     *
     * 示例："SW"
     */
    @SerialName("wde")
    val windDirectionEnglish: String,

    /**
     * 当前时间，例如 "17:30"。
     *
     * 示例："17:30"
     */
    @SerialName("time")
    val time: String,

    /**
     * 风力等级，例如 "2级"。
     *
     * 示例："2级"
     */
    @SerialName("WS")
    val windStrength: String,
) {
    /**
     * 将 [date] 字符串转换为 [Date] 对象的方法。
     * 这有助于在程序中更容易地处理日期信息。
     *
     * @return Date 转换后的日期对象
     */
    fun getDateAsDate(): Date {
        val formatter = SimpleDateFormat("MM月dd日(E)", Locale.CHINA)
        return formatter.parse(date)
    }

    /**
     * 将 [time] 字符串转换为 [Date] 对象的方法。
     * 这有助于在程序中更容易地处理时间信息。
     *
     * @return Date 转换后的日期对象
     */
    fun getTimeAsDate(): Date {
        val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        return formatter.parse(time)
    }
}
