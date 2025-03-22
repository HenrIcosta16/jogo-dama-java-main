package com.damas.objetos.itensJogo;

import com.damas.gui.CasaGUI;

public class DamaBranca extends Dama {

    public DamaBranca(Casa casa, int tipo) {
        super(casa, Peca.DAMA_BRANCA);
    }

    @Override
    public void desenhar(CasaGUI casaGUI) {
        casaGUI.desenharDamaBranca();
    }

    @Override
    public void moverdestino(Casa destino) {
        casa.removerPeca();
        destino.colocarPeca(this);
        casa = destino;
    }

    /*
     * CALCULA A DISTANCIA ENTRE A POSIÇAO ATUAL E O DESTINO.
     */
    @Override
    public boolean isMovimentoValido(Casa destino) {
        int distanciaX = Math.abs((destino.getX() - casa.getX()));
        int distanciaY = Math.abs((destino.getY() - casa.getY()));

        // SE O MVMNT fOR NA DIAGONAL (distanciaX == distanciaY), ELE RETORNA TRUE.
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
