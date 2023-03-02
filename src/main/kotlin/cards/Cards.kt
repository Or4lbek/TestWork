package cards

import Board
import Jail
import Player

internal open class CollectCard(protected var amount: Int, text: String) : Card(
    createCollectMessage(
        amount, text
    )
) {
    override fun doAction(player: Player?) {
        player!!.addMoney(amount)
    }

    companion object {
        fun createCollectMessage(amount: Int, text: String): String {
            val collectMessage = "$text. Collect $amount TG"
            val payMessage = text + ". Pay TG" + -amount
            return if (amount > 0) collectMessage else payMessage
        }
    }
}

internal class CollectEveryCard(private val players: ArrayList<Player>, amount: Int, text: String) :
    CollectCard(amount, createCollectMessage(amount, text)) {
    override fun doAction(player: Player?) {
        player!!.addMoney(amount * players.size)
        for (p in players) {
            p.addMoney(-amount)
        }
    }

    companion object {
        fun createCollectMessage(amount: Int, text: String): String {
            return CollectCard.createCollectMessage(
                amount,
                text
            ) + if (amount > 0) " from every player" else " to every player"
        }
    }
}

internal class MoveCard(private val numSquares: Int, private val board: Board, text: String?) : Card(
    text!!
) {
    override fun doAction(player: Player?) {
        player!!.move(numSquares, board)
    }
}

internal class MoveToCard(private val index: IntArray, private val board: Board, text: String?) : Card(
    text!!
) {
    override fun doAction(player: Player?) {
        var minimumDistance = 40
        for (i in index.indices) {
            val distanceToIndex = (40 + index[i] - player!!.position) % 40
            if (40 + index[i] - player.position < minimumDistance) {
                minimumDistance = distanceToIndex
            }
        }
        player!!.move(minimumDistance, board)
    }
}

internal class HouseRepairCard(private val perHouse: Int, private val perHotel: Int, text: String) :
    CollectCard(0, text) {
    private fun calculateFee(player: Player?): Int {
        var fee = 0
        for (p in player!!.ownColorGroupList) {
            if (p.numHouses == 5) {
                fee += perHotel
            } else if (p.numHouses > 0) {
                fee += perHouse * p.numHouses
            }
        }
        amount = fee
        return fee
    }

    override fun doAction(player: Player?) {
        player!!.addMoney(-calculateFee(player))
    }
}

internal class ToJailCard(private val jail: Jail) : Card("Go Directly To Jail") {
    override fun doAction(player: Player?) {
        jail.sendToJail(player)
    }
}

internal class OutOfJailCard : Card("Get Out Of Jail For Free") {
    override fun doAction(player: Player?) {
        player!!.outOfJailCards++
    }
}