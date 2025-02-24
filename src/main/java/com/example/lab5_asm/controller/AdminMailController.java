package com.example.lab5_asm.controller;

import com.example.lab5_asm.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/admin/mail")
public class AdminMailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send")
    public String sendMail(@RequestParam String to, @RequestParam String subject,
                           @RequestParam String body,
                           @RequestParam(required = false) MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                mailService.sendEmailWithAttachment(to, subject, body, file);
                return "Email with attachment sent successfully!";
            } else {
                mailService.sendEmail(to, subject, body);
                return "Email sent successfully!";
            }
        } catch (MessagingException | IOException e) {
            return "Error sending email: " + e.getMessage();
        }
    }
}
