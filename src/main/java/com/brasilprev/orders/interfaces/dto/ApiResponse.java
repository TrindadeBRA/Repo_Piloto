package com.brasilprev.orders.interfaces.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Standard envelope for all API responses.
 */
@Schema(description = "Standard response wrapper for all API endpoints")
public class ApiResponse<T> {

    @Schema(description = "Indicates if the operation was successful", example = "true")
    private boolean success;
    
    @Schema(description = "The actual data returned by the operation")
    private T data;
    
    @Schema(description = "Error message if operation failed", example = "Order not found")
    private String message;

    private ApiResponse(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, null, message);
    }

    public boolean isSuccess() { return success; }
    public T getData() { return data; }
    public String getMessage() { return message; }
}