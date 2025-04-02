package com.hikarukimi.taimountain

/**
 * Response 类用于封装 API 响应数据。
 *
 * 包含响应的状态码、消息和数据。提供了静态方法来创建成功的响应、错误的响应和包含数据的响应。
 *
 * @param code    HTTP 状态码，默认成功为 200，错误为 500。
 * @param message 响应的消息描述。
 * @param data    返回的数据，可以是任何类型，对于错误响应通常为 null。
 */
class Response private constructor(private val code: Int, private val message: String, val data: Any?) {

    companion object {

        /**
         * 创建一个表示成功的响应。
         *
         * @param data 返回的数据对象，可以是任何类型。
         * @return 成功的 Response 对象，状态码为 200，消息为 "success"。
         */
        fun success(data: Any?): Response {
            return Response(200, "success", data)
        }

        /**
         * 创建一个表示错误的响应。
         *
         * @return 错误的 Response 对象，状态码为 500，data 为 null。
         */
        fun error(): Response {
            return Response(500, "error", null)
        }

        /**
         * 创建一个表示错误的响应。
         *
         * @param message 错误消息描述。
         * @return 错误的 Response 对象，状态码为 500，data 为 null。
         */
        fun error(message: String): Response {
            return Response(500, message, null)
        }

        /**
         * 创建一个包含数据的成功响应。
         *
         * 这个方法与 success 方法相同，提供了一种明确的方式来指定返回的数据。
         *
         * @param data 返回的数据对象，可以是任何类型。
         * @return 包含数据的成功 Response 对象，状态码为 200，消息为 "success"。
         */
        fun data(data: Any?): Response {
            return success(data)
        }

        // 创建一个自定义状态码的响应
        fun custom(code: Int, message: String, data: Any?): Response {
            return Response(code, message, data)
        }

        // 将异常转换为错误响应
        fun fromException(e: Exception): Response {
            return Response(500, e.message ?: "An unknown error occurred", null)
        }

    }
}