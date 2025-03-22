package com.damas.objetos.itensJogo;

import java.util.ArrayList;

/**
 * Representa uma Peça do jogo.
 * Possui uma casa e um tipo associado.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 * @author José Alisson Rocha da Silva {@link jose.alisson2@academico.ufpb.br}
 */
public abstract class Pedra implements Peca {

    protected Casa casa;
    protected int tipo;

    public Pedra(Casa casa, int tipo) {
        this.casa = casa;
        this.tipo = tipo;
        casa.colocarPeca(this);
    }

    public boolean isMovimentoValido(Casa destino) {

        // SENTIDO UNITÁRIO E DISTANCIA X E Y DA CASA ATUAL ATÉ A CASA DE DESTINO
        int distanciaX = Math.abs(destino.getX() - casa.getX());
        int distanciaY = Math.abs(destino.getY() - casa.getY());

        if ((distanciaX == 0) || (distanciaY == 0))
            return false;

        // REGRA DE MOVIMENTO NO CASO DA DISTÂNCIA SER DE 2 CASAS (MOVIMENTO DE COMER
        // PEÇA)
        if ((distanciaX <= 2 || distanciaY <= 2) && (distanciaX == distanciaY)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean mover(Casa origem, Casa destino) {
        // SENTIDO UNITÁRIO E DISTANCIA X E Y DA CASA ATUAL ATÉ A CASA DE DESTINO
        int distanciaX = Math.abs(destino.getX() - origem.getX());
        int distanciaY = Math.abs(destino.getY() - origem.getY());

        if ((distanciaX == 0) || (distanciaY == 0))
            return false;

        // REGRA DE MOVIMENTO NO CASO DA DISTÂNCIA SER DE 2 CASAS (MOVIMENTO DE COMER
        // PEÇA)
        if ((distanciaX <= 2 || distanciaY <= 2) && (distanciaX == distanciaY)) {
            return true;
        }

        return false;
    }

    @Override
    public int getTipo() {
        return tipo;

    }

    @Override
    public void transformarDama(Casa casa, int jogadas) {
        if (this.getTipo() == Pedra.PEDRA_BRANCA) {
            casa.setPeca(new DamaBranca(casa, jogadas));
        } else if (this.getTipo() == Pedra.PEDRA_VERMELHA) {
            casa.setPeca(new DamaVermelha(casa, jogadas));
        }
    }

    @Override
    public boolean podeSerDama() {
        // REGRA PARA PEÇAS BRANCAS
        if (casa.getPeca().getTipo() == Peca.PEDRA_BRANCA && casa.getY() == 7)
            return true;

        // REGRA PARA PEÇAS VERMELHAS
        if (casa.getPeca().getTipo() == Peca.PEDRA_VERMELHA && casa.getY() == 0)
            return true;

        return false;

    }

    @Override
    public void ComerPecaComEstilo(ArrayList<Casa> pecasAComer, JogoController jogoController,
            int jogadasSemComerPeca) {
        int pecasComidas = pecasAComer.size();

        if (jogoController.getVez() == 1)
            jogoController.getJogadorUm().addPonto(pecasComidas);
        if (jogoController.getVez() == 2)
            jogoController.getJogadorDois().addPonto(pecasComidas);

        for (Casa casa : pecasAComer) {
            casa.removerPeca();
        }

        pecasAComer.removeAll(pecasAComer);

        jogadasSemComerPeca = 0;
    }

    @Override
    public void processarResultadoDeMovimento(Casa destino, ArrayList<Casa> pecasAComer, JogoController jogoController,
            int jogadasSemComerPeca, int jogadas) {
        if (!pecasAComer.isEmpty()) {
            // Lógica de comer peça com estilo
            ComerPecaComEstilo(pecasAComer, jogoController, jogadasSemComerPeca);
            jogadasSemComerPeca = 0;

            // Verifica se o jogo deve continuar ou se troca de vez
            if (jogoController.deveContinuarJogando(destino)) {
                // Lógica para continuar o jogo (ex: bloquear casa de origem)
            } else {
                jogoController.trocarDeVez();
            }
        } else {
            // Incrementa as jogadas sem comer peça e troca de vez
            jogadasSemComerPeca++;
            jogoController.trocarDeVez();
        }

        jogadas++;

        // Verifica se a peça pode se transformar em dama
        if (podeSerDama()) {
            transformarDama(destino, jogadas);
        }
    }

}
