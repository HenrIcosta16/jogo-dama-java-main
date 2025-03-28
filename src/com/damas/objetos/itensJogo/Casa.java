package com.damas.objetos.itensJogo;

/**
    não dependem de implementações concretas, mas de abstrações (interface Peca).
 */

/**
 * Representa uma Casa do tabuleiro.
 * Possui uma posi�ao (i,j) e pode conter uma Pe�a.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */
public class Casa {

    private int x;
    private int y;
    private Peca peca;
    private Tabuleiro tabuleiro;

    public Casa(int x, int y, Tabuleiro tabuleiro) {
        this.x = x;
        this.y = y;
        this.tabuleiro = tabuleiro;
        this.peca = null;
    }

    /**
     * @param peca a Pe�ça a ser posicionada nesta Casa.
     */

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public void colocarPeca(Peca peca) {
        this.peca = peca;
    }

    /**
     * Remove a peca posicionada nesta casa, se houver.
     */
    public void removerPeca() {
        peca = null;
    }

    /**
     * @return a Peca posicionada nesta Casa, ou Null se a casa estiver livre.
     */
    public Peca getPeca() {
        return peca;
    }

    /**
     * @return true se existe uma peça nesta casa, caso contrario false.
     */
    public boolean possuiPeca() {
        return peca != null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
}
