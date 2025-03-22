package com.damas.objetos.itensJogo;

import com.damas.gui.CasaGUI;

public class PedraVermelha extends Pedra {

    public PedraVermelha(Casa casa, int tipo) {
        super(casa, Pedra.PEDRA_VERMELHA);
    }

    @Override
    public void desenhar(CasaGUI casaGUI) {
        casaGUI.desenharPedraVermelha();
    }

    @Override
    public void moverdestino(Casa destino) {
        casa.removerPeca();
        destino.colocarPeca(this);
        casa = destino;
    }

}
