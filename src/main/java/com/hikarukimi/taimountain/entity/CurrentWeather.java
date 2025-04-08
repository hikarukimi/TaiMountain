package com.hikarukimi.taimountain.entity;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import com.hikarukimi.taimountain.util.Utils;

/**
 * 当前天气实体类。
 * <p>
 * 此类代表了某个时间点的天气状况，包含温度、湿度、体感温度等信息。
 *
 * @author Hikarukimi
 */
public class CurrentWeather {
    /**
     * 天气状况的中文描述。
     * <p>
     * 示例："晴朗" 或 "多云"
     */
    private final String cap;

    /**
     * 昼夜标识符，"d" 表示白天，"n" 表示夜晚。
     * <p>
     * 示例："d" 或 "n"
     */
    private final String daytime;

    /**
     * 体感温度（单位：℃）。
     * 示例：8.0
     */
    private final double feels;

    /**
     * 露点温度（单位：℃）。
     */
    private final double dewPt;

    /**
     * 湿度。
     * 示例：14.0
     */
    private final double rh;

    /**
     * 当前温度（单位：℃）。
     * 示例：8.0
     */
    private final double temp;

    /**
     * 风速（单位：m/s）。
     */
    private final double windSpd;

    /**
     * 风向。
     */
    private final String pvdrWindDir;

    /**
     * 风力。
     */
    private final String pvdrWindSpd;

    /**
     * 气压（单位：hPa）。
     */
    private final double baro;

    /**
     * 空气质量指数（AQI）。
     */
    private final double aqi;

    /**
     * 能见度（单位：公里）。
     */
    private final double vis;

    /**
     * 紫外线指数
     */
    private final double uv;

    /**
     * 云量百分比（单位：%）。
     * 示例：14.0
     */
    private final double cloudCover;

    private Double rainAmount;

    /**
     * 创建时间（ISO 8601 格式）。
     * 使用 ISO 8601 标准格式来表示日期时间，以便于解析和处理。
     * 示例："2025-03-16T15:41:00+08:00"
     */
    private final String created;

    private Boolean rime;
    private Boolean freezingRain;
    private Boolean seaOfClouds;
    private Boolean blizzard;
    private Boolean thunder;
    private String comment;
    private Boolean freezing;
    private WindLevel evaluateWindLevel;

    public CurrentWeather(String cap, String daytime, double feels, double dewPt, double rh,
                         double temp, double windSpd, String pvdrWindDir, String pvdrWindSpd,
                         double baro, double aqi, double vis, double uv, double cloudCover,
                         String created) {
        this.cap = cap;
        this.daytime = daytime;
        this.feels = feels;
        this.dewPt = dewPt;
        this.rh = rh;
        this.temp = temp;
        this.windSpd = windSpd;
        this.pvdrWindDir = pvdrWindDir;
        this.pvdrWindSpd = pvdrWindSpd;
        this.baro = baro;
        this.aqi = aqi;
        this.vis = vis;
        this.uv = uv;
        this.cloudCover = cloudCover;
        this.created = created;
    }

    /**
     * 将 created 字符串转换为 ZonedDateTime 对象的方法。
     * 这有助于在程序中更容易地处理时间信息。
     *
     * @return ZonedDateTime 转换后的日期时间对象
     */
    public ZonedDateTime getCreatedAsZonedDateTime() {
        return ZonedDateTime.parse(created, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    private boolean isRime() {
        return Utils.isRime(this.temp, this.rh, this.dewPt, this.windSpd, this.cloudCover);
    }

    private boolean isFreezingRain() {
        return Utils.isFreezingRain(this.temp, this.rainAmount, this.rh, this.dewPt, this.cloudCover);
    }

    private boolean isSeaOfClouds() {
        return Utils.isSeaOfClouds(this.cloudCover);
    }

    private boolean isBlizzard() {
        return Utils.isBlizzard(rainAmount, this.windSpd, this.rh, this.temp);
    }

    private boolean isFreezing() {
        return Utils.isFreezing(this.temp, this.dewPt, this.rh);
    }

    private WindLevel evaluateWindLevel() {
        return Utils.evaluateWindLevel(this.windSpd);
    }

    public void buildOwn() {
        this.rime = isRime();
        this.freezingRain = isFreezingRain();
        this.seaOfClouds = isSeaOfClouds();
        this.blizzard = isBlizzard();
        this.freezing = isFreezing();
        this.evaluateWindLevel = evaluateWindLevel();
    }

    public void buildComment(ArrayList<WeatherWarning> alters) {
        for (WeatherWarning alter : alters) {
            if (alter.getTitle().contains("雷电")) {
                this.thunder = true;
                break;
            }
        }
        if (thunder == null || !thunder) {
            thunder = false;
        }

        if (this.comment == null) {
            this.comment = String.format("当前的温度为 %.1f°C，体感为温度 %.1f°C,请注意天气变化", this.temp, this.feels);
        }
    }

    // Getters
    public String getCap() { return cap; }
    public String getDaytime() { return daytime; }
    public double getFeels() { return feels; }
    public double getDewPt() { return dewPt; }
    public double getRh() { return rh; }
    public double getTemp() { return temp; }
    public double getWindSpd() { return windSpd; }
    public String getPvdrWindDir() { return pvdrWindDir; }
    public String getPvdrWindSpd() { return pvdrWindSpd; }
    public double getBaro() { return baro; }
    public double getAqi() { return aqi; }
    public double getVis() { return vis; }
    public double getUv() { return uv; }
    public double getCloudCover() { return cloudCover; }
    public Double getRainAmount() { return rainAmount; }
    public String getCreated() { return created; }
    public Boolean getRime() { return rime; }
    public Boolean getFreezingRain() { return freezingRain; }
    public Boolean getSeaOfClouds() { return seaOfClouds; }
    public Boolean getBlizzard() { return blizzard; }
    public Boolean getThunder() { return thunder; }
    public String getComment() { return comment; }
    public Boolean getFreezing() { return freezing; }
    public WindLevel getEvaluateWindLevel() { return evaluateWindLevel; }

    // Setters for mutable fields
    public void setRainAmount(Double rainAmount) { this.rainAmount = rainAmount; }
    public void setRime(Boolean rime) { this.rime = rime; }
    public void setFreezingRain(Boolean freezingRain) { this.freezingRain = freezingRain; }
    public void setSeaOfClouds(Boolean seaOfClouds) { this.seaOfClouds = seaOfClouds; }
    public void setBlizzard(Boolean blizzard) { this.blizzard = blizzard; }
    public void setThunder(Boolean thunder) { this.thunder = thunder; }
    public void setComment(String comment) { this.comment = comment; }
    public void setFreezing(Boolean freezing) { this.freezing = freezing; }
    public void setEvaluateWindLevel(WindLevel evaluateWindLevel) { this.evaluateWindLevel = evaluateWindLevel; }
} 