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
public class Rei extends Peca {

    // Rei 1 casa em qualquer direção (horizontal, vertical, diagonal)

    public Rei() {

    }

    public Rei(Tabuleiro tabuleiro, Color cor, Position posicao) {
        super(tabuleiro, cor, posicao);
    }

    @Override
    public Position[][] possiveisMovimentos() {

        Tabuleiro tabuleiro = getTabuleiro();
        Position posicaoAtual = getPosicao();
        Position[][] possiveisMovimentos = new Position[tabuleiro.getLinhas()][tabuleiro.getColunas()];

        for (int linha = posicaoAtual.getLinha() - 1; linha <= posicaoAtual.getLinha() + 1; linha++) {

            for (int coluna = posicaoAtual.getColuna() - 1; coluna <= posicaoAtual.getColuna() + 1; coluna++) {

                if (linha >= 0 && linha < tabuleiro.getLinhas() && coluna >= 0 && coluna < tabuleiro.getColunas()) {

                    Position posicaoParaSerChecada = new Position(linha, coluna);
                    if (!posicaoParaSerChecada.equals(posicaoAtual)) {

                        Peca possivelPeca = tabuleiro.pegarPosicaoEspecifica(posicaoParaSerChecada);

                        if (possivelPeca == null) {
                            possiveisMovimentos[linha][coluna] = posicaoParaSerChecada;
                        }

                        else if (possivelPeca != null && possivelPeca.getCor() != getCor()) {
                            possiveisMovimentos[linha][coluna] = posicaoParaSerChecada;

                        }
                    }
                }

            }
        }
        return possiveisMovimentos;
    }

    @Override
    public String toString() {
        return "R";
    }
}
