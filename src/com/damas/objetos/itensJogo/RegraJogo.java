package com.damas.objetos.itensJogo;

import java.util.ArrayList;

public class RegraJogo {
    private Tabuleiro tabuleiro;
    private Jogo jogo;
    private JogoController jogoController;
    private ArrayList<Casa> pecasAComer;

    public RegraJogo(ArrayList<Casa> pecasAComer, Tabuleiro tabuleiro, Jogo jogo, JogoController jogoController) {
        this.tabuleiro = tabuleiro;
        this.jogo = jogo;
        this.jogoController = jogoController;
        this.pecasAComer = pecasAComer;
    }

    public boolean simularMovimentoEValidar(Casa origem, Casa destino) {
        Peca peca = origem.getPeca();

        if (destino.getPeca() != null)
            return false;

        // SENTIDO DO MOVIMENTO E DISTÂNCIA DO MOVIMENTO
        int[] movimento = calcularMovimento(origem, destino);
        if (movimento == null)
            return false;

        int sentidoX = movimento[0];
        int sentidoY = movimento[1];
        int distanciaX = movimento[2];
        int distanciaY = movimento[3];
        if (!validarMovimento(origem, destino, peca, sentidoX, sentidoY, distanciaX, distanciaY)) {
            return false;
        }

        if (!verificarCaminho(origem, destino, peca, sentidoX, sentidoY)) {
            return false;
        }
        return true;
    }

    private int[] calcularMovimento(Casa origem, Casa destino) {
        int sentidoX = destino.getX() - origem.getX();
        int sentidoY = destino.getY() - origem.getY();
        int distanciaX = Math.abs(sentidoX);
        int distanciaY = Math.abs(sentidoY);

        if (distanciaX == 0 || distanciaY == 0)
            return null;

        sentidoX /= distanciaX;
        sentidoY /= distanciaY;

        return new int[] { sentidoX, sentidoY, distanciaX, distanciaY };
    }

    private boolean validarMovimento(Casa origem, Casa destino, Peca peca, int sentidoX, int sentidoY, int distanciaX,
            int distanciaY) {
        // && (peca.getTipo() == Pedra.PEDRA_BRANCA || peca.getTipo() ==
        // Pedra.PEDRA_VERMELHA)
        if (distanciaX >= 2 && distanciaY >= 2
                && (peca.getTipo() == Pedra.PEDRA_BRANCA || peca.getTipo() == Pedra.PEDRA_VERMELHA)) {
            boolean v = validarCasaIntermediaria(destino, sentidoX, sentidoY);
            return v;
        } else {
            return validarMovimentoPedra(peca, distanciaX, distanciaY, sentidoY);
        }
    }

    private boolean validarCasaIntermediaria(Casa destino, int sentidoX, int sentidoY) {
        Casa casa = tabuleiro.getCasa(destino.getX() - sentidoX, destino.getY() - sentidoY);
        return casa.getPeca() != null;
    }

    private boolean validarMovimentoPedra(Peca peca, int distanciaX, int distanciaY, int sentidoY) {
        if (distanciaX >= 1 && distanciaY >= 1) {
            if ((peca.getTipo() == Pedra.PEDRA_BRANCA && sentidoY == 1)
                    || (peca.getTipo() == Pedra.DAMA_BRANCA && (sentidoY == -1 || sentidoY == 1))) {
                return true;
            } else if ((peca.getTipo() == Pedra.PEDRA_VERMELHA && sentidoY == -1)
                    || (peca.getTipo() == Pedra.DAMA_VERMELHA && (sentidoY == 1 || sentidoY == -1))) {
                return true;
            }
        }
        return false;
    }

    private boolean verificarCaminho(Casa origem, Casa destino, Peca peca, int sentidoX, int sentidoY) {
        int i = origem.getX();
        int j = origem.getY();
        int casasComPecaSeguidas = 0;

        while (!(i == destino.getX() && j == destino.getY())) {
            i += sentidoX;
            j += sentidoY;

            Casa alvo = tabuleiro.getCasa(i, j);
            Peca pecaAlvo = alvo.getPeca();

            if (pecaAlvo != null) {
                casasComPecaSeguidas++;

                if (ehMesmaCor(peca, pecaAlvo)) {
                    limparPecasAComer();
                    return false;
                }

            } else {
                if (casasComPecaSeguidas == 1) {
                    adicionarPecaAComer(alvo, sentidoX, sentidoY);
                }
                casasComPecaSeguidas = 0;
            }

            if (casasComPecaSeguidas == 2) {
                limparPecasAComer();
                return false;
            }
        }
        return true;
    }

    private boolean ehMesmaCor(Peca peca, Peca pecaAlvo) {
        return ((peca.getTipo() == Pedra.PEDRA_BRANCA || peca.getTipo() == Pedra.DAMA_BRANCA) &&
                (pecaAlvo.getTipo() == Pedra.PEDRA_BRANCA || pecaAlvo.getTipo() == Pedra.DAMA_BRANCA)) ||
                ((peca.getTipo() == Pedra.PEDRA_VERMELHA || peca.getTipo() == Pedra.DAMA_VERMELHA) &&
                        (pecaAlvo.getTipo() == Pedra.PEDRA_VERMELHA || pecaAlvo.getTipo() == Pedra.DAMA_VERMELHA));
    }

    private void limparPecasAComer() {
        if (pecasAComer.size() > 0) {
            pecasAComer.clear();
        }
    }

    private void adicionarPecaAComer(Casa alvo, int sentidoX, int sentidoY) {
        Casa casa = tabuleiro.getCasa(alvo.getX() - sentidoX, alvo.getY() - sentidoY);
        pecasAComer.add(casa);

    }

    public boolean percorrerEVerificar(Casa origem, int deltaX, int deltaY) {

        Peca peca = origem.getPeca();
        int x = origem.getX();
        int y = origem.getY();
        int pecasSeguidasNoCaminho = 0;

        // SE O TIPO FOR PEDRA
        if ((peca.getTipo() == Peca.PEDRA_BRANCA) || (peca.getTipo() == Peca.PEDRA_VERMELHA)) {
            x += deltaX;
            y += deltaY;
            try {
                Peca pecaAtual = tabuleiro.getCasa(x, y).getPeca();
                if (!(pecaAtual == null)) {
                    if (tabuleiro.getCasa((x + deltaX), (y + deltaY)).getPeca() != null) {
                        return false;
                    }
                    if ((peca.getTipo() == Peca.PEDRA_BRANCA) && ((pecaAtual.getTipo() == Peca.DAMA_BRANCA
                            || pecaAtual.getTipo() == Peca.PEDRA_BRANCA))) {
                        return false;
                    } else {
                        if ((peca.getTipo() == Peca.PEDRA_VERMELHA) && ((pecaAtual.getTipo() == Peca.DAMA_VERMELHA
                                || pecaAtual.getTipo() == Peca.PEDRA_VERMELHA))) {
                            return false;
                        }
                    }

                    return true;
                }

            } catch (Exception e) {
                return false;
            }

        } else {
            while (x >= 0 && x < 8 && y >= 0 && y < 8) {
                Peca pecaAtual = tabuleiro.getCasa(x, y).getPeca();
                if (pecaAtual != null) {
                    pecasSeguidasNoCaminho++;
                    if ((peca.getTipo() == Peca.DAMA_BRANCA
                            && (pecaAtual.getTipo() == Peca.PEDRA_BRANCA || pecaAtual.getTipo() == Peca.DAMA_BRANCA))
                            || (peca.getTipo() == Peca.DAMA_VERMELHA && (pecaAtual.getTipo() == Peca.PEDRA_VERMELHA
                                    || pecaAtual.getTipo() == Peca.DAMA_VERMELHA))) {
                        return false;
                    }
                    if (pecasSeguidasNoCaminho > 1) {
                        return false;
                    }
                }
                x += deltaX;
                y += deltaY;
            }
        }

        return false;
    }

    public boolean deveContinuarJogando(Casa origem) {
        return percorrerEVerificar(origem, Tabuleiro.X_ESQUERDA, Tabuleiro.Y_CIMA) ||
                percorrerEVerificar(origem, Tabuleiro.X_DIREITA, Tabuleiro.Y_CIMA) ||
                percorrerEVerificar(origem, Tabuleiro.X_DIREITA, Tabuleiro.Y_BAIXO) ||
                percorrerEVerificar(origem, Tabuleiro.X_ESQUERDA, Tabuleiro.Y_BAIXO);
    }

}
