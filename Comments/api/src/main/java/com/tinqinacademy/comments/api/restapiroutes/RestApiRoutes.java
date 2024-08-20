package com.tinqinacademy.comments.api.restapiroutes;

public class RestApiRoutes {

    private final static String API = "/api/v1";
    public final static String HOTEL = API + "/hotel";
    public final static String SYSTEM = API + "/system";
    public final static String COMMENT = "/comment";
    public final static String COMMENT_ID = "/{commentId}";
    public final static String ROOM_ID = "/{roomId}";

    public final static String RETRIEVE_ALL_COMMENTS = HOTEL + ROOM_ID + COMMENT;
    public final static String LEAVE_COMMENT = HOTEL + ROOM_ID + COMMENT;
    public final static String UPDATE_COMMENT_CONTENT = HOTEL + COMMENT_ID+ COMMENT;

    public static final String UPDATE_COMMENT = SYSTEM + COMMENT + COMMENT_ID;
    public static final String DELETE_COMMENT = SYSTEM + COMMENT + COMMENT_ID;


}
