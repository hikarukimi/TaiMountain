package com.hikarukimi.taimountain.util

import com.hikarukimi.taimountain.service.ScheduleService
import org.slf4j.LoggerFactory

/**
 * RegexUtil 提供了用于处理和提取 JSON 数据的正则表达式工具方法。
 *
 * @author Hikarukimi
 */
class RegexUtil private constructor() { // 私有构造函数防止实例化

    companion object {
        /**
         * 日志记录器实例。
         */
        private val logger = LoggerFactory.getLogger(ScheduleService::class.java)

        /**
         * 从输入字符串中提取 JSON 部分。
         *
         * 此方法使用正则表达式来匹配形如 'var variableName = {...};' 的字符串，并返回 {...} 中的内容。
         * 如果输入为 null 或者不符合预期格式，则抛出 IllegalArgumentException。
         *
         * @param input 输入字符串，期望格式为 'var variableName = {...};'
         * @return 提取出来的 JSON 字符串
         * @throws IllegalArgumentException 当输入为 null 或者不符合预期格式时抛出
         */
        fun extractJsonFromVarParameter(input: String?): String {
            if (input == null) {
                throw IllegalArgumentException("输入字符串不能为空")
            }

            // 定义一个正则表达式来匹配 'var variableName = {...};'
            val regex = """var\s+\w+\s*=\s*(\{.*})\s*;?""".toRegex()

            // 使用正则表达式找到匹配的部分
            val matchResult = regex.find(input)

            // 如果找到了匹配的部分，则返回捕获组中的 JSON 字符串
            return matchResult?.groups?.get(1)?.value ?: run {
                logger.error ( "输入字符串不符合预期格式: $input" )
                throw IllegalArgumentException("输入字符串不符合预期格式")
            }
        }

        /**
         * - 支持更多类型的 JSON 结构提取，例如从 HTML 标签中提取 JSON。
         * - 增加对不同格式的输入支持，例如直接解析整个 JSON 对象而非仅限于 'var variableName = {...}' 形式。
         * - 异常处理增强：提供更具体的异常信息或自定义异常类型。
         */

        fun extractJsonFromHtmlTag(input: String): String {
            val regex = "<script\\s+type=\"application/json\">(.*?)</script>".toRegex()
            val matchResult = regex.find(input)
            return matchResult?.groups?.get(1)?.value ?: throw IllegalArgumentException("未找到符合格式的JSON数据")
        }

    }
}