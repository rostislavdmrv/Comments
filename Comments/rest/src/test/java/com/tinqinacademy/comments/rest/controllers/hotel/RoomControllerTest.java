package com.tinqinacademy.comments.rest.controllers.hotel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comments.api.interfaces.room.RoomService;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentInput;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentOutput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static com.tinqinacademy.comments.rest.restapiroutes.RestApiRoutes.USER_LEAVE_COMMENT;
import static com.tinqinacademy.comments.rest.restapiroutes.RestApiRoutes.USER_RETRIEVE_ALL_COMMENTS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RoomControllerTest {

    @Autowired
    private  MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RoomService roomService ;




    @Test
    void retrievesAllComments() throws Exception {
        String roomId ="12";
        LocalDate today = LocalDate.now();


        mockMvc.perform(get(USER_RETRIEVE_ALL_COMMENTS, roomId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        /*
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.comments[0].id").value("1"))
                .andExpect(jsonPath("$.comments[0].firstName").value("a"))
                .andExpect(jsonPath("$.comments[0].lastName").value("b"))
                .andExpect(jsonPath("$.comments[0].content").value("c"))
                .andExpect(jsonPath("$.comments[0].publishDate").value(today.toString()))
                .andExpect(jsonPath("$.comments[0].lastEditedDate").value(today.plusDays(1).toString()))
                .andExpect(jsonPath("$.comments[0].lastEditedBy").value("d"))
                .andExpect(jsonPath("$.comments[1].id").value("2"))
                .andExpect(jsonPath("$.comments[1].firstName").value("name2"))
                .andExpect(jsonPath("$.comments[1].lastName").value("name3"))
                .andExpect(jsonPath("$.comments[1].content").value("content content content content"))
                .andExpect(jsonPath("$.comments[1].publishDate").value(today.plusDays(5).toString()))
                .andExpect(jsonPath("$.comments[1].lastEditedDate").value(today.plusDays(10).toString()))
                .andExpect(jsonPath("$.comments[1].lastEditedBy").value("admin")*/


    }

    @Test
    public void testLeaveComment_whenValidInput_thenReturns200() throws Exception {

        LeaveCommentInput input = LeaveCommentInput.builder()
                .roomId("123")
                .firstName("Jon")
                .lastName("Doe")
                .content("This is a comment.")
                .build();
        LeaveCommentOutput output = LeaveCommentOutput.builder()
                .id("123")
                .build();

        when(roomService.leaveComment(any(LeaveCommentInput.class))).thenReturn(output);

        mockMvc.perform(post(USER_LEAVE_COMMENT, "123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123"));
    }

    @Test
    public void testLeaveComment_whenInvalidInput_thenReturns400() throws Exception{
        LeaveCommentInput input = LeaveCommentInput.builder()
                .roomId("")
                .firstName("")
                .lastName("")
                .content("")
                .build();

        mockMvc.perform(post(USER_LEAVE_COMMENT, "roomId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void leaveComment() {
    }

    @Test
    void updateContentComment() {
    }
}