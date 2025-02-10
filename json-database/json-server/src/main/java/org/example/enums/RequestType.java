package org.example.enums;

public enum RequestType {

    SET("set"),
    GET("get"),
    DELETE("delete"),
    EXIT("exit"),
    INVALID_REQUEST("invalid request");

    private final String requestType;

    RequestType(String requestType) {
        this.requestType = requestType;
    }

    private String getRequestType() {
        return this.requestType;
    }

    public static RequestType getRequest(String request) {
        for (RequestType type : RequestType.values()) {
            if (type.getRequestType().equals(request)) {
                return type;
            }
        }
        return INVALID_REQUEST;
    }

    public static String getRequest(RequestType type) {
        return (type.getRequestType() != null) ?
                type.getRequestType() : INVALID_REQUEST.getRequestType();
    }
}
