package br.ifmg.produto1_2026.config;

import br.ifmg.produto1_2026.services.AtivacaoClienteService;
import br.ifmg.produto1_2026.util.Notificador;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

//@Configurable
public class AtivadorConfig {
    /*
    // nesse caso, a criação do bean não seria necessária
    // pois o Spring Boot saberia criar esse objeto.
    @Bean
    public AtivacaoClienteService atividacaoClienteService(Notificador notificador){
        AtivacaoClienteService atividacaoClienteService = new AtivacaoClienteService(notificador);
        return atividacaoClienteService;
    }
     */

}
