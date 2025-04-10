
import Aplicacao.Partida;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        Partida partida = new Partida();

        partida.organizacaoDasPecas();
        partida.comecarPartida();
    }
}
