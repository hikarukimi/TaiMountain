package com.hikarukimi.taimountain.util;


/**
 * RegexUtil 提供了用于处理和提取 JSON 数据的正则表达式工具方法。
 *
 * @author Hikarukimi
 */
public final class RegexUtil {
    // 私有构造函数防止实例化
    private RegexUtil() {
        throw new AssertionError("Utility class should not be instantiated");
    }

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
    public static String extractJsonFromVarParameter(String input) {
        if (input == null) {
            throw new IllegalArgumentException("输入字符串不能为空");
        }

        // 定义一个正则表达式来匹配 'var variableName = {...};'
        String regex = "var\\s+\\w+\\s*=\\s*(\\{.*})\\s*;?";

        // 使用正则表达式找到匹配的部分
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(input);

        // 如果找到了匹配的部分，则返回捕获组中的 JSON 字符串
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new IllegalArgumentException("输入字符串不符合预期格式");
        }
    }

    /**
     * 从HTML标签中提取JSON数据。
     *
     * @param input 包含JSON数据的HTML字符串
     * @return 提取出来的JSON字符串
     * @throws IllegalArgumentException 当未找到符合格式的JSON数据时抛出
     */
    public static String extractJsonFromHtmlTag(String input) {
        String regex = "<script\\s+type=\"application/json\">(.*?)</script>";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new IllegalArgumentException("未找到符合格式的JSON数据");
        }
    }
} 