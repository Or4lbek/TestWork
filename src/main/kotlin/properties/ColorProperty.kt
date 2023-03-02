package properties

import Property

class ColorProperty(
    name: String?,
    @JvmField val group: Group,
    price: Int,
    rent: Int, //rent based on number of houses
    private val rent1: Int,
    private val rent2: Int,
    private val rent3: Int,
    private val rent4: Int,
    private val rentH: Int
) : Property(name, price, rent) {
    var numHouses = 0 //number of houses currently on property
        private set
    @JvmField
    var houseCost = 0

    enum class Group(@JvmField val maxInGroup: Int) {
        BROWN(2), SKY(3), PINK(3), ORANGE(3), RED(3), YELLOW(3), GREEN(3), BLUE(2)
    }

    init {
        houseCost = when (group) {
            Group.BROWN, Group.SKY -> 50
            Group.PINK, Group.ORANGE -> 100
            Group.RED, Group.YELLOW -> 150
            Group.GREEN, Group.BLUE -> 200
            else -> -1
        }
    }

    fun addHouse() {
        owner.addMoney(-houseCost)
        numHouses++
        if (numHouses == 5) {
            println("Purchased a hotel on $name for $houseCost")
        } else {
            println("Purchased a house on $name for $houseCost")
        }
    }

    override fun getRent(): Int {
        var rent = 0
        when (numHouses) {
            0 -> {
                rent = super.getRent()
                if (owner.ownsGroup(group)) {
                    rent *= 2
                }
            }

            1 -> rent = rent1
            2 -> rent = rent2
            3 -> rent = rent3
            4 -> rent = rent4
            5 -> rent = rentH
        }
        return rent
    }
}