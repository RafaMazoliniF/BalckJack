import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
    static boolean is_playing = true;

    public static void main(String[] args) {
        //Scanner
        Scanner scanner = new Scanner(System.in);
        Dealer dealer = new Dealer();
        ArrayList<Player> players = new ArrayList<Player>();

        //Número de participantes
        int num_playersAux = 2;
        do{
            System.out.print("\nInsira o número de participantes (Mínimo de 2): ");
            num_playersAux = scanner.nextInt();
            if (num_playersAux < 2) System.out.println("\nValor inválido. Tente novamente.");
        } while (num_playersAux < 2);

        final int num_players = num_playersAux;

        //Começo do jogo
        System.out.println("\n\n" +
                           "========== BLACK JACK ==========\n\n");

        //Turno a turno
        for (int turn = 0; is_playing; turn++){
            System.out.printf("\n\nTURNO %d", turn);

            //Turno inicial -> jogadores recebem as cartas
            if (turn == 0) {
                System.out.println("\n\n*Jogadores recebem suas mãos iniciais*\n\n");

                //Dealer hand
                dealer.insertCard(dealer.getDeck().pop());
                dealer.insertCard(dealer.getDeck().pop());

                //Players hand
                for (int i = 0; i < num_players; i++) {
                    System.out.printf("\nMão do(a) %d\nInsira o seu nome: ", i + 1);
                    String player_name = scanner.next();

                    players.add(new Player(player_name));
                    Player current_player = players.get(i);

                    current_player.insertCard(dealer.getDeck().pop());
                    current_player.insertCard(dealer.getDeck().pop());

                    System.out.println("\n!Digite qualquer coisa para mostrar a mão!");
                    scanner.next();
                    current_player.printHand();
                }
            }

            else {
                //Jogador a jogador
                for (int i = 0; i < players.size(); i++) {
                    Player current_player = players.get(i);

                    System.out.print("\nTurno do(a) " + current_player.getName());
                    System.out.print("\nDigite:" +
                            "\n[1] para comprar uma carta;" +
                            "\n[-] para parar;\n> ");

                    //Escolhe comprar ou parar
                    int choice = scanner.nextInt();

                    //Comprou
                    if (choice == 1) {
                        Card new_card = dealer.getDeck().pop();
                        current_player.insertCard(new_card);
                        System.out.println("\nA carta comprada foi: " + new_card.toString());
                        System.out.println("O seu novo baralho é: ");
                        current_player.printHand();

                        if (current_player.getHandValue() == 21) {
                            System.out.println("\nBLACK JACK!");
                            endGame();
                        } else if (current_player.getHandValue() > 21) {
                            System.out.println("\nPERDEU...");
                            if (players.size() > 2) {
                                players.remove(current_player);
                            } else {
                                players.remove(current_player);
                                System.out.println("O jogador " + current_player.getName() + "venceu");
                                endGame();
                            }
                        }
                    }
                }
            }
        }
    }

    //End the turns loop
    static void endGame() {
        is_playing = false;
    }
}