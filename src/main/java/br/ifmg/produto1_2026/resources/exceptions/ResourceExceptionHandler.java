package br.ifmg.produto1_2026.resources.exceptions;

import br.ifmg.produto1_2026.services.exceptions.ResourceNotFound;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.swing.text.View;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {
    private final View error;

    public ResourceExceptionHandler(View error) {
        this.error = error;
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<StandartError> entityNotFound(ResourceNotFound e,
                                                        HttpServletRequest req) {
        StandartError error = new StandartError();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());
        error.setError("Registro não encontrado");
        error.setTimestamp(Instant.now());
        error.setPath(req.getRequestURI());


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }
}
