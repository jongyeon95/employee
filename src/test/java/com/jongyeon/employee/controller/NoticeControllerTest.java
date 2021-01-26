package com.jongyeon.employee.controller;

import com.jongyeon.employee.domain.Notice;
import com.jongyeon.employee.service.NoticeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(NoticeController.class)
public class NoticeControllerTest {


    @Autowired
    MockMvc mvc;

    @MockBean
    NoticeService noticeService;

    @Test
    public void list() throws Exception {
        List<Notice> MockList = new ArrayList<>();
        MockList.add(Notice.builder().title("test").writer("tester").content("test").build());
        given(noticeService.getNoticeList()).willReturn(MockList);
        mvc.perform(get("/notice"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test")));
    }

    @Test
    public void getNotice() throws Exception {
        Notice MockNotice = Notice.builder().id(1L).title("test").build();
        given(noticeService.getNotice(1L)).willReturn(MockNotice);
        mvc.perform(get("/notice/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test")));
    }

    @Test
    public void addNotice() throws Exception {
        mvc.perform(post("/notice")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"content\":\"test content\",\"title\":\"test title\"}"))
                .andExpect(status().isCreated());

    }

    @Test
    public void updateNotice() throws Exception{
        Notice MockNotice=Notice.builder().title("change").content("change").build();
        given(noticeService.updateNotice(1L,MockNotice))
                .willReturn(Notice.builder().title("change").content("change").build());
        mvc.perform(patch("/notice/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"content\":\"change\",\"title\":\"change\"}"))
                .andExpect(content().string(containsString("change")));

        verify(noticeService).updateNotice(eq(1L),any());

    }

}