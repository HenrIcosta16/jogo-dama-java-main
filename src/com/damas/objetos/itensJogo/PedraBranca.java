package com.damas.objetos.itensJogo;

import com.damas.gui.CasaGUI;

public class PedraBranca extends Pedra {

    public PedraBranca(Casa casa, int tipo) {
        super(casa, Pedra.PEDRA_BRANCA);
    }

    @Override
    public void desenhar(CasaGUI casaGUI) {
        casaGUI.desenharPedraBranca();
    }

    @Override
    public void moverdestino(Casa destino) {
        casa.removerPeca();
        destino.colocarPeca(this);
        casa = destino;
    }

}
