package cards

import Player
import java.util.*

class Deck {
    private val deck = LinkedList<Card?>()

    //plays top card and puts it on bottom of deck
    fun playTop(player: Player?) {
        val topCard = deck.removeFirst()
        add(topCard)
        topCard!!.play(player)
    }

    fun add(card: Card?) {
        deck.addLast(card)
    }

    fun shuffle() {
        deck.shuffle()
    }
}