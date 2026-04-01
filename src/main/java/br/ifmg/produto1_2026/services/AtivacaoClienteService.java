package br.ifmg.produto1_2026.services;

import br.ifmg.produto1_2026.util.NotificacaoEmail;
import br.ifmg.produto1_2026.entities.Usuario;
import br.ifmg.produto1_2026.util.Notificador;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

@Service
public class AtivacaoClienteService {

    private Notificador modificador;

    public AtivacaoClienteService(NotificacaoEmail modificador) {
        System.out.println("Iniciando AtivacaoClienteService");

    }

    public void notificar(Usuario usuario, String mensagem){
       // System.out.printf("Notificando %s através do email $s: %s\n",usuario.getNome(), usuario.getEmail(), mensagem);

        modificador.notificar(usuario, mensagem);

    }

    @PostConstruct
    public void init(){
        System.out.println("Metodo executado depois do construtor");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Metodo executado ao destruir oo construtor");
    }

}
