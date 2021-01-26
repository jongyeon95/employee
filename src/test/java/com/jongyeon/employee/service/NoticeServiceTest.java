package com.jongyeon.employee.service;

import com.jongyeon.employee.domain.Notice;
import com.jongyeon.employee.repository.NoticeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class NoticeServiceTest {

    NoticeService noticeService;

    @Mock
    NoticeRepository noticeRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        noticeService=new NoticeService(noticeRepository);
    }

    @Test
    public void getNoticeList(){
        List<Notice> MockList = new ArrayList<>();
        MockList.add(Notice.builder().title("test").writer("tester").content("test").build());
        given(noticeRepository.findAll()).willReturn(MockList);
        List<Notice> list=noticeService.getNoticeList();
        assertThat(list.get(0).getTitle(),is("test"));
    }

    @Test
    public void getNotice(){
        Notice MockNotice = Notice.builder().id(1L).title("test").build();
        given(noticeRepository.findById(1L)).willReturn(Optional.of(MockNotice));
        Notice notice=noticeService.getNotice(1L);
        assertThat(notice.getTitle(),is("test"));
    }

    @Test
    public void addNotice(){
        Notice MockNotice = Notice.builder().id(1L).title("test").build();
        noticeService.addNotice(MockNotice);
        verify(noticeRepository).save(any());
    }

    @Test
    public void updateNotice(){
        Notice MockNotice = Notice.builder().id(1L).title("test").build();
        given(noticeRepository.findById(1L)).willReturn(Optional.of(MockNotice));
        Notice changeNotice=Notice.builder().content("change").title("change").build();
        given(noticeRepository.save(any())).willReturn(changeNotice);
        Notice notice = noticeService.updateNotice(1L,changeNotice);
        assertThat(notice.getTitle(),is("change"));
    }

}