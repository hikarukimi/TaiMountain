package com.hikarukimi.taimountain.controller
import com.hikarukimi.taimountain.Response
import com.hikarukimi.taimountain.service.WeatherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * com.hikarukimi.taimountain.controller.WeatherController 提供天气信息的 RESTful API 接口。
 *
 * @author Hikarukimi
 */
@RequestMapping("/weathers")
@RestController
class WeatherController
/**
 * 构造函数注入 WeatherService 实例。
 *
 * @param weatherService 用于获取天气数据的服务类实例
 */ @Autowired constructor(private val weatherService: WeatherService) {

    @get:GetMapping("/basic")
    val weather: ResponseEntity<Any>
        /**
         * 获取当前基本天气信息。
         *
         * GET /weathers/basic
         * 返回当前天气的基本信息。
         *
         * @return Response 包含天气数据的对象
         */
        get() {
            try {
                val response: Response = weatherService.getWeather()
                return ResponseEntity.ok(response)
            } catch (e: Exception) {
                return ResponseEntity.status(500).body(Response.error("Failed to retrieve basic weather information."))
            }
        }
    /**
     * 获取天气预报信息。
     *
     * GET /weathers/forecast
     * 返回未来几天的天气预报信息。
     *
     * @return Response 包含天气预报数据的对象
     */
       @GetMapping("/forecast")
       fun forecast(@RequestParam("location") location: String): ResponseEntity<Any>
       {
                try {
                    val response: Response = weatherService.getForecast(location)
                    return ResponseEntity.ok(response)
                } catch (e: Exception) {
                    return ResponseEntity.status(500).body(Response.error("Failed to retrieve weather forecast."))
                }
       }

        @get:GetMapping("/gate")
        val gateTime: ResponseEntity<Any>
        get() {
            try {
                val response: Response = weatherService.getGateTime()
                return ResponseEntity.ok(response)
            } catch (e: Exception) {
                return ResponseEntity.status(500).body(Response.error("Failed to retrieve gate time."))
            }
        }

     @GetMapping("/location/{location}")
     fun getWeatherByLocation(@PathVariable("location") location: String): ResponseEntity<Any> {
         try {
             val response: Response = weatherService.getWeatherByLocation(location)
             return ResponseEntity.ok(response)
         } catch (e: Exception) {
             return ResponseEntity.status(500).body(Response.error("Failed to retrieve weather information."))
         }
     }
}