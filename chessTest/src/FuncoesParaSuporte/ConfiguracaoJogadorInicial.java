package FuncoesParaSuporte;

import Jogador.Jogador;
import java.util.Random;

/**
 *
 * @author Pichau
 */
public class ConfiguracaoJogadorInicial {

    public ConfiguracaoJogadorInicial() {

    }

    public Jogador[] escolherJogador(Jogador j1, Jogador j2) {

        Random random = new Random();
        int numeroGerado = random.nextInt(2); // Numero aleatorio de 0 a 1

        Jogador[] jogadores = new Jogador[2]; // Conjunto de tamanho 2

        if (numeroGerado == 0) {
            jogadores[0] = j1;
            jogadores[1] = j2;

        }

        else {
            jogadores[0] = j2;
            jogadores[1] = j1;
        }

        return jogadores;

    }

}
