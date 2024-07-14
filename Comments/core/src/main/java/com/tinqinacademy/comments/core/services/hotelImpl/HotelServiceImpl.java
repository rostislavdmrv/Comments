package com.tinqinacademy.comments.core.services.hotelImpl;

import com.tinqinacademy.comments.api.interfaces.room.RoomService;
import com.tinqinacademy.comments.api.operations.editcommentbyuser.EditCommentContentInput;
import com.tinqinacademy.comments.api.operations.editcommentbyuser.EditCommentContentOutput;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentInput;
import com.tinqinacademy.comments.api.operations.leavescommentsforcertainroom.LeaveCommentOutput;
import com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom.CommentOutput;
import com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom.ReturnCommentInput;
import com.tinqinacademy.comments.api.operations.returnsallcommentsforcertainroom.ReturnCommentOutput;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class HotelServiceImpl implements RoomService {



    @Override
    public ReturnCommentOutput retrievesAllComments(ReturnCommentInput input) {
        log.info("Start retrieving all the comments for certain room");

        List<CommentOutput> randomComments = generateRandomComments();

        ReturnCommentOutput output = ReturnCommentOutput.builder().comments(randomComments).build();
        log.info("End retrieving all the comments for certain room");

        return output;
    }



    private List<CommentOutput> generateRandomComments(){
        List<CommentOutput> randomComments = new ArrayList<>();
        LocalDate today = LocalDate.now();

        CommentOutput randomComment1 = CommentOutput.builder()
                .id("1")
                .firstName("a")
                .lastName("b")
                .content("c")
                .publishDate(today)
                .lastEditedDate(today.plusDays(1))
                .lastEditedBy("d")
                .build();

        CommentOutput randomComment2 = CommentOutput.builder()
                .id("2")
                .firstName("name2")
                .lastName("name3")
                .content("content content content content")
                .publishDate(today.plusDays(5))
                .lastEditedDate(today.plusDays(10))
                .lastEditedBy("admin")
                .build();

        randomComments.add(randomComment1);
        randomComments.add(randomComment2);

        return randomComments;
    }

    @Override
    public LeaveCommentOutput leaveComment(LeaveCommentInput input) {

        log.info("Start leaving comment");
        LeaveCommentOutput output = LeaveCommentOutput.builder().id(input.getRoomId()).build();
        log.info("End leaving comment");

        return output;
    }

    @Override
    public EditCommentContentOutput updateContentComment(EditCommentContentInput input) {
        log.info("Start updating content comment");
        EditCommentContentOutput output = EditCommentContentOutput.builder().id(input.getContentId()).build();
        log.info("End updating content comment");
        return output;
    }


}
