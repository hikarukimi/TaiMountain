enum class UrlConstant(val urlString: String) {
    /**
     * 基础天气数据的URL。
     *
     * 此URL指向一个提供基础天气信息（如温度、湿度等）的接口。
     */
    BASIC_URL("https://d1.weather.com.cn/sk_2d/101120801.html"),

    /**
     * 天气预报数据的URL。
     *
     * 这个URL指向一个提供未来几天天气预报数据的API。该API包含详细的信息，
     * 如每日最高最低温度、风速、降水概率等，并且支持多种参数定制化查询。
     */
    FORECAST_URL("https://api.msn.cn/weather/overview?apikey=j5i4gDqHL6nGYwx5wi5kRhXjtf2c5qgFX9fzfk0TOo&activityId=807965C4-2E6F-47ED-9233-66B1864C80E1&ocid=msftweather&cm=zh-cn&it=edgeid&user=m-181EEE5DD3B7624C2157FAF4D2F46371&scn=APP_ANON&units=C&appId=9e21380c-ff19-4c78-b4ea-19558e93a5d3&wrapodata=false&includemapsmetadata=true&cuthour=true&lifeDays=2&lifeModes=18&includestorm=true&includeLifeActivity=true&lifeSubTypes=1%2C3%2C4%2C10%2C26&insights=65536&startDate=-1&endDate=%2B9&discardFutureInsightTimeseries=true&distanceinkm=0&regionDataCount=20&orderby=distance&days=10&pageOcid=prime-weather%3A%3Aweathertoday-peregrine&source=weather_csr&fdhead=PRG-1SW-WXWPDEL%2CPRG-1SW-WXWPDS%2Cprg-1sw-wxomghd%2Cprg-1sw-wxomghdnr%2Cprg-1sw-wea-waze&region=cn&market=zh-cn&locale=zh-cn&lat=36.19198&lon=117.13526");

    companion object {

        /**
         * 根据枚举名称获取对应的 URL。
         *
         * @param name 枚举名称，例如 "BASIC_URL" 或 "FORECAST_URL"。
         * @return 对应的 URL 字符串，如果找不到匹配的枚举则抛出 IllegalArgumentException。
         */
        fun fromName(name: String): String {
            return try {
                valueOf(name).urlString
            } catch (e: IllegalArgumentException) {
                throw IllegalArgumentException("No constant with name '$name' found in UrlConstant.")
            }
        }


    }
}