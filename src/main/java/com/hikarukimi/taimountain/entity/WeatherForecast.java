package com.hikarukimi.taimountain.entity;

import com.hikarukimi.taimountain.util.Utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * WeatherForecast entity class.
 * This class represents weather forecast information, including temperature, humidity, precipitation, and other data points.
 *
 * @author Hikarukimi
 */
public class WeatherForecast {
    /**
     * Description of weather conditions.
     * Example: "Sunny" or "Cloudy"
     */
    private String cap;

    /**
     * Feels like temperature (in ℃).
     * Example: 8.0
     */
    private double feels;

    /**
     * Humidity.
     * Example: 14.0
     */
    private double rh;

    /**
     * Probability of precipitation.
     * Example: 2.5
     */
    private double precip;

    /**
     * Temperature (in ℃).
     * Example: 8.0
     */
    private double temp;

    /**
     * Creation time (ISO 8601 format).
     *
     * It is recommended to use ISO 8601 standard format for date-time representation for easier parsing and processing.
     * Example: "2025-03-16T15:41:00+08:00"
     */
    private String created;

    /**
     * Total rainfall (in mm).
     * Example: 10.0
     */
    private double rainAmount;

    /**
     * Wind speed (in m/s).
     */
    private double windSpd;

    /**
     * Accumulated rainfall in the past 24 hours (in mm).
     * Example: 5.0
     */
    private double raAccu;

    /**
     * Sunrise time (ISO 8601 format).
     * Example: "2025-03-16T06:41:00+08:00"
     */
    private String sunrise;

    /**
     * Sunset time (ISO 8601 format).
     */
    private String sunset;

    /**
     * Cloud cover percentage (in %).
     * Example: 14.0
     */
    private double cloudCover;

    /**
     * Dew point temperature (in ℃).
     */
    private double dewPt;

    /**
     * Whether there is rime frost
     */
    private Boolean rime;

    /**
     * Whether there is freezing rain
     */
    private Boolean freezingRain;

    /**
     * Whether there is a sea of clouds
     */
    private Boolean seaOfClouds;

    /**
     * Whether there is a blizzard
     */
    private Boolean blizzard;

    private Boolean freezing;

    private WindLevel evaluateWindLevel;

    // Default constructor
    public WeatherForecast() {
    }

    // Getters and Setters
    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public double getFeels() {
        return feels;
    }

    public void setFeels(double feels) {
        this.feels = feels;
    }

    public double getRh() {
        return rh;
    }

    public void setRh(double rh) {
        this.rh = rh;
    }

    public double getPrecip() {
        return precip;
    }

    public void setPrecip(double precip) {
        this.precip = precip;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public double getRainAmount() {
        return rainAmount;
    }

    public void setRainAmount(double rainAmount) {
        this.rainAmount = rainAmount;
    }

    public double getWindSpd() {
        return windSpd;
    }

    public void setWindSpd(double windSpd) {
        this.windSpd = windSpd;
    }

    public double getRaAccu() {
        return raAccu;
    }

    public void setRaAccu(double raAccu) {
        this.raAccu = raAccu;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public double getDewPt() {
        return dewPt;
    }

    public void setDewPt(double dewPt) {
        this.dewPt = dewPt;
    }

    public Boolean getRime() {
        return rime;
    }

    public void setRime(Boolean rime) {
        this.rime = rime;
    }

    public Boolean getFreezingRain() {
        return freezingRain;
    }

    public void setFreezingRain(Boolean freezingRain) {
        this.freezingRain = freezingRain;
    }

    public Boolean getSeaOfClouds() {
        return seaOfClouds;
    }

    public void setSeaOfClouds(Boolean seaOfClouds) {
        this.seaOfClouds = seaOfClouds;
    }

    public Boolean getBlizzard() {
        return blizzard;
    }

    public void setBlizzard(Boolean blizzard) {
        this.blizzard = blizzard;
    }

    public Boolean getFreezing() {
        return freezing;
    }

    public void setFreezing(Boolean freezing) {
        this.freezing = freezing;
    }

    public WindLevel getEvaluateWindLevel() {
        return evaluateWindLevel;
    }

    public void setEvaluateWindLevel(WindLevel evaluateWindLevel) {
        this.evaluateWindLevel = evaluateWindLevel;
    }

    /**
     * Converts the [created] string to a [ZonedDateTime] object.
     * This helps in processing time information more easily in the program.
     *
     * @return ZonedDateTime the converted date-time object
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
        return Utils.isBlizzard(this.rainAmount, this.windSpd, this.rh, this.temp);
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
} 