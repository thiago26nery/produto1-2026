package br.ifmg.produto1_2026.services;

import br.ifmg.produto1_2026.dto.EmailDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(@Valid EmailDto emailDto){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom();
        message.setTo(emailDto.getTo());
        message.setSubject(emailDto.getSubject());
        message.setText(emailDto.getBody());

        mailSender.send(message);
    }
}
