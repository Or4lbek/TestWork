package cards

import Player
import Square

class CardDraw(private val deck: Deck, name: String?) : Square(name) {
    override fun doAction(player: Player) {
        deck.playTop(player)
    }
}