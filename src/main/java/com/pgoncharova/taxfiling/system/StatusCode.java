package com.pgoncharova.taxfiling.system;

public class StatusCode {

    public static final int SUCCESS = 200;

    public static final int INVALID_ARGUMENT = 400; // Bad request (eg. invalid parameters)

    public static final int UNAUTHORIZED = 401; // Username or password incorrect

    public static final int FORBIDDEN = 403; // No permission

    public static final int NOT_FOUND = 404;

    public static final int INTERNAL_SERVER_ERROR = 500;

}
