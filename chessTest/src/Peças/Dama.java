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
public class Dama extends Peca {

    public Dama() {

    }

    public Dama(Tabuleiro tabuleiro, Color cor, Position posicao) {
        super(tabuleiro, cor, posicao);
    }

    // Dama pode ir para qualquer lugar do tabuleiro desde que não pule peças
    public Position[][] possiveisMovimentos() {
        Tabuleiro tabuleiro = getTabuleiro();
        Position posicaoAtual = getPosicao();
        Position[][] possiveisMovimentos = new Position[tabuleiro.getLinhas()][tabuleiro.getColunas()];

        boolean movimentoParaCima = true;
        boolean movimentoParaBaixo = true;

        boolean movimentoParaEsquerda = true;
        boolean movimentoParaDireita = true;

        // Cima e baixo
        for (int i = 1; i < tabuleiro.getLinhas(); i++) {

            int linhaParaCima = posicaoAtual.getLinha() + i;
            int linhaParaBaixo = posicaoAtual.getLinha() - i;

            // Se tiver permitido o movimento para cima
            if (movimentoParaCima) {
                // Se estiver nos parametros
                if (linhaParaCima >= 0 && linhaParaCima < tabuleiro.getLinhas()) {

                    Position posicaoParaCima = new Position(linhaParaCima, posicaoAtual.getColuna());
                    Peca possivelPecaParaCima = tabuleiro.pegarPosicaoEspecifica(posicaoParaCima);
                    // Se for null é um movimento
                    if (possivelPecaParaCima == null) {
                        possiveisMovimentos[linhaParaCima][posicaoAtual.getColuna()] = posicaoParaCima;
                    }
                    // Se for tiver uma peça inimiga é movimento porém cancela o proximo loop
                    else if (possivelPecaParaCima != null && possivelPecaParaCima.getCor() != getCor()) {
                        possiveisMovimentos[linhaParaCima][posicaoAtual.getColuna()] = posicaoParaCima;
                        movimentoParaCima = false;
                    }
                    // Cancela o proximo loop
                    else if (possivelPecaParaCima != null && possivelPecaParaCima.getCor() == getCor()) {
                        movimentoParaCima = false;
                    }
                }
            }

            if (movimentoParaBaixo) {

                if (linhaParaBaixo >= 0 && linhaParaBaixo < tabuleiro.getLinhas()) {

                    Position posicaoParaBaixo = new Position(linhaParaBaixo, posicaoAtual.getColuna());
                    Peca possivelPecaParaBaixo = tabuleiro.pegarPosicaoEspecifica(posicaoParaBaixo);

                    if (possivelPecaParaBaixo == null) {
                        possiveisMovimentos[linhaParaBaixo][posicaoAtual.getColuna()] = posicaoParaBaixo;
                    }

                    else if (possivelPecaParaBaixo != null && possivelPecaParaBaixo.getCor() != getCor()) {
                        possiveisMovimentos[linhaParaBaixo][posicaoAtual.getColuna()] = posicaoParaBaixo;
                        movimentoParaBaixo = false;
                    }

                    else if (possivelPecaParaBaixo != null && possivelPecaParaBaixo.getCor() == getCor()) {
                        movimentoParaBaixo = false;
                    }
                }
            }

        }

        // Direita e esquerda
        for (int i = 1; i < tabuleiro.getColunas(); i++) {

            int colunaParaEsquerda = posicaoAtual.getColuna() - i;
            int colunaParaDireita = posicaoAtual.getColuna() + i;

            if (movimentoParaEsquerda) {

                if (colunaParaEsquerda >= 0 && colunaParaEsquerda < tabuleiro.getColunas()) {

                    Position posicaoParaChecarNaEsquerda = new Position(posicaoAtual.getLinha(), colunaParaEsquerda);
                    Peca possivelPecaNaEsquerda = tabuleiro.pegarPosicaoEspecifica(posicaoParaChecarNaEsquerda);

                    if (possivelPecaNaEsquerda == null) {
                        possiveisMovimentos[posicaoAtual.getLinha()][colunaParaEsquerda] = posicaoParaChecarNaEsquerda;
                    }

                    else if (possivelPecaNaEsquerda != null && possivelPecaNaEsquerda.getCor() != getCor()) {
                        possiveisMovimentos[posicaoAtual.getLinha()][colunaParaEsquerda] = posicaoParaChecarNaEsquerda;
                        movimentoParaEsquerda = false;
                    }

                    else if (possivelPecaNaEsquerda != null && possivelPecaNaEsquerda.getCor() == getCor()) {
                        movimentoParaEsquerda = false;
                    }
                }
            }

            if (movimentoParaDireita) {
                if (colunaParaDireita >= 0 && colunaParaDireita < tabuleiro.getColunas()) {

                    Position posicaoParaChecarNaDireita = new Position(posicaoAtual.getLinha(), colunaParaDireita);
                    Peca possivelPecaNaDireita = tabuleiro.pegarPosicaoEspecifica(posicaoParaChecarNaDireita);

                    if (possivelPecaNaDireita == null) {
                        possiveisMovimentos[posicaoAtual.getLinha()][colunaParaDireita] = posicaoParaChecarNaDireita;

                    }

                    else if (possivelPecaNaDireita != null && possivelPecaNaDireita.getCor() != getCor()) {
                        possiveisMovimentos[posicaoAtual.getLinha()][colunaParaDireita] = posicaoParaChecarNaDireita;
                        movimentoParaDireita = false;
                    }

                    else if (possivelPecaNaDireita != null && possivelPecaNaDireita.getCor() == getCor()) {
                        movimentoParaDireita = false;
                    }
                }
            }
        }

        // Diagonal Direita cima
        for (int i = 1; i < tabuleiro.getLinhas(); i++) {
            int novaLinha = posicaoAtual.getLinha() - i;
            int novaColuna = posicaoAtual.getColuna() + i;

            if (novaLinha >= 0 && novaLinha < tabuleiro.getLinhas() && novaColuna >= 0
                    && novaColuna < tabuleiro.getColunas()) {

                Position posicaoParaChecar = new Position(novaLinha, novaColuna);
                Peca possivelPeca = tabuleiro.pegarPosicaoEspecifica(posicaoParaChecar);

                if (possivelPeca == null) {
                    possiveisMovimentos[novaLinha][novaColuna] = posicaoParaChecar;
                }

                else if (possivelPeca != null & possivelPeca.getCor() != getCor()) {
                    possiveisMovimentos[novaLinha][novaColuna] = posicaoParaChecar;
                    break;
                }

                else if (possivelPeca != null & possivelPeca.getCor() == getCor()) {
                    break;
                }

            }
        }

        // Diagonal Baixo direita
        for (int i = 1; i < tabuleiro.getLinhas(); i++) {
            int novaLinha = posicaoAtual.getLinha() + i;
            int novaColuna = posicaoAtual.getColuna() + i;

            if (novaLinha >= 0 && novaLinha < tabuleiro.getLinhas() && novaColuna >= 0
                    && novaColuna < tabuleiro.getColunas()) {

                Position posicaoParaChecar = new Position(novaLinha, novaColuna);
                Peca possivelPeca = tabuleiro.pegarPosicaoEspecifica(posicaoParaChecar);

                if (possivelPeca == null) {
                    possiveisMovimentos[novaLinha][novaColuna] = posicaoParaChecar;
                }

                else if (possivelPeca != null & possivelPeca.getCor() != getCor()) {
                    possiveisMovimentos[novaLinha][novaColuna] = posicaoParaChecar;
                    break;
                }

                else if (possivelPeca != null & possivelPeca.getCor() == getCor()) {
                    break;
                }

            }
        }

        // Diagonal Cima esquerda
        for (int i = 1; i < tabuleiro.getLinhas(); i++) {
            int novaLinha = posicaoAtual.getLinha() + i;
            int novaColuna = posicaoAtual.getColuna() - i;

            if (novaLinha >= 0 && novaLinha < tabuleiro.getLinhas() && novaColuna >= 0
                    && novaColuna < tabuleiro.getColunas()) {

                Position posicaoParaChecar = new Position(novaLinha, novaColuna);
                Peca possivelPeca = tabuleiro.pegarPosicaoEspecifica(posicaoParaChecar);

                if (possivelPeca == null) {
                    possiveisMovimentos[novaLinha][novaColuna] = posicaoParaChecar;
                }

                else if (possivelPeca != null & possivelPeca.getCor() != getCor()) {
                    possiveisMovimentos[novaLinha][novaColuna] = posicaoParaChecar;
                    break;
                }

                else if (possivelPeca != null & possivelPeca.getCor() == getCor()) {
                    break;
                }

            }
        }

        // Diagonal Baixo esquerda
        for (int i = 1; i < tabuleiro.getLinhas(); i++) {
            int novaLinha = posicaoAtual.getLinha() - i;
            int novaColuna = posicaoAtual.getColuna() - i;

            if (novaLinha >= 0 && novaLinha < tabuleiro.getLinhas() && novaColuna >= 0
                    && novaColuna < tabuleiro.getColunas()) {

                Position posicaoParaChecar = new Position(novaLinha, novaColuna);
                Peca possivelPeca = tabuleiro.pegarPosicaoEspecifica(posicaoParaChecar);

                if (possivelPeca == null) {
                    possiveisMovimentos[novaLinha][novaColuna] = posicaoParaChecar;
                }

                else if (possivelPeca != null & possivelPeca.getCor() != getCor()) {
                    possiveisMovimentos[novaLinha][novaColuna] = posicaoParaChecar;
                    break;
                }

                else if (possivelPeca != null & possivelPeca.getCor() == getCor()) {
                    break;
                }

            }
        }

        return possiveisMovimentos;
    }

}
