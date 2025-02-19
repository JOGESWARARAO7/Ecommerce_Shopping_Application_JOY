package com.example.mainproject.joy.security;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import jakarta.mail.MessagingException;

@Service
public class EmailCheck {

    @Autowired
    private JavaMailSender mailSender;

    public ResponseEntity<String> sendSimpleEmail(String toemail, int otp, String body, String subjectString, String imagePath) {
        try {
            jakarta.mail.internet.MimeMessage message = mailSender.createMimeMessage();
            org.springframework.mail.javamail.MimeMessageHelper helper = new org.springframework.mail.javamail.MimeMessageHelper(message, true); // true indicates multipart mode

            // Set basic email information
            helper.setFrom("prasanna.vsp80@gmail.com");
            helper.setTo(toemail);
            helper.setSubject(subjectString);

            // Set the body of the email with the OTP
            String fullBody = body + "\n\nYour OTP is: " + otp;
            helper.setText(fullBody);

            // Load the image from the classpath and attach it
            File image = null;
			try {
				image = ResourceUtils.getFile("classpath:" + imagePath);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Assuming image is in resources folder
            helper.addAttachment("image.jpg", image); // Attach the image as "image.jpg"

            // Send the email
            mailSender.send(message);
            return ResponseEntity.ok("OTP Sent with Image");
        } catch (MessagingException | MailException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to send email with image: " + e.getMessage());
        }
    }

    public String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generate a 6-digit OTP
        return String.valueOf(otp);
    }
}
