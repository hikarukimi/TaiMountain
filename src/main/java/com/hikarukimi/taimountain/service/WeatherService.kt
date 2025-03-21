package com.hikarukimi.taimountain.service

import WeatherInfo
import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONPath
import com.hikarukimi.taimountain.Response
import com.hikarukimi.taimountain.entity.WeatherForecast
import com.hikarukimi.taimountain.UrlConstant
import com.hikarukimi.taimountain.entity.CurrentWeather
import com.hikarukimi.taimountain.util.RegexUtil
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class WeatherService {

    private val logger = LoggerFactory.getLogger(WeatherService::class.java)

    private fun requestBuildWithHeader(): WebClient.Builder {
        return WebClient.builder()
            .defaultHeaders { headers ->
                headers.add("Host", "d1.weather.com.cn")
                headers.add("Referer", "https://www.weather.com.cn/")
            }
    }

    /**
     * 获取当前天气信息。
     *
     * 发送 HTTP 请求从指定 URL 获取天气数据，并解析为 WeatherInfo 对象。
     * 使用 Kotlinx Serialization 的 Json 解析器将响应体中的 JSON 字符串转换为 WeatherInfo 对象。
     *
     * @return Response 包含解析后的 WeatherInfo 数据。
     */
    fun getWeather(): Response {
        // 构建 WebClient 实例并发送 GET 请求
        val webClient = requestBuildWithHeader().baseUrl(UrlConstant.BASIC_URL.urlString).build()

        // 发送请求并处理响应
        return try {
            val responseBody = webClient.get().retrieve().bodyToMono(String::class.java).block()
            if (responseBody != null) {
                // 使用正则表达式提取 JSON 字符串，并通过 Json 解析器将其转换为 WeatherInfo 对象
                Response.data(Json.decodeFromString<WeatherInfo>(RegexUtil.extractJsonFromVarParameter(responseBody)))
            } else {
                Response.error("Failed to retrieve weather data: Empty response body.")
            }
        } catch (e: Exception) {
            logger.error("Error fetching weather data.", e)
            Response.error("Failed to retrieve weather data: ${e.message}")
        }
    }

    /**
     * 获取天气预报信息。
     *
     * 发送 HTTP 请求从指定 URL 获取天气预报数据，并解析为 CurrentWeather 和 WeatherForecast 对象。
     * 使用 JSONPath 提取响应体中的特定字段，并将其解析为相应的对象。
     *
     * @return Response 包含当前天气和天气预报数据。
     */
    fun getForecast(): Response {
        // 构建 WebClient 实例并发送 GET 请求
        val webClient = WebClient.builder()
            .defaultHeaders { headers ->
                headers.add("Referer", "https://www.msn.cn/zh-cn/weather/forecast/in-%E5%B1%B1%E4%B8%9C%E7%9C%81,%E6%B3%B0%E5%AE%89%E5%B8%82?ocid=ansmsnweather&loc=eyJsIjoi5rOw5bGx5Yy6IiwiciI6IuWxseS4nOecgSIsInIyIjoi5rOw5a6J5biCIiwiYyI6IuS4reWNjuS6uuawkeWFseWSjOWbvSIsImkiOiJjbiIsInQiOjEwMiwiZyI6InpoLWNuIiwieCI6IjExNy4xMzUy")
            }
            .baseUrl(UrlConstant.FORECAST_URL.urlString)
            .build()

        // 发送请求并处理响应
        return try {
            val responseBody = webClient.get().retrieve().bodyToMono(String::class.java).block() ?:
            return Response.error("Failed to retrieve forecast data: Empty response body.")

            // 创建自定义的 Json 解析器，忽略未知字段
            val jsonParser = Json { ignoreUnknownKeys = true }

            // 创建 HashMap 用于存储最终结果
            val resultHashMap = HashMap<String, Any>()

            // 使用 JSONPath 提取天气数据
            val weather = JSONPath.eval(responseBody, "$.responses[0].weather")

            // 提取当前天气数据并解析为 CurrentWeather 对象
            val currentString = JSON.toJSONString(JSONPath.eval(weather, "$[0].current"))
            val currentWeather = jsonParser.decodeFromString<CurrentWeather>(currentString)

            // 提取天气预报数据并解析为 WeatherForecast 对象列表
            val forecastString = JSONPath.eval(weather, "$[0].forecast.days..hourly[*]") as ArrayList<ArrayList<*>>
            val forecastList = ArrayList<WeatherForecast>()

            // 遍历天气预报数据，逐个解析并添加到列表中
            for (i in 0 until forecastString.size) {
                for (j in 0 until forecastString[i].size) {
                    val forecast = JSON.toJSONString(forecastString[i][j])
                    forecastList.add(jsonParser.decodeFromString<WeatherForecast>(forecast))
                }
            }

            // 将当前天气和天气预报数据放入 HashMap
            resultHashMap["current"] = currentWeather
            resultHashMap["forecast"] = forecastList

            // 返回包含数据的 Response
            Response.data(resultHashMap)
        } catch (e: Exception) {
            logger.error("Error fetching forecast data.", e)
            Response.error("Failed to retrieve forecast data: ${e.message}")
        }
    }
}