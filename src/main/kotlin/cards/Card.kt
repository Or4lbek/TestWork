package cards

import Player

abstract class Card(var text: String) {
    fun play(player: Player?) {
        doAction(player)
        println("You drew: $text")
    }

    abstract fun doAction(player: Player?)
}