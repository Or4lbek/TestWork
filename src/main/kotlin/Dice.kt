class Dice {
    private var roll1 = 0
    private var roll2 = 0
    fun roll(): Int {
        print("Press enter to roll")
        Input.read()
        roll1 = (Math.random() * 6 + 1).toInt()
        roll2 = (Math.random() * 6 + 1).toInt()
        println("Rolled a $roll1 and $roll2")
        return roll1 + roll2
    }

    //uses value of the dice without rolling
    fun currentRoll(): Int {
        return roll1 + roll2
    }

    val isDouble: Boolean
        get() = roll1 == roll2
}