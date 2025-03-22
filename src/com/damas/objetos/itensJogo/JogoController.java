package com.damas.objetos.itensJogo;

import java.util.ArrayList;

public class JogoController {
    private Tabuleiro tabuleiro;
    private Jogador jogadorUm;
    private Jogador jogadorDois;
    private ControllerpermitMoves movimentador;
    private ArrayList<Casa> pecasAComer = new ArrayList<Casa>();

    private Jogo jogo;
    private RegraJogo regraJogo;

    public JogoController(Jogo jogo, Tabuleiro tabuleiro, Jogador jogadoUm, Jogador jogadorDois) {
        this.tabuleiro = tabuleiro;
        this.jogo = jogo;
        this.jogadorUm = jogadoUm;
        this.jogadorDois = jogadorDois;
        this.regraJogo = new RegraJogo(pecasAComer, tabuleiro, jogo, this);
        this.movimentador = new ControllerpermitMoves(pecasAComer, tabuleiro, this);
    }

    public void moverPeca(int origemX, int origemY, int destinoX, int destinoY) {
        movimentador.moverPeca(origemX, origemY, destinoX, destinoY);
    }

    public void trocarDeVez() {
        if (getVez() == 1) {
            setVez(2);
        } else {
            setVez(1);
        }
    }

    public boolean simularMovimentoEValidar(Casa origem, Casa destino) {
        return regraJogo.simularMovimentoEValidar(origem, destino);
    }

    public boolean deveContinuarJogando(Casa origem) {
        return regraJogo.deveContinuarJogando(origem);
    }

    public int getGanhador() {
        if (jogadorUm.getPontos() >= 12)
            return 1;
        if (jogadorDois.getPontos() >= 12)
            return 2;
        return 0;
    }

    public int getJogadasSemComerPeca() {
        return movimentador.getJogadasSemComerPeca();
    }

    public int getJogadas() {
        return movimentador.getJogadas();
    }

    public Jogador getJogadorUm() {
        return this.jogadorUm;
    }

    public Jogador getJogadorDois() {
        return this.jogadorDois;
    }

    public int getVez() {
        return jogo.getVez();
    }

    public void setVez(int vez) {
        jogo.setVez(vez);
    }

    public Casa getCasaBloqueadaOrigem() {
        return movimentador.getCasaBloqueadaOrigem();
    }

}