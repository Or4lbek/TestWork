package consts

import Dice
import Property

class Utility(name: String?, private val dice: Dice) : Property(name, 150, 0) {
    override fun getRent(): Int {
        val rent: Int
        val roll = dice.currentRoll()
        rent = when (owner.numUtilities) {
            1 -> 4 * roll
            2 -> 10 * roll
            else -> -1
        }
        return rent
    }
}