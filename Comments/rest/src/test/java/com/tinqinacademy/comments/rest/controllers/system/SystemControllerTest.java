package com.tinqinacademy.comments.rest.controllers.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comments.api.operations.deletecommentbyadmin.DeleteCommentInput;
import com.tinqinacademy.comments.api.operations.editcommentallbyadmin.EditCommentAllInput;
import com.tinqinacademy.comments.api.operations.editcommentcontentbyuser.EditCommentContentInput;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentInput;
import com.tinqinacademy.comments.api.restapiroutes.RestApiRoutes;
import com.tinqinacademy.comments.persistence.models.entities.Comment;
import com.tinqinacademy.comments.persistence.repositories.CommentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import java.util.UUID;


import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2)
class SystemControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CommentRepository commentRepository;

    @BeforeEach
    public void setup() {
        Comment comment = Comment.builder()
                .roomId(UUID.randomUUID())
                .userId(UUID.randomUUID())
                .content("This room is soo cool!")
                .publishDate(LocalDateTime.now())
                .lastEditedDate(LocalDateTime.now())
                .lastEditedBy(UUID.randomUUID())
                .build();

        commentRepository.save(comment);

    }

    @AfterEach
    public void afterEach() {
        commentRepository.deleteAll();
    }


    @Test
    void retrieveAllCommentsOfRoomOk() throws Exception {
        String roomId = commentRepository.findAll().get(0).getRoomId().toString();

        mvc.perform(get(RestApiRoutes.RETRIEVE_ALL_COMMENTS,roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void retrieveAllCommentsOfRoomNotFound() throws Exception {
        String roomId = commentRepository.findAll().get(0).getRoomId().toString();

        mvc.perform(get(RestApiRoutes.RETRIEVE_ALL_COMMENTS+"/wrong",roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound());
    }

    @Test
    void leaveCommentReturnsCreated() throws Exception {
        String roomId = commentRepository.findAll().getFirst().getRoomId().toString();
        String userId = commentRepository.findAll().getFirst().getUserId().toString();

        LeaveCommentInput input = LeaveCommentInput.builder()
                .roomId(roomId)
                .userId(userId)
                .content("This room is soo cool!")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(post(RestApiRoutes.LEAVE_COMMENT, roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isCreated());
    }

    @Test
    void leaveCommentForRoomBadRequest() throws Exception {
        String roomId = commentRepository.findAll().getFirst().getRoomId().toString();
        String userId = commentRepository.findAll().getFirst().getUserId().toString();

        LeaveCommentInput input = LeaveCommentInput.builder()
                .roomId(roomId)
                .userId(userId)
                .content("")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(post(RestApiRoutes.LEAVE_COMMENT,roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void leaveCommentForRoomNotFound() throws Exception {
        String roomId = "1111";
        String userId = commentRepository.findAll().getFirst().getUserId().toString();

        LeaveCommentInput input = LeaveCommentInput.builder()
                .roomId(roomId)
                .userId(userId)
                .content("This room is soo cool!")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(post(RestApiRoutes.LEAVE_COMMENT+"/more",roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound());
    }

    @Test
    void editCommentContentForRoomOk() throws Exception {
        String commentId = commentRepository.findAll().getFirst().getId().toString();
        String userId = commentRepository.findAll().getFirst().getUserId().toString();

        EditCommentContentInput input = EditCommentContentInput.builder()
                .commentId(commentId)
                .content("The room was cozy, shiny and large.")
                .userId(userId)
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(patch(RestApiRoutes.UPDATE_COMMENT_CONTENT,commentId)
                        .contentType("application/json-patch+json")
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(commentId));
    }

    @Test
    void editCommentForRoomOk() throws Exception {
        String commentId = commentRepository.findAll().getFirst().getId().toString();
        String roomId = commentRepository.findAll().getFirst().getRoomId().toString();
        String userId = commentRepository.findAll().getFirst().getUserId().toString();
        String newContent = "The room was large.";

        EditCommentAllInput input = EditCommentAllInput.builder()
                .commentId(commentId)
                .roomId(roomId)
                .userId(userId)
                .content(newContent)
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(put(RestApiRoutes.UPDATE_COMMENT,commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(commentId));

        Comment updatedComment = commentRepository.findById(UUID.fromString(commentId)).get();
        assertEquals(newContent,updatedComment.getContent());
    }

    @Test
    void editCommentForRoomBadRequest() throws Exception {
        String commentId = commentRepository.findAll().getFirst().getId().toString();
        String roomId = "452-fd-51";
        String userId = commentRepository.findAll().getFirst().getUserId().toString();

        EditCommentAllInput input = EditCommentAllInput.builder()
                .commentId(commentId)
                .roomId(roomId)
                .userId(userId)
                .content("The room was large.")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(put(RestApiRoutes.UPDATE_COMMENT,commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest());
    }


    @Test
    void deleteCommentForRoomOk() throws Exception {
        String commentId = commentRepository.findAll().getFirst().getId().toString();

        mvc.perform(delete(RestApiRoutes.DELETE_COMMENT,commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCommentForRoomNotFound() throws Exception {
        String commentId = commentRepository.findAll().getFirst().getId().toString();

        mvc.perform(delete(RestApiRoutes.DELETE_COMMENT+"/wrong",commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound());
    }




    @Test
    void editCommentContentForRoomNotFound() throws Exception {
        String commentId = "sbjbdjcvjvc";

        EditCommentContentInput input = EditCommentContentInput.builder()
                .commentId(commentId)
                .content("Recommended!")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(patch(RestApiRoutes.UPDATE_COMMENT_CONTENT+"/more",commentId)
                        .contentType("application/json-patch+json")
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound());
    }



    @Test
    void updateComment() {
    }

    @Test
    public void testDeleteComment() throws Exception {

        DeleteCommentInput deleteCommentInput = DeleteCommentInput.builder()
                .commendId("1234112d-6611-4562-a2db-ee1e4f4c3123")
                .build();


        mvc.perform(delete(RestApiRoutes.DELETE_COMMENT,deleteCommentInput.getCommendId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(deleteCommentInput)))
                .andExpect(status().isNotFound());

        assert (commentRepository.findById(UUID.fromString("1234112d-6611-4562-a2db-ee1e4f4c3123")).isEmpty());
    }

    @Test
    void testEditCommentReturnsOk() throws Exception {
        String commentId = "1234112d-6611-4562-a2db-ee1e4f4c3123";
        String roomId = "5a76112d-6611-4562-a2db-ee1e4f4c3894";
        String userId = "56fc4621-a5f4-442b-82bb-cdd532ba4d33";

        EditCommentAllInput editCommentInput = EditCommentAllInput.builder()
                .commentId(commentId)
                .roomId(roomId)
                .userId(userId)
                .content("Updated content!")
                .build();

        String serializedInput = mapper.writeValueAsString(editCommentInput);

        mvc.perform(put(RestApiRoutes.UPDATE_COMMENT, commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.content").value("Updated content!"));

        Comment updatedComment = commentRepository.findById(UUID.fromString(commentId)).orElseThrow();
        assertEquals("Updated content!", updatedComment.getContent());
    }


    @Test
    void editCommentReturnsBadRequest() throws Exception {
        String commentId = commentRepository.findAll().getFirst().getId().toString();
        String userId = commentRepository.findAll().getFirst().getUserId().toString();

        EditCommentAllInput input = EditCommentAllInput.builder()
                .commentId(commentId)
                .userId(userId)
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(put(RestApiRoutes.UPDATE_COMMENT, commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest());
    }

}