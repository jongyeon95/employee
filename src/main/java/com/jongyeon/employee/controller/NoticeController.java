package com.jongyeon.employee.controller;

import com.jongyeon.employee.domain.Notice;
import com.jongyeon.employee.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class NoticeController {

    //TODO paging

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/notice")
    public List<Notice> list(){
        List<Notice> list = noticeService.getNoticeList();
        return list;
    }

    @GetMapping("/notice/{id}")
    public Notice getNotice(@PathVariable("id") Long id){
        Notice notice=noticeService.getNotice(id);
        return notice;
    }

    //TODO 후에 Admin 처리 할것
    @PostMapping("/notice")
    public ResponseEntity<?> addNotice(@RequestBody Notice resource) throws URISyntaxException {
        Notice notice = Notice.builder()
                .writer("Admin")
                .updatedTime(LocalDate.now())
                .createdTime(LocalDate.now())
                .content(resource.getContent())
                .title(resource.getTitle())
                .build();
        noticeService.addNotice(notice);
        URI location=new URI("/notice/"+notice.getId());
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/notice/{id}")
    public Notice updateNotice(@RequestBody Notice resource,@PathVariable("id") Long id){
        return noticeService.updateNotice(id,resource);
    }
}
