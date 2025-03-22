package com.damas.objetos.itensJogo;

public class StatusJogo {

    private JogoController jogoController;

    public StatusJogo(JogoController jogoController) {
        this.jogoController = jogoController;
    }

    @Override
    public String toString() {

        String retorno = "Vez: ";
        if (jogoController.getVez() == 1) {
            retorno += jogoController.getJogadorUm().getNome();
            retorno += "\n";
        } else if (jogoController.getVez() == 2) {
            retorno += jogoController.getJogadorDois().getNome();
            retorno += "\n";
        }

        retorno += "Nº de jogadas: " + jogoController.getJogadas() + "\n";
        retorno += "Jogadas sem comer peça: " + jogoController.getJogadasSemComerPeca() + "\n";
        retorno += "\n";
        retorno += "Informações do(a) jogador(a) " + jogoController.getJogadorUm().getNome() + "\n";
        retorno += "Pontos: " + jogoController.getJogadorUm().getPontos() + "\n";
        retorno += "Nº de peças restantes: " + (12 - jogoController.getJogadorDois().getPontos()) + "\n";
        retorno += "\n";
        retorno += "Informações do(a) jogador(a) " + jogoController.getJogadorDois().getNome() + "\n";
        retorno += "Pontos: " + jogoController.getJogadorDois().getPontos() + "\n";
        retorno += "Nº de peças restantes: " + (12 - jogoController.getJogadorUm().getPontos()) + "\n";

        if (jogoController.getCasaBloqueadaOrigem() == null) {
            retorno += "\n";
            // retorno += "Mova a peça na casa " +
            // jogoController.getCasaBloqueadaOrigem().getX() + ":" +
            // jogoController.getCasaBloqueadaOrigem().getY() + "!";
        }

        return retorno;
    }
}
