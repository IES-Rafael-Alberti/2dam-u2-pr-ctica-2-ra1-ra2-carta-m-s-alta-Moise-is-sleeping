package com.moise.cartas

class Deck {
    var cardList = arrayListOf<Card>()
    companion object{
        val deck = Deck()
        fun createDeck(): ArrayList<Card> {
            val numbers = PlayingCards.values()
            val suites = Suites.values()
            for (suite in suites ){
                var counter = 2
                var secondaryCounter = 0
                for (number in numbers){
                    if (counter == 10 && secondaryCounter <4){
                        val card = Card(number,suite,counter,counter,number.toString()+"_of_$suite")
                        deck.cardList.add(card)
                        counter = 10
                        secondaryCounter+=1
                    }else if(counter == 10 && secondaryCounter ==4){
                        counter = 11
                        val card = Card(number,suite,counter-10,counter,number.toString()+"_of_$suite")
                        deck.cardList.add(card)
                    }else{
                        val card = Card(number,suite,counter,counter,number.toString()+"_of_$suite")
                        deck.cardList.add(card)
                        counter+=1
                    }


                }
            }
            return deck.cardList
        }
        fun shuffle() {
            deck.cardList.shuffle()

        }

        fun giveCard(): Card {
            shuffle()
            val pickedCard = deck.cardList.last()
            deck.cardList.dropLast(1)
            return pickedCard
        }


    }
}