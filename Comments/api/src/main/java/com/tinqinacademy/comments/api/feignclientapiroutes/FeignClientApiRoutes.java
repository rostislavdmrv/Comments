package com.tinqinacademy.comments.api.feignclientapiroutes;

import com.tinqinacademy.comments.api.restapiroutes.RestApiRoutes;

public class FeignClientApiRoutes {
    public static final String RETRIEVE_COMMENTS = "GET  " + RestApiRoutes.USER_RETRIEVE_ALL_COMMENTS +
            "?roomId={roomId}";
    public static final String LEAVE_COMMENT = "POST " + RestApiRoutes.USER_LEAVE_COMMENT;
    public static final String UPDATE_COMMENT_CONTENT = "PATCH " + RestApiRoutes.USER_UPDATE_COMMENT_CONTENT;
    public static final String UPDATE_COMMENT_BY_ADMIN = "PUT " + RestApiRoutes.ADMIN_UPDATE;
    public static final String DELETE_COMMENT = "DELETE " + RestApiRoutes.ADMIN_DELETE;
}
