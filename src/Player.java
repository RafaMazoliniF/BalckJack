//O Player armazena o seu nome, a sua mão, e o valor da mão
//A mão é armazenada em uma ArrayList<cartas>

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand = new ArrayList<Card>();
    private int handValue = 0;

    public ArrayList<Card> getHand() {
        return hand;
    }

    public Player(String name) {
        this.name = name.toUpperCase();
    }

    //Adiciona uma nova carta à mão
    public void insertCard(Card new_card) {
        hand.add(new_card);
        switch (new_card.getFace()) {
            case "Ás":
                handValue += 11; break;
            case "Dois":
                handValue += 2; break;
            case "Três":
                handValue += 3; break;
            case "Quatro":
                handValue += 4; break;
            case "Cinco":
                handValue += 5; break;
            case "Seis":
                handValue += 6; break;
            case "Sete":
                handValue += 7; break;
            case "Oito":
                handValue += 8; break;
            case "Nove":
                handValue += 9; break;
            case "Dez":
            case "Valete":
            case "Rainha":
            case "Rei" :
                handValue += 10; break;
            default:
                break;
        }

        hand.forEach((card) -> {
            if (card.getFace() == "Ás" && handValue > 21) {
                handValue -= 10;
            }
        });
    }

    public void printHand() {
        hand.forEach((card -> System.out.println(card.toString())));
    }

    public int getHandValue() {
        return handValue;
    }

    public String getName() {
        return name;
    }
}
