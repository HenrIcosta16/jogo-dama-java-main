package com.damas.objetos.itensJogo;

import java.util.ArrayList;

public class ControllerpermitMoves {
    private Tabuleiro tabuleiro;
    private JogoController jogoController;
    private Casa casaBloqueadaOrigem = null;
    private int jogadas = 0;
    private int jogadasSemComerPeca = 0;
    private ArrayList<Casa> pecasAComer;

    public ControllerpermitMoves(ArrayList<Casa> pecasAComer, Tabuleiro tabuleiro, JogoController jogoController) {
        this.tabuleiro = tabuleiro;
        this.jogoController = jogoController;
        this.pecasAComer = pecasAComer;
    }

    public void moverPeca(int origemX, int origemY, int destinoX, int destinoY) {
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);
        Peca peca = origem.getPeca();

        if (casaBloqueadaOrigem == null && isJogadorCorreto(peca)) {
            moverPecaSeValido(origem, destino, peca);
        } else {
            moverPecaComCasaBloqueada(origem, destino, origemX, origemY, destinoX, destinoY);
        }
    }

    private boolean isJogadorCorreto(Peca peca) {
        return (jogoController.getVez() == 1
                && (peca.getTipo() == Pedra.PEDRA_BRANCA || peca.getTipo() == Pedra.DAMA_BRANCA)) ||
                (jogoController.getVez() == 2
                        && (peca.getTipo() == Pedra.PEDRA_VERMELHA || peca.getTipo() == Pedra.DAMA_VERMELHA));
    }

    private void moverPecaSeValido(Casa origem, Casa destino, Peca peca) {
        if (peca.isMovimentoValido(destino) &&
                jogoController.simularMovimentoEValidar(origem, destino)) {
            peca.moverdestino(destino);
            processarResultadoMovimento(destino);
        }
    }

    private void moverPecaComCasaBloqueada(Casa origem, Casa destino, int origemX, int origemY, int destinoX,
            int destinoY) {
        if (origem.equals(casaBloqueadaOrigem) && jogoController.simularMovimentoEValidar(origem, destino)
                && !pecasAComer.isEmpty()) {
            casaBloqueadaOrigem = null;
            moverPeca(origemX, origemY, destinoX, destinoY);
        }
    }

    private void processarResultadoMovimento(Casa destino) {
        destino.getPeca().processarResultadoDeMovimento(destino, pecasAComer, jogoController, jogadasSemComerPeca,
                jogadas);
        // if (!pecasAComer.isEmpty()) {
        // comerPecas(destino);
        // if (jogoController.deveContinuarJogando(destino)) {
        // casaBloqueadaOrigem = destino;
        // } else {
        // jogoController.trocarDeVez();
        // }
        // } else {
        // jogadasSemComerPeca++;
        // jogoController.trocarDeVez();
        // }

        // jogadas++;
        // if (podeTransformarParaDama(destino)) {
        // destino.getPeca().transformarDama(destino, jogadas);
        // }
    }

    private void comerPecas(Casa casa) {
        casa.getPeca().ComerPecaComEstilo(pecasAComer, jogoController, jogadasSemComerPeca);
        jogadasSemComerPeca = 0;
        // private void comerPecas(Casa casa) {
        // int pecasComidas = pecasAComer.size();

        // if (jogoController.getVez() == 1)
        // jogoController.getJogadorUm().addPonto(pecasComidas);
        // if (jogoController.getVez() == 2)
        // jogoController.getJogadorDois().addPonto(pecasComidas);

        // for (Casa casa : pecasAComer) {
        // casa.removerPeca();
        // }

        // pecasAComer.removeAll(pecasAComer);

        // jogadasSemComerPeca = 0;
    }

    // private boolean podeTransformarParaDama(Casa casa) {

    private boolean podeTransformarParaDama(Casa casa) {
        return casa.getPeca().podeSerDama();

        // private boolean podeTransformarParaDama(Casa casa) {

        // REGRA PARA PEÇAS BRANCAS
        // if (casa.getPeca().getTipo() == Peca.PEDRA_BRANCA && casa.getY() == 7)
        // return true;

        // REGRA PARA PEÇAS VERMELHAS
        // if (casa.getPeca().getTipo() == Peca.PEDRA_VERMELHA && casa.getY() == 0)
        // return true;

        // return false;
    }

    // private void transformarPedraParaDama(Casa casa) {
    // Peca pedra = casa.getPeca();

    // if (pedra.getTipo() == Peca.PEDRA_BRANCA) {
    // pedra = new DamaBranca(casa, jogadas);
    // } else {
    // pedra = new DamaVermelha(casa, jogadas);
    // }

    // casa.setPeca(pedra); // Atualiza a peça na casa
    // }

    public Casa getCasaBloqueadaOrigem() {
        return this.casaBloqueadaOrigem;
    }

    public int getJogadas() {
        return this.jogadas;
    }

    public int getJogadasSemComerPeca() {
        return this.jogadasSemComerPeca;
    }
}
