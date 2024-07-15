package com.tinqinacademy.comments.rest.restapiroutes;

public class RestApiRoutes {

    private final static String API = "api/v1";
    public final static String API_HOTEL = API + "/hotel";
    public final static String API_SYSTEM = API + "/system";
    public final static String COMMENT = "/comment";

    public final static String USER_RETRIEVE_ALL_COMMENTS = API_HOTEL + "/{roomId}" + COMMENT;
    public final static String USER_LEAVE_COMMENT = API_HOTEL + "/{roomId}" + COMMENT;
    public final static String USER_UPDATE_COMMENT_CONTENT = API_HOTEL + "/{commentId}/comment";

    public static final String ADMIN_UPDATE = API_SYSTEM + COMMENT + "/{commentId}";
    public static final String ADMIN_DELETE = API_SYSTEM + COMMENT + "/{commentId}";


}
