import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<Player>();
        Dealer dealer = new Dealer();

        Scanner scanner = new Scanner(System.in);

        System.out.print("========== BLACK JACK ==========\n\n");

        //*Cria todos os jogadores
        System.out.print("Quantos jogadores participarão?\n> ");
        int num_players = scanner.nextInt();

        for (int i = 0; i < num_players; i++) {
            System.out.print("\nDigite o nome do novo jogador: ");
            players.add(new Player(scanner.next()));
        }

        players.forEach((player) -> {
            player.insertCard(dealer.getDeck().pop());
            player.insertCard(dealer.getDeck().pop());
        });

        dealer.insertCard(dealer.getDeck().pop());
        dealer.insertCard(dealer.getDeck().pop());

        //* LOOP PRINCIPAL
        for (int turn = 0; true; turn++) {
            System.out.println("\nTURNO " + turn);

            for (int i = 0; i < players.size(); i++) {
                Player current_player = players.get(i);

                if (current_player != dealer) {
                    //Turno inicial
                    if (turn == 0) {
                        System.out.print("\nMão do(a) jogador(a) " + current_player.getName() + "\n");
                        current_player.printHand();
                    }

                    //Começou o jogo
                    else {
                        System.out.print("\n\nVez de " + current_player.getName() +
                                "\nDigite [0] para parar ou [1] para comprar mais uma carta: ");
                        int para_ou_compra = scanner.nextInt();
                        if (para_ou_compra == 1) {
                            current_player.insertCard(dealer.getDeck().pop());
                            System.out.print("\nSua nova mão é:\n");
                            current_player.printHand();
                        } else if (para_ou_compra != 0) {
                            System.out.print("Escolha inválida. Por padrão, você parou.");
                        } else {
                            if (players.contains(dealer)) {
                                if (dealer.getHandValue() > current_player.getHandValue()) endGame(dealer);
                            }
                        }

                        if (current_player.getHandValue() == 21) {
                            endGame(current_player);
                        } else if (current_player.getHandValue() > 21) {
                            System.out.print("\n" + current_player.getName() + " perdeu...");
                            players.remove(current_player);
                            i--;
                        }

                        if (players.size() == 1) {
                            players.add(dealer);
                            System.out.print("\n\nMão da Mesa:\n");
                            dealer.printHand();
                        }
                    }
                }

                //Turno da mesa
                else {
                    if (players.get(0).getHandValue() > dealer.getHandValue()) {
                        System.out.print("\n\nMESA COMPRA UMA CARTA\n\n");
                        dealer.insertCard(dealer.getDeck().pop());
                        System.out.print("\n\nNova mão da mesa:\n");
                        dealer.printHand();
                    }

                    if (dealer.getHandValue() == 21) {
                        System.out.print("\n\nBLACK JACK!!!!");
                        endGame(dealer);
                    }

                    else if (dealer.getHandValue() > 21) {
                        endGame(players.get(0));
                    }
                }
            }
        }
    }

    //Termina o jogo fechando o programa
    static void endGame (Player winner) {
        System.out.print("\n\nO(a) JOGADOR(a) " + winner.getName().toUpperCase() + " VENCEU!");
        System.exit(0);
    }
}
