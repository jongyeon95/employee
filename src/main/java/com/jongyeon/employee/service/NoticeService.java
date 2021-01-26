package com.jongyeon.employee.service;

import com.jongyeon.employee.domain.Notice;
import com.jongyeon.employee.exception.NoticeNotFoundException;
import com.jongyeon.employee.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeService {

    NoticeRepository noticeRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository){
        this.noticeRepository=noticeRepository;
    }

    public List<Notice> getNoticeList(){
        List<Notice> list = noticeRepository.findAll();
        return list;
    }

    public Notice getNotice(Long id){
        Notice notice = noticeRepository.findById(id).orElseThrow(()->new NoticeNotFoundException(id));
        return notice;
    }

    public Notice addNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    public Notice updateNotice(Long id,Notice resource) {
        Notice notice = noticeRepository.findById(id).orElseThrow(()->new NoticeNotFoundException(id));
        notice.setContent(resource.getContent());
        notice.setTitle(resource.getTitle());
        notice.setUpdatedTime(LocalDate.now());
        return noticeRepository.save(notice);
    }
}
