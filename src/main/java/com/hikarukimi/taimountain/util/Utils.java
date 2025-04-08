package com.hikarukimi.taimountain.util;

import com.hikarukimi.taimountain.entity.WindLevel;

/**
 * Utility class for weather-related calculations and evaluations.
 */
public class Utils {
    /**
     * 判断是否为雾凇天气
     */
    public static boolean isRime(double temp, double humidity, double dewPoint, double windSpeed, double cloudCover) {
        return temp >= -10.0 && temp <= 0.0 &&
                humidity >= 70 &&
                dewPoint >= temp - 2 && dewPoint <= temp + 2 && // 露点接近气温
                windSpeed >= 1.0 && windSpeed <= 3.0 &&
                cloudCover >= 90;
    }

    /**
     * 判断是否为雨凇天气
     */
    public static boolean isFreezingRain(double temp, double precipitation, double humidity, double dewPoint, double cloudCover) {
        return temp < 0 &&
                precipitation > 0 &&
                humidity >= 70 &&
                dewPoint >= temp - 2 && dewPoint <= temp + 2 && // 露点接近气温
                cloudCover >= 90;
    }

    /**
     * 判断是否为云海天气
     */
    public static boolean isSeaOfClouds(double lowCloudCover) {
        return lowCloudCover > 70; // 下层云量 > 70 时可能形成云海
    }

    /**
     * 判断是否下雪
     */
    public static boolean isBlizzard(double precipitationRate, double windSpeed, double humidity, double temp) {
        return precipitationRate > 10 &&
                windSpeed >= 17 &&
                humidity >= 80 &&
                temp < 0;
    }

    /**
     * 判断是否结冰
     */
    public static boolean isFreezing(double actualTemp, double dewPoint, double relativeHumidity) {
        return actualTemp <= 0.0 && dewPoint <= 0.0 && relativeHumidity > 70.0;
    }

    /**
     * 评估风力等级
     */
    public static WindLevel evaluateWindLevel(double windSpeed) {
        if (windSpeed >= 17) {
            return WindLevel.StrongGale;
        } else if (windSpeed >= 14) {
            return WindLevel.Gale;
        } else if (windSpeed >= 11) {
            return WindLevel.WindWarning;
        } else {
            return WindLevel.NoWarning;
        }
    }
} 