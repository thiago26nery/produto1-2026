package br.ifmg.produto1_2026.util;

import br.ifmg.produto1_2026.entities.Usuario;
import org.springframework.stereotype.Component;

//@Component
public class NotificacaoSMS implements Notificador {

    private boolean caixaAlta;
    private String servidorSmpt;


    public NotificacaoSMS(String servidorSmpt) {
        System.out.println("Notificacao email com sucesso");
        this.servidorSmpt = servidorSmpt;

    }

    public void notificar(Usuario usuario, String mensagem) {
        if (caixaAlta) {
            mensagem = mensagem.toUpperCase();
        }

        System.out.printf("Notificando %s através do telefone %s no servidor %s: \n",usuario.getNome(), usuario.getTelefone(), servidorSmpt ,mensagem);

    }

    public boolean isCaixaAlta() {
        return caixaAlta;
    }

    public void setCaixaAlta(boolean caixaAlta) {
        this.caixaAlta = caixaAlta;
    }

    public String getServidorSmpt() {
        return servidorSmpt;
    }

    public void setServidorSmpt(String servidorSmpt) {
        this.servidorSmpt = servidorSmpt;
    }
}
