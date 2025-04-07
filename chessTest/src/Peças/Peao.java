/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Peças;

import Xadrez.Tabuleiro;

/**
 *
 * @author Pichau
 */
public class Peao extends Peca {

    // Peão 1 casa para frente (2 no primeiro movimento), captura na diagonal

    public Peao(Tabuleiro tabuleiro, Color cor, Position posicao) {
        super(tabuleiro, cor, posicao);
    }

    @Override
    public Position[][] possiveisMovimentos() {

        Tabuleiro tabuleiro = getTabuleiro();
        Position[][] possiveisMovimentos = new Position[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        Position posicaoDaPeca = getPosicao();
        int linhaAtual = posicaoDaPeca.getLinha();
        int colunaAtual = posicaoDaPeca.getColuna();

        // Checar na coluna - 1 e + 1 pra ver se tem peça (diagonal)
        for (int linha = linhaAtual - 1; linha <= linhaAtual + 1; linha++) { // Checando a linha anterior e sucessora

            for (int coluna = colunaAtual - 1; coluna <= colunaAtual + 1; coluna++) { // Checando as colunas na diagonal

                // Vendo se ta nos parâmetros
                if (linha >= 0 && linha < tabuleiro.getLinhas() && coluna >= 0 && coluna < tabuleiro.getColunas()) {

                    // Vendo se não é a central
                    if (linha != linhaAtual) {

                        Position posicaoChecada = new Position(linha, coluna);
                        Peca pecaNaPosicaoAtual = tabuleiro.pegarPosicaoEspecifica(posicaoChecada);

                        // Se a coluna for maior ou menor (diagonal)
                        if (posicaoChecada.getColuna() < colunaAtual || posicaoChecada.getColuna() > colunaAtual) {

                            // Se tiver peça e for cor diferente
                            if (pecaNaPosicaoAtual != null && pecaNaPosicaoAtual.getCor() != getCor()) {
                                // Captura, não precisa do break pq só checa as diagonais no limite
                                possiveisMovimentos[linha][coluna] = posicaoChecada;
                            }

                        }
                        // Se não for diagonal é a mesma coluna porem em outra linha
                        // Se não tiver peça ou for peça inimiga
                        else if (pecaNaPosicaoAtual == null || pecaNaPosicaoAtual.getCor() != getCor()) {

                            if (primeiroMovimento) { // Se for o primeiro movimento

                                if (getCor() == Color.BLACK) { // Se for preta

                                    Position posicaoDaFrente = new Position(linha - 1, colunaAtual);
                                    possiveisMovimentos[linha][coluna] = posicaoChecada;

                                    Peca pecaNaSegundaCasa = tabuleiro.pegarPosicaoEspecifica(posicaoDaFrente);
                                    if (pecaNaSegundaCasa == null) {

                                        possiveisMovimentos[linha - 1][coluna] = posicaoDaFrente;
                                    }
                                }

                                else {

                                    Position posicaoDaFrente = new Position(linha + 1, colunaAtual);
                                    possiveisMovimentos[linha][coluna] = posicaoChecada;

                                    Peca pecaNaSegundaCasa = tabuleiro.pegarPosicaoEspecifica(posicaoDaFrente);
                                    if (pecaNaSegundaCasa == null) {

                                        possiveisMovimentos[linha + 1][coluna] = posicaoDaFrente;
                                    }

                                }

                            }

                            else {

                                if (getCor() == Color.BLACK) {
                                    if (posicaoChecada.getLinha() < linhaAtual) {
                                        possiveisMovimentos[linha][coluna] = posicaoChecada;

                                    }
                                }

                                else {
                                    if (posicaoChecada.getLinha() > linhaAtual) {
                                        possiveisMovimentos[linha][coluna] = posicaoChecada;

                                    }
                                }
                            }

                        }

                    }
                }
            }
        }
        return possiveisMovimentos;
    }
}
