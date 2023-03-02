object Monopoly {
    @JvmStatic
    fun main(args: Array<String>) {
        val dice = Dice()
        val jail = Jail()
        val players = createPlayers(2)
        val board = Board(jail, dice, players)
        val game = Game(jail, dice, board, players)
        jail.setGame(game)
        game.turn(players[0])
    }

    private fun createPlayers(numPlayers: Int): ArrayList<Player> {
        val players = ArrayList<Player>()
        for (i in 1..numPlayers) {
            print("Player $i name: ")
            players.add(Player(Input.read()))
        }
        return players
    }
}