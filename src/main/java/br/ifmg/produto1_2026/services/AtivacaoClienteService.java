package br.ifmg.produto1_2026.services;

import br.ifmg.produto1_2026.util.NotificacaoEmail;
import br.ifmg.produto1_2026.entities.Usuario;
import br.ifmg.produto1_2026.util.Notificador;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtivacaoClienteService {

    private Notificador notificador;

    /*public AtivacaoClienteService(Notificador notificador) {
        System.out.println("Iniciando AtivacaoClienteService");

    }*/

    public void notificar(Usuario usuario, String mensagem){
       // System.out.printf("Notificando %s através do email $s: %s\n",usuario.getNome(), usuario.getEmail(), mensagem);

        notificador.notificar(usuario, mensagem);

    }

    //@Autowired
    public AtivacaoClienteService(Notificador notificador){
        System.out.println("Iniciando AtivacaoClienteService");
        this.notificador = notificador;
    }

    public AtivacaoClienteService(){
        System.out.println("Iniciando AtivacaoClienteService com o construtor sem parâmetros");
    }

    public void ativar(Usuario usuario, String mensagem){
        notificador.notificar(usuario, mensagem);

       /* for(Notificador notificador : notificadores){

        }*/
    }

    /*@PostConstruct
    public void init(){
        System.out.println("Metodo executado depois do construtor");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Metodo executado ao destruir oo construtor");
    }*/

    public Notificador getNotificador() {
        return notificador;
    }

    public void setNotificador(Notificador notificador){
        this.notificador = notificador;
    }
}
