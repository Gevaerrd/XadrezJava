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
public class Torre extends Peca {

    // Torre Horizontal e vertical (qualquer número de casas)

    public Torre() {

    }

    public Torre(Tabuleiro tabuleiro, Color cor, Position posicao) {
        super(tabuleiro, cor, posicao);
    }

    public Position[][] possiveisMovimentos() {

        Tabuleiro tabuleiro = getTabuleiro();
        Position posicaoAtual = getPosicao();
        Position[][] possiveisMovimentos = new Position[tabuleiro.getLinhas()][tabuleiro.getColunas()];

        boolean movimentoParaCima = true;
        boolean movimentoParaBaixo = true;

        boolean movimentoParaEsquerda = true;
        boolean movimentoParaDireita = true;

        // Vertical cima e baixo
        for (int linha = 1; linha < tabuleiro.getLinhas(); linha++) {

            int novaLinhaCima = posicaoAtual.getLinha() + linha;
            int novaLinhaBaixo = posicaoAtual.getLinha() - linha;

            if (movimentoParaCima) {

                if (novaLinhaCima >= 0 && novaLinhaCima < tabuleiro.getLinhas()) {
                    Position posicaoParaCima = new Position(novaLinhaCima, posicaoAtual.getColuna());
                    Peca possivelPeca = tabuleiro.pegarPosicaoEspecifica(posicaoParaCima);

                    if (possivelPeca == null) {
                        possiveisMovimentos[novaLinhaCima][posicaoAtual.getColuna()] = posicaoParaCima;
                    }

                    else if (possivelPeca != null && possivelPeca.getCor() != getCor()) {
                        possiveisMovimentos[novaLinhaCima][posicaoAtual.getColuna()] = posicaoParaCima;
                        movimentoParaCima = false;
                    }

                    else if (possivelPeca != null && possivelPeca.getCor() == getCor()) {
                        movimentoParaCima = false;
                    }

                }
            }

            if (movimentoParaBaixo) {

                if (novaLinhaBaixo >= 0 && novaLinhaBaixo < tabuleiro.getLinhas()) {

                    Position posicaoParaBaixo = new Position(novaLinhaBaixo, posicaoAtual.getColuna());
                    Peca possivelPeca = tabuleiro.pegarPosicaoEspecifica(posicaoParaBaixo);

                    if (possivelPeca == null) {
                        possiveisMovimentos[novaLinhaBaixo][posicaoAtual.getColuna()] = posicaoParaBaixo;
                    }

                    else if (possivelPeca != null && possivelPeca.getCor() != getCor()) {
                        possiveisMovimentos[novaLinhaBaixo][posicaoAtual.getColuna()] = posicaoParaBaixo;
                        movimentoParaBaixo = false;
                    }

                    else if (possivelPeca != null && possivelPeca.getCor() == getCor()) {
                        movimentoParaBaixo = false;
                    }

                }
            }
        }

        // Esquerda e direita
        for (int coluna = 1; coluna < tabuleiro.getColunas(); coluna++) {

            int novaColunaEsquerda = posicaoAtual.getColuna() - coluna;
            int novaColunaDireita = posicaoAtual.getColuna() + coluna;

            if (movimentoParaEsquerda) {

                if (novaColunaEsquerda >= 0 && novaColunaEsquerda < tabuleiro.getColunas()) {

                    Position posicaoParaEsquerda = new Position(posicaoAtual.getLinha(), novaColunaEsquerda);
                    Peca possivelPeca = tabuleiro.pegarPosicaoEspecifica(posicaoParaEsquerda);

                    if (possivelPeca == null) {
                        possiveisMovimentos[posicaoAtual.getLinha()][novaColunaEsquerda] = posicaoParaEsquerda;
                    }

                    else if (possivelPeca != null && possivelPeca.getCor() != getCor()) {
                        possiveisMovimentos[posicaoAtual.getLinha()][novaColunaEsquerda] = posicaoParaEsquerda;
                        movimentoParaEsquerda = false;
                    }

                    else if (possivelPeca != null && possivelPeca.getCor() == getCor()) {
                        movimentoParaEsquerda = false;
                    }

                }
            }

            if (movimentoParaDireita) {

                if (novaColunaDireita >= 0 && novaColunaDireita < tabuleiro.getColunas()) {

                    Position posicaoParaDireita = new Position(posicaoAtual.getLinha(), novaColunaDireita);
                    Peca possivelPeca = tabuleiro.pegarPosicaoEspecifica(posicaoParaDireita);

                    if (possivelPeca == null) {
                        possiveisMovimentos[posicaoAtual.getLinha()][novaColunaDireita] = posicaoParaDireita;
                    }

                    else if (possivelPeca != null && possivelPeca.getCor() != getCor()) {
                        possiveisMovimentos[posicaoAtual.getLinha()][novaColunaDireita] = posicaoParaDireita;
                        movimentoParaDireita = false;
                    }

                    else if (possivelPeca != null && possivelPeca.getCor() == getCor()) {
                        movimentoParaDireita = false;
                    }

                }
            }

        }
        return possiveisMovimentos;
    }

}
