package br.ifmg.produto1_2026.config;

import br.ifmg.produto1_2026.util.NotificacaoEmail;
import br.ifmg.produto1_2026.util.Notificador;
import org.springframework.context.annotation.Bean;

public class NotificadorConfig {
/*
    //nessa caso, a criação do bean é necessária,
    //pois o Spring Boot NÃO saberia criar esse objeto.
    //Qual seria o servidor smtp??
    @Bean
    public Notificador notificacaoEmail(){
        NotificacaoEmail notificacaoEmail = new NotificacaoEmail("smtp.google.com");
        notificacaoEmail.setCaixaAlta(true);

        return notificacaoEmail;
    }
    */
}
