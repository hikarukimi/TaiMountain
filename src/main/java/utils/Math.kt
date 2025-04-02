package utils

/**
 * 判断是否为雾凇天气
 */
fun isRime(temp: Double, humidity: Double, dewPoint: Double, windSpeed: Double, cloudCover: Double): Boolean {
    return temp in -10.0..0.0 &&
            humidity >= 70 &&
            dewPoint >= temp - 2 && dewPoint <= temp + 2 && // 露点接近气温
            windSpeed in 1.0..3.0 &&
            cloudCover >= 90
}

/**
 * 判断是否为雨凇天气
 */
fun isFreezingRain(temp: Double, precipitation: Double, humidity: Double, dewPoint: Double, cloudCover: Double): Boolean {
    return temp < 0 &&
            precipitation > 0 &&
            humidity >= 70 &&
            dewPoint >= temp - 2 && dewPoint <= temp + 2 && // 露点接近气温
            cloudCover >= 90
}

/**
 * 判断是否为云海天气
 */
fun isSeaOfClouds(lowCloudCover: Double): Boolean {
    return lowCloudCover > 70 // 下层云量 > 70 时可能形成云海
}

/**
 * 判断是否下雪
 */
fun isBlizzard(precipitationRate: Double, windSpeed: Double, humidity: Double, temp: Double): Boolean {
    return precipitationRate > 10 &&
            windSpeed >= 17 &&
            humidity >= 80 &&
            temp < 0
}

// 判断是否结冰的函数
fun isFreezing(actualTemp: Double, dewPoint: Double, relativeHumidity: Double): Boolean {
    return actualTemp <= 0.0 && dewPoint <= 0.0 && relativeHumidity > 70.0
}

// 评估风力等级的函数
enum class WindLevel {
    NoWarning,
    WindWarning,
    Gale,
    StrongGale
}

fun evaluateWindLevel(windSpeed: Double): WindLevel {
    return when {
        windSpeed >= 17 -> WindLevel.StrongGale
        windSpeed >= 14 -> WindLevel.Gale
        windSpeed >= 11 -> WindLevel.WindWarning
        else -> WindLevel.NoWarning
    }
}