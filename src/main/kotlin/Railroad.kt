class Railroad(name: String?) : Property(name, 200, 0) {
    override fun getRent(): Int {
        val rent: Int = when (owner.numRailroads) {
            1 -> 25
            2 -> 50
            3 -> 100
            4 -> 200
            else -> -1
        }
        return rent
    }
}