package com.damas.objetos.itensJogo;

import com.damas.gui.CasaGUI;

/*
 * PARECIDA COM A BRANCA
 */
public class DamaVermelha extends Dama {

    public DamaVermelha(Casa casa, int tipo) {
        super(casa, Peca.DAMA_VERMELHA);
    }

    @Override
    public void desenhar(CasaGUI casaGUI) {
        casaGUI.desenharDamaVermelha();
    }

    @Override
    public void moverdestino(Casa destino) {
        casa.removerPeca();
        destino.colocarPeca(this);
        casa = destino;
    }

    @Override
    public boolean isMovimentoValido(Casa destino) {
        int distanciaX = Math.abs((destino.getX() - casa.getX()));
        int distanciaY = Math.abs((destino.getY() - casa.getY()));

        if (distanciaX == distanciaY)
            return true;

        return false;
    }

    @Override
    public boolean mover(Casa origem, Casa destino) {
        // Movimento válido se a peça se mover diagonalmente
        int distanciaX = Math.abs((destino.getX() - origem.getX()));
        int distanciaY = Math.abs((destino.getY() - origem.getY()));

        if (distanciaX == distanciaY)
            return true;

        return false;
    }
}
