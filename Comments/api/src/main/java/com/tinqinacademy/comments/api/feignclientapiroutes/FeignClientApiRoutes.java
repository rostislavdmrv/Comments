package com.tinqinacademy.comments.api.feignclientapiroutes;

import com.tinqinacademy.comments.api.restapiroutes.RestApiRoutes;

public class FeignClientApiRoutes {
    public static final String RETRIEVE_COMMENTS = "GET  " + RestApiRoutes.RETRIEVE_ALL_COMMENTS;
    public static final String LEAVE_COMMENT = "POST " + RestApiRoutes.LEAVE_COMMENT;
    public static final String UPDATE_COMMENT_CONTENT = "PATCH " + RestApiRoutes.UPDATE_COMMENT_CONTENT;
    public static final String UPDATE_COMMENT_BY_ADMIN = "PUT " + RestApiRoutes.UPDATE_COMMENT;
    public static final String DELETE_COMMENT = "DELETE " + RestApiRoutes.DELETE_COMMENT;
}
