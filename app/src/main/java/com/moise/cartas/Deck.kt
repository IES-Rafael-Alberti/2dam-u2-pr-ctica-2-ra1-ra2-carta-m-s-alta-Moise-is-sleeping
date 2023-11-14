package com.moise.cartas

import android.util.Log

class Deck {
    var cardList = arrayListOf<Card>()
    companion object{
        val deck = Deck()
        private const val tag = "DeckCreation"
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
            Log.d(tag,"List created")
            return deck.cardList
        }
        fun shuffle() {
            deck.cardList.shuffle()

        }

        fun giveCard(): Card {
            shuffle()
            Log.d(tag, deck.cardList.size.toString())
            if (deck.cardList.isNullOrEmpty()){
                Log.d(tag,"empty")
                return Card(PlayingCards.ace,Suites.spades,0,0,"backside")
            }
            else{
                val card = deck.cardList.last()
                deck.cardList = deck.cardList.dropLast(1) as ArrayList<Card>
                   return card

            }

        }


    }
}