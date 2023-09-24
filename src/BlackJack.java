import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
    /**
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<Player>();
        Dealer dealer = new Dealer();

        Scanner scanner = new Scanner(System.in);
        
        // Regras 
        System.out.print("===================== BLACK JACK ========================\n\n");
        System.out.print("VALOR DAS CARTAS\n");
        System.out.print("\n O \'ás\' vale 1 ou 11 pontos." +
                        "\n\'J\', \'Q\',\'k\'  valem 10 pontos." +
                        "\n As demais cartas, seu próprio valor.");


        System.out.println("\n\n===============Como Jogar o Black Jack==================\n");
        System.out.println("No seu turno, cada jogador tem várias opções sempre e quando não tenha \"blackjackz\":" +
        "\n\n 1- \"Pedir carta\". O dealer distribui uma carta mais ao jogador." + 
        "\n Se as cartas somarem mais de 21 pontos, automaticamente perde e passa a vez." +
        "\n 2- \"Parar\". O jogador fica com as cartas que tiver e passa a vez ao seguinte jogador.");

        System.out.print("\n\n======================= O Dealer ========================\n\n");

        System.out.println("Quando todos os jogadores terminarem seu turno, o dealer fará sua jogada."+
                            "\nEstá obrigado a dar-se cartas até que a sua jogada some 17 pontos ou mais."+
                            "\nNão pode dobrar, dividir, desistir e nem apostar seguro.");
                
        System.out.print("\n\n======================= BOM JOGO ========================\n\n");

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

        //* LOOP PRINCIPAL**
        for (int turn = 0; true; turn++) {
            System.out.println("\nTURNO " + turn);

            for (int i = 0; i < players.size(); i++) {
                Player current_player = players.get(i);

                if (current_player != dealer) {
                    //Turno inicial
                    if (turn == 0) {
                        System.out.print("\nMão do(a) jogador(a) " + current_player.getName() + "\n");
                        current_player.printHand();
                        if (current_player.getHandValue() == 21) endGame(current_player);
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
