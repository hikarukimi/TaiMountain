package com.hikarukimi.taimountain;

import lombok.Getter;

/**
 * @author hikarukimi
 */
@Getter
public class Response {
    // Getters
    private final int code;
    private final String message;
    private final Object data;

    private Response(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * Creates a successful response.
     *
     * @param data Return data object, can be any type.
     * @return Successful Response object with status code 200 and message "success".
     */
    public static Response success(Object data) {
        return new Response(200, "success", data);
    }

    /**
     * Creates an error response.
     *
     * @return Error Response object with status code 500 and null data.
     */
    public static Response error() {
        return new Response(500, "error", null);
    }

    /**
     * Creates an error response with a custom message.
     *
     * @param message Error message description.
     * @return Error Response object with status code 500 and null data.
     */
    public static Response error(String message) {
        return new Response(500, message, null);
    }

    /**
     * Creates a successful response containing data.
     *
     * This method is identical to success method, providing an explicit way to specify return data.
     *
     * @param data Return data object, can be any type.
     * @return Successful Response object containing data, with status code 200 and message "success".
     */
    public static Response data(Object data) {
        return success(data);
    }

    /**
     * Creates a response with custom status code and message.
     *
     * @param code    Custom status code.
     * @param message Custom message.
     * @param data    Return data object.
     * @return Response object with specified parameters.
     */
    public static Response custom(int code, String message, Object data) {
        return new Response(code, message, data);
    }

    /**
     * Creates an error response from an exception.
     *
     * @param e Exception to convert to error response.
     * @return Error Response object with exception message or default error message.
     */
    public static Response fromException(Exception e) {
        return new Response(500, e.getMessage() != null ? e.getMessage() : "An unknown error occurred", null);
    }

}