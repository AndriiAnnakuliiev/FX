package com.example.computerstore.service;

import com.example.computerstore.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    @Autowired
    private JavaMailSender javaMailSender;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handleMessage(String message) {
        System.out.println("Received message: " + message);
        sendEmailNotification(message);
    }

    private void sendEmailNotification(String message) {
        //SimpleMailMessage mailMessage = new SimpleMailMessage();
        //mailMessage.setTo("andreiannakuliev82@gmail.com"); 
        //mailMessage.setSubject("Purchase Confirmation");
        //mailMessage.setText(message);

        //javaMailSender.send(mailMessage);
        System.out.println("Email sent with message: " + message);
    }
}
