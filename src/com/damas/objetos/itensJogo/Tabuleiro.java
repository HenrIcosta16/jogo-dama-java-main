package com.damas.objetos.itensJogo;

/**
 * O Tabuleiro do jogo.
 * Responsável por armazenar as 64 casas do jogo.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */

public class Tabuleiro {

    public static final int MAX_LINHAS = 8;
    public static final int MAX_COLUNAS = 8;

    public static final int X_ESQUERDA = -1;
    public static final int X_DIREITA = 1;
    public static final int Y_BAIXO = -1;
    public static final int Y_CIMA = 1;

    private Casa[][] casas;

    public Tabuleiro() {
        montarTabuleiro();
    }

    /**
     * Adiciona as Casas no tabuleiro
     */
    private void montarTabuleiro() {
        casas = new Casa[MAX_LINHAS][MAX_COLUNAS];
        for (int x = 0; x < MAX_LINHAS; x++) {
            for (int y = 0; y < MAX_COLUNAS; y++) {
                Casa casa = new Casa(x, y, this);
                casas[x][y] = casa;
            }
        }
        colocarPecas();
    }

    public void colocarPecas() {

        // CRIA E PÕE AS PEÇAS NA PARTE INFERIOR DO TABULEIRO
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 3; y++) {
                if ((x % 2 == 0) && (y % 2 == 0)) {
                    Casa casa = this.getCasa(x, y);
                    new PedraBranca(casa, Pedra.PEDRA_BRANCA);
                }

                else if ((x % 2 != 0) && (y % 2 != 0)) {
                    Casa casa = this.getCasa(x, y);
                    new PedraBranca(casa, Peca.PEDRA_BRANCA);
                }
            }

        }
        // CRIA E POE AS PEÇAS NA PARTE SUPERIOR DO TABULEIRO
        for (int x = 0; x < 8; x++) {
            for (int y = 5; y < 8; y++) {
                if ((x % 2 != 0) && (y % 2 != 0)) {
                    Casa casa = this.getCasa(x, y);
                    new PedraVermelha(casa, Peca.PEDRA_VERMELHA);
                } else if ((x % 2 == 0) && (y % 2 == 0)) {
                    Casa casa = this.getCasa(x, y);
                    new PedraVermelha(casa, Peca.PEDRA_VERMELHA);
                }
            }
        }
    }

    /**
     * @param x linha
     * @param y coluna
     * @return Casa na posicao (x,y)
     */
    public Casa getCasa(int x, int y) {
        return casas[x][y];
    }
}