package com.damas.objetos.itensJogo;

/**
 * Armazena o tabuleiro e responsavel por posicionar as pecas.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 * @author João Victor da S. Cirilo {@link joao.cirilo@academico.ufpb.br}
 * @author José Alisson Rocha da Silva {@link jose.alisson2@academico.ufpb.br}
 */
public class Jogo {

    private Tabuleiro tabuleiro;
    private Jogador jogadorUm;
    private Jogador jogadorDois;
    private int vezAtual = 1;
    private JogoController jogoController;

    public Jogo() {
        tabuleiro = new Tabuleiro();
        jogadorUm = new Jogador("player branco");
        jogadorDois = new Jogador("player vermelho");
        jogoController = new JogoController(this, tabuleiro, jogadorUm, jogadorDois);

    }

    public void moverPeca(int origemX, int origemY, int destinoX, int destinoY) {
        jogoController.moverPeca(origemX, origemY, destinoX, destinoY);
    }

    public int getGanhador() {
        return jogoController.getGanhador();
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void setJogadorUm(Jogador jogador) {
        jogadorUm = jogador;
    }

    public void setJogadorDois(Jogador jogador) {
        jogadorDois = jogador;
    }

    public Jogador getJogadorUm() {
        return jogadorUm;
    }

    public Jogador getJogadorDois() {
        return jogadorDois;
    }

    public int getJogadasSemComerPecas() {
        return jogoController.getJogadasSemComerPeca();
    }

    public int getJogada() {
        return jogoController.getJogadas();
    }

    public JogoController getController() {
        return this.jogoController;
    }

    public int getVez() {
        return this.vezAtual;
    }

    public void setVez(int vez) {
        this.vezAtual = vez;
    }

}
