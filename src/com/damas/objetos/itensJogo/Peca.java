package com.damas.objetos.itensJogo;

import com.damas.gui.CasaGUI;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface com os métodos das peças
 * 
 * @author João Victor da S. Cirilo {@link joao.cirilo@academico.ufpb.br}
 */
public interface Peca {

    public static final int PEDRA_BRANCA = 1;
    public static final int PEDRA_VERMELHA = 2;
    public static final int DAMA_BRANCA = 3;
    public static final int DAMA_VERMELHA = 4;

    boolean mover(Casa origem, Casa destino);

    public abstract void moverdestino(Casa destino);

    public boolean isMovimentoValido(Casa destino);

    public int getTipo();

    public void desenhar(CasaGUI casaGUI);

    public boolean podeSerDama();

    void transformarDama(Casa casa, int jogadas);

    void ComerPecaComEstilo(ArrayList<Casa> pecasAComer, JogoController jogoController, int jogadasSemComerPeca);

    void processarResultadoDeMovimento(Casa destino, ArrayList<Casa> pecasAComer, JogoController jogoController,
            int jogadasSemComerPeca, int jogadas);

    default void moverSeValido(Casa origem, Casa destino, JogoController jogoController, List<Casa> pecasAComer,
            int jogadasSemComerPeca, int jogadas) {

    }
}