abstract class PlayerOption(private var message: String) {
    abstract fun action()
    override fun toString(): String {
        return message
    }
}