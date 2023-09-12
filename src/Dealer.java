//O dealer (mesa) herda a classe Player para que consiga participar do jogo com a sua própria mão.
//Armazena o baralho ja embaralhado em uma Pilha<cartas>


import java.util.Stack;

public class Dealer extends Player{
    private Stack<Card> deck;
    public Dealer(){
        super("Mesa");

        DeckOfCards deckOfCards = new DeckOfCards();
        this.deck = new Stack<Card>();
        deckOfCards.shuffle();
        for(int i = 0; i < 52; i++){
            this.deck.push(deckOfCards.dealCard());
        }
    }

    public Stack<Card> getDeck(){
        return deck;
    }
}
