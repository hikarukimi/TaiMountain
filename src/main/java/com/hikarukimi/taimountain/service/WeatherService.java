package com.hikarukimi.taimountain.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONPath;
import com.hikarukimi.taimountain.Response;
import com.hikarukimi.taimountain.UrlConstant;
import com.hikarukimi.taimountain.entity.CurrentWeather;
import com.hikarukimi.taimountain.entity.WeatherForecast;
import com.hikarukimi.taimountain.entity.WeatherWarning;
import com.hikarukimi.taimountain.util.RegexUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    private WebClient.Builder requestBuildWithHeader() {
        return WebClient.builder()
                .defaultHeaders(headers -> {
                    headers.add("Host", "d1.weather.com.cn");
                    headers.add("Referer", "https://www.weather.com.cn/");
                });
    }

    /**
     * 获取当前天气信息。
     * <p>
     * 发送 HTTP 请求从指定 URL 获取天气数据，并解析为 WeatherInfo 对象。
     * 使用 Kotlinx Serialization 的 Json 解析器将响应体中的 JSON 字符串转换为 WeatherInfo 对象。
     *
     * @return Response 包含解析后的 WeatherInfo 数据。
     */
    public Response getWeather() {
        // 构建 WebClient 实例并发送 GET 请求
        WebClient webClient = requestBuildWithHeader()
                .baseUrl(UrlConstant.BASIC_URL.getUrlString())
                .build();

        // 发送请求并处理响应
        try {
            String responseBody = webClient.get().retrieve().bodyToMono(String.class).block();
            if (responseBody != null) {
                // 使用正则表达式提取 JSON 字符串，并通过 Json 解析器将其转换为 WeatherInfo 对象
                return Response.data(
                        JSON.toJSONString(RegexUtil.extractJsonFromVarParameter(responseBody)));
            } else {
                return Response.error("Failed to retrieve weather data: Empty response body.");
            }
        } catch (Exception e) {
            logger.error("Error fetching weather data.", e);
            return Response.error("Failed to retrieve weather data: " + e.getMessage());
        }
    }

    /**
     * 获取天气预报信息。
     * <p>
     * 发送 HTTP 请求从指定 URL 获取天气预报数据，并解析为 CurrentWeather 和 WeatherForecast 对象。
     * 使用 JSONPath 提取响应体中的特定字段，并将其解析为相应的对象。
     *
     * @param location 位置参数
     * @return Response 包含当前天气和天气预报数据。
     */
    public Response getForecast(@RequestParam("location") String location) {
        // 构建 WebClient 实例并发送 GET 请求
        WebClient webClient = WebClient.builder()
                .defaultHeaders(headers -> {
                    headers.add("Referer", "https://www.msn.cn/zh-cn/weather/forecast/in-%E5%B1%B1%E4%B8%9C%E7%9C%81,%E6%B3%B0%E5%AE%89%E5%B8%82?ocid=ansmsnweather&loc=eyJsIjoi5rOw5bGx5Yy6IiwiciI6IuWxseS4nOecgSIsInIyIjoi5rOw5a6J5biCIiwiYyI6IuS4reWNjuS6uuawkeWFseWSjOWbvSIsImkiOiJjbiIsInQiOjEwMiwiZyI6InpoLWNuIiwieCI6IjExNy4xMzUy");
                })
                .baseUrl(UrlConstant.fromName(location))
                .build();

        // 发送请求并处理响应
        try {
            String responseBody = webClient.get().retrieve().bodyToMono(String.class).block();
            if (responseBody == null) {
                return Response.error("Failed to retrieve forecast data: Empty response body.");
            }


            // 创建 HashMap 用于存储最终结果
            Map<String, Object> resultHashMap = new HashMap<>();

            // 使用 JSONPath 提取天气数据
            Object weather = JSONPath.eval(responseBody, "$.responses[0].weather");

            // 提取当前天气数据并解析为 CurrentWeather 对象
            String currentString = JSON.toJSONString(JSONPath.eval(weather, "$[0].current"));
            Object comment = JSONPath.eval(weather, "$[0].alerts.safetyGuide[0]");
            ArrayList<WeatherWarning> thunder = (ArrayList<WeatherWarning>) JSONPath.eval(weather, "$[0].alerts");

            CurrentWeather currentWeather = JSON.parseObject(currentString,CurrentWeather.class);

            // 提取天气预报数据并解析为 WeatherForecast 对象列表
            ArrayList<ArrayList<?>> forecastString = (ArrayList<ArrayList<?>>) JSONPath.eval(weather, "$[0].forecast.days..hourly[*]");
            ArrayList<String> forecastSunSet = (ArrayList<String>) JSONPath.eval(weather, "$[0].forecast.days[*].almanac.sunset");
            ArrayList<String> forecastSunRise = (ArrayList<String>) JSONPath.eval(weather, "$[0].forecast.days[*].almanac.sunrise");
            ArrayList<WeatherForecast> forecastList = new ArrayList<>();

            double rainAmount = 0.0;
            // 遍历天气预报数据，逐个解析并添加到列表中
            for (int i = 0; i < forecastString.size(); i++) {
                for (int j = 0; j < forecastString.get(i).size(); j++) {
                    String forecast = JSON.toJSONString(forecastString.get(i).get(j));
                    WeatherForecast weatherForecast = JSON.parseObject(forecast,WeatherForecast.class);
                    weatherForecast.setSunset(forecastSunSet.get(i));
                    weatherForecast.setSunrise(forecastSunRise.get(i));
                    weatherForecast.buildOwn();
                    rainAmount += weatherForecast.getRainAmount();
                    forecastList.add(weatherForecast);
                }
            }

            // 将当前天气和天气预报数据放入 HashMap
            resultHashMap.put("current", currentWeather);
            currentWeather.setRainAmount(rainAmount);
            currentWeather.buildOwn();
            currentWeather.setComment(comment != null ? comment.toString() : null);
            currentWeather.buildComment(thunder);
            resultHashMap.put("forecast", forecastList);

            // 返回包含数据的 Response
            return Response.data(resultHashMap);
        } catch (Exception e) {
            logger.error("Error fetching forecast data.", e);
            return Response.error("Failed to retrieve forecast data: " + e.getMessage());
        }
    }

    public Response getGateTime() {
        Map<String, String> map = new HashMap<>();
        map.put("openTime", "08:00");
        map.put("closeTime", "16:00");
        return Response.data(map);
    }

    public Response getWeatherByLocation(String location) {
        // 构建 WebClient 实例并发送 GET 请求
        WebClient webClient = WebClient.builder()
                .defaultHeaders(headers -> {
                    headers.add("Referer", "https://www.msn.cn/zh-cn/weather/forecast/in-%E5%B1%B1%E4%B8%9C%E7%9C%81,%E6%B3%B0%E5%AE%89%E5%B8%82?ocid=ansmsnweather&loc=eyJsIjoi5rOw5bGx5Yy6IiwiciI6IuWxseS4nOecgSIsInIyIjoi5rOw5a6J5biCIiwiYyI6IuS4reWNjuS6uuawkeWFseWSjOWbvSIsImkiOiJjbiIsInQiOjEwMiwiZyI6InpoLWNuIiwieCI6IjExNy4xMzUy");
                })
                .baseUrl(UrlConstant.fromName(location))
                .build();

        String responseBody = webClient.get().retrieve().bodyToMono(String.class).block();
        if (responseBody == null) {
            return Response.error("Failed to retrieve forecast data: Empty response body.");
        }

        // 使用 JSONPath 提取天气数据
        Object weather = JSONPath.eval(responseBody, "$.responses[0].weather");
        Object result = JSONPath.eval(weather, "$[0].current.cap");
        return Response.data(result);
    }
} 