package com.hikarukimi.taimountain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * WeatherInfo 实体类。
 *
 * 此类表示天气信息，包含温度、湿度、风速、空气质量等数据点。
 *
 * @author Hikarukimi
 */
public class WeatherInfo {
    /**
     * 华氏温度。
     * 示例："45"
     */
    @JsonProperty("tempf")
    private String tempF;

    /**
     * 日期，格式如 "03月08日(星期六)"。
     * 示例："03月08日(星期六)"
     */
    @JsonProperty("date")
    private String date;

    /**
     * 当前是否有降雨，"0" 表示没有雨。
     * 示例："0"
     */
    @JsonProperty("rain")
    private String rain;

    /**
     * 可能是指车辆限行编号，这里为空字符串。
     * 示例：""
     */
    @JsonProperty("limitnumber")
    private String limitNumber;

    /**
     * 摄氏温度。
     * 示例："7"
     */
    @JsonProperty("temp")
    private String tempC;

    /**
     * 城市代码，例如 "101120801"。
     * 示例："101120801"
     */
    @JsonProperty("city")
    private String cityCode;

    /**
     * 城市名称，例如 "泰安"。
     * 示例："泰安"
     */
    @JsonProperty("cityname")
    private String cityName;

    /**
     * 风速英文描述，例如 "5km/h"。
     * 示例："5km/h"
     */
    @JsonProperty("wse")
    private String windSpeedEnglish;

    /**
     * 大气压强，单位可能是 hPa
     * 示例："1015"
     */
    @JsonProperty("qy")
    private String pressure;

    /**
     * PM2.5 的空气质量指数。
     * 示例："35"
     */
    @JsonProperty("aqi_pm25")
    private String aqiPm25;

    /**
     * 能见度，例如 "8km"。
     * 示例："8km"
     */
    @JsonProperty("njd")
    private String visibility;

    /**
     * 城市英文名，例如 "taian"。
     * 示例："taian"
     */
    @JsonProperty("nameen")
    private String nameEn;

    /**
     * 英文天气描述，例如 "Cloudy"。
     * 示例："Cloudy"
     */
    @JsonProperty("weathere")
    private String weatherEn;

    /**
     * 风向描述，例如 "西南风"。
     * 示例："西南风"
     */
    @JsonProperty("WD")
    private String windDirection;

    /**
     * 相对湿度百分比，例如 "39%"。
     * 示例："39%"
     */
    @JsonProperty("SD")
    private String humidityPercentage;

    /**
     * 同 SD，相对湿度百分比，例如 "39%"。
     * 示例："39%"
     */
    @JsonProperty("sd")
    private String humidity;

    /**
     * 过去24小时内的降雨量，"0" 表示没有雨。
     * 示例："0"
     */
    @JsonProperty("rain24h")
    private String rainLast24Hours;

    /**
     * 天气代码，例如 "d01" 可能用于内部逻辑或图标显示。
     * 示例："d01"
     */
    @JsonProperty("weathercode")
    private String weatherCode;

    /**
     * 空气质量指数 (AQI)。
     * 示例："50"
     */
    @JsonProperty("aqi")
    private String aqi;

    /**
     * 中文天气描述，例如 "多云"。
     * 示例："多云"
     */
    @JsonProperty("weather")
    private String weatherZh;

    /**
     * 英文风向缩写，例如 "SW"。
     * 示例："SW"
     */
    @JsonProperty("wde")
    private String windDirectionEnglish;

    /**
     * 当前时间，例如 "17:30"。
     * 示例："17:30"
     */
    @JsonProperty("time")
    private String time;

    /**
     * 风力等级，例如 "2级"。
     * 示例："2级"
     */
    @JsonProperty("WS")
    private String windStrength;

    // Getters and Setters
    public String getTempF() {
        return tempF;
    }

    public void setTempF(String tempF) {
        this.tempF = tempF;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getLimitNumber() {
        return limitNumber;
    }

    public void setLimitNumber(String limitNumber) {
        this.limitNumber = limitNumber;
    }

    public String getTempC() {
        return tempC;
    }

    public void setTempC(String tempC) {
        this.tempC = tempC;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getWindSpeedEnglish() {
        return windSpeedEnglish;
    }

    public void setWindSpeedEnglish(String windSpeedEnglish) {
        this.windSpeedEnglish = windSpeedEnglish;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getAqiPm25() {
        return aqiPm25;
    }

    public void setAqiPm25(String aqiPm25) {
        this.aqiPm25 = aqiPm25;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getWeatherEn() {
        return weatherEn;
    }

    public void setWeatherEn(String weatherEn) {
        this.weatherEn = weatherEn;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getHumidityPercentage() {
        return humidityPercentage;
    }

    public void setHumidityPercentage(String humidityPercentage) {
        this.humidityPercentage = humidityPercentage;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getRainLast24Hours() {
        return rainLast24Hours;
    }

    public void setRainLast24Hours(String rainLast24Hours) {
        this.rainLast24Hours = rainLast24Hours;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(String weatherCode) {
        this.weatherCode = weatherCode;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getWeatherZh() {
        return weatherZh;
    }

    public void setWeatherZh(String weatherZh) {
        this.weatherZh = weatherZh;
    }

    public String getWindDirectionEnglish() {
        return windDirectionEnglish;
    }

    public void setWindDirectionEnglish(String windDirectionEnglish) {
        this.windDirectionEnglish = windDirectionEnglish;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWindStrength() {
        return windStrength;
    }

    public void setWindStrength(String windStrength) {
        this.windStrength = windStrength;
    }

    /**
     * 将 date 字符串转换为 Date 对象的方法。
     * 这有助于在程序中更容易地处理日期信息。
     *
     * @return Date 转换后的日期对象
     * @throws ParseException 如果日期格式不正确
     */
    public Date getDateAsDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日(E)", Locale.CHINA);
        return formatter.parse(date);
    }

    /**
     * 将 time 字符串转换为 Date 对象的方法。
     * 这有助于在程序中更容易地处理时间信息。
     *
     * @return Date 转换后的日期对象
     * @throws ParseException 如果时间格式不正确
     */
    public Date getTimeAsDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return formatter.parse(time);
    }
} 