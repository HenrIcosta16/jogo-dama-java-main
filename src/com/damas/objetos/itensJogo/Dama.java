package com.damas.objetos.itensJogo;

/**
 * Dama do jogo.
 * <p>
 * Recebe uma casa e um tipo associado
 * </p>
 * 
 * @author João Victor da S. Cirilo {@link joao.cirilo@academico.ufpb.br}
 */
public abstract class Dama extends Pedra {

    public static final int DAMA_BRANCA = 1;
    public static final int DAMA_VERMELHA = 3;

    public Dama(Casa casa, int tipo) {
        super(casa, tipo);

    }

}
