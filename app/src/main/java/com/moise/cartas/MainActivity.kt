package com.moise.cartas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Creates the deck
        Deck.createDeck()
        setContent {
            //Creates the button and the image
            Buttons()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
        /**
         * Function that generates the image of the card and the 2 buttons
         */
fun Buttons(){
    //variable that stores the card to be displayed
    var selectedCard by remember { mutableStateOf(Card(PlayingCards.ace,Suites.spades,0,0,"backside")) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Row {
            //Creates the image of the card
            ImageCreator(selectedCard)
        }
        Row (
            modifier = Modifier.padding(top = 80.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Button(
                modifier = Modifier
                    .padding(10.dp)
                    .height(60.dp)
                    .width(160.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color.Black),
                onClick = {
                    //the selected card is the last card of the deck
                    selectedCard = Deck.giveCard()

                }) {
                Text(text = "New Card")
            }
            Button(
                modifier = Modifier
                    .padding(10.dp)
                    .height(60.dp)
                    .width(160.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color.Black),
                onClick = {
                    //Clears the deck
                    Deck.deck.cardList.clear()
                    //And creates it again
                    Deck.createDeck()
                    //And sets the selected card to default
                    selectedCard = Card(PlayingCards.ace,Suites.spades,0,0,"backside")
                }) {
                Text(text = "Restart !")
            }
        }
    }
}

/**
 * Function that receives the card in order to extract the drawable id
 * @param card the card to be analyzed
 * @return the id of the card
 */
@Composable
fun getCardId(card:Card): Int {
    val context = LocalContext.current
    val id = context.resources.getIdentifier(card.idDrawable, "drawable", context.packageName)
    return id
}


/**
 * Function that creates the image
 * @param card Card to be displayed
 */
@Composable
fun ImageCreator(card: Card){
    Image(
        //uses the function getCardId to extract the id of the card
        painter = painterResource(id = getCardId(card)),
        contentDescription = "image",
        modifier = Modifier
            .width(700.dp)
            .height(400.dp))
}












