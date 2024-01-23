package com.bookmanagement.bookmanagement.Controller;

import com.bookmanagement.bookmanagement.Entity.EmailRequest;
import com.bookmanagement.bookmanagement.Service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/books")
@Tag(name = "Email Checker", description = "Endpoints to retrieve information about Email")
@RestController
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }
    @Operation(summary = "To send mail ", description = "Get a list of all mail", tags = {"Email Checker"})
    @PostMapping("/send-email")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        String to = emailRequest.getTo();
        String subject = emailRequest.getSubject();
        String body = emailRequest.getBody();

        emailService.sendSimpleMessage(to, subject, body);

        return "Email sent successfully!";
    }
}