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
public class Cavalo extends Peca {

    public Cavalo() {

    }

    public Cavalo(Tabuleiro tabuleiro, Color cor, Position posicao) {
        super(tabuleiro, cor, posicao);
    }

    @Override
    public Position[][] possiveisMovimentos() {
        Position posicaoAtual = getPosicao();
        Tabuleiro tabuleiro = getTabuleiro();
        Position[][] posicoes = new Position[tabuleiro.getLinhas()][tabuleiro.getColunas()];

        int[][] direcoes = {
                { -2, -1 }, { -2, 1 }, // Duas para cima, uma para a esquerda ou direita
                { -1, -2 }, { -1, 2 }, // Uma para cima, duas para a esquerda ou direita
                { 1, -2 }, { 1, 2 }, // Uma para baixo, duas para a esquerda ou direita
                { 2, -1 }, { 2, 1 } // Duas para baixo, uma para a esquerda ou direita
        };

        for (int[] direcao : direcoes) {

            int linha = direcao[0];
            int coluna = direcao[1];

            int novaLinha = posicaoAtual.getLinha() + linha;
            int novaColuna = posicaoAtual.getColuna() + coluna;

            if (novaLinha >= 0 && novaLinha < tabuleiro.getLinhas() && novaColuna >= 0
                    && novaColuna < tabuleiro.getColunas()) {

                Position posicaoParaChecar = new Position(novaLinha, novaColuna);
                Peca pecaParaChecar = tabuleiro.pegarPosicaoEspecifica(posicaoParaChecar);

                if (pecaParaChecar != null && pecaParaChecar.getCor() != getCor()) {
                    posicoes[novaLinha][novaColuna] = posicaoParaChecar;
                }

                if (pecaParaChecar == null) {
                    posicoes[novaLinha][novaColuna] = posicaoParaChecar;
                }
            }

        }

        return posicoes;
    }

    @Override
    public String toString() {
        return "C";
    }
}
