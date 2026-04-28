package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.dto.EmailDto;
import br.ifmg.produto1_2026.services.EmailService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("/email")
public class EmailResource {
    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> sendEmail(@Valid @RequestBody Email email){

        emailService.sendMail(EmailDto);
        return ResponseEntity.ok().build();
    }
}
