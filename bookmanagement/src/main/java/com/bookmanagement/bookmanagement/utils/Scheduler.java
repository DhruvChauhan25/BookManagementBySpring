package com.bookmanagement.bookmanagement.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private final EmailAspect emailAspect;

    @Autowired
    public Scheduler(EmailAspect emailAspect) {
        this.emailAspect = emailAspect;
    }

    @Scheduled(fixedRate = 60000)
    public void triggerEmailAspect() {
        emailAspect.sendEmail("dhruv@gmail.com", "Scheduled Email", "This is a scheduled email.");
    }
}
