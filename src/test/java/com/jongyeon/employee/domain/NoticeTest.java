package com.jongyeon.employee.domain;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isNotNull;

public class NoticeTest {

    @Test
    public void create(){
        Notice notice= Notice.builder()
                .title("test title")
                .content("content test")
                .createdTime(LocalDate.now())
                .updatedTime(LocalDate.now())
                .writer("Admin")
                .build();
        assertThat(notice.getTitle(),is("test title"));
    }

}