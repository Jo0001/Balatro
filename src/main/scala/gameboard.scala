@main def gameboard(): Unit =
  val current = 20
  val goal = 300
  val reward = 3
  val ante = 1
  val round = 1
  val chips = 0
  val multiplier = 0
  val statusbar = s"<Score $current/$goal> || <Reward $reward$$> || <Ante $ante/8> || <Round $round>"
  println(createTitle("Small Blind", statusbar, Console.BLUE_B))
  println(statusbar + "\n\n")
  println(Console.BLUE_B + Console.BLACK + s"$chips" + Console.RESET + " x " +
    Console.RED_B + Console.BLACK + s"$multiplier" + Console.RESET)
  println(Console.BLUE + "Ace♣  " + Console.YELLOW + "Ace♦  " + Console.BLUE + "Queen♣  " +
    Console.RED + "10♥  9♥  " + Console.YELLOW + "9♦  " + Console.WHITE + "4♠  " + Console.BLUE + "2♣ " + Console.RESET)


  // ♠ = Black, ♣ = Blue, ♥ = Red, ♦ = Yellow
  println("\n\nMenu")
  //Alternative print("[0] Play hand [1-8] (de)select card [9] discard selected [-1] open settings")

  println("Use 'select <cardnumber>' or 'select <cardnumberA cardnumberB ...> to select cards")
  println("Selecting the same card again will unselect it")
  println("Card numbers are starting with 1 (to not confuse non techy users)")
  println("Use 'play' to ... well play your selected cards")
  println("Use 'discard' to discard your selected cards")
  println("Use 'menu' to open the settings and exit the game")

def createTitle(title: String, statusbar: String, color: String): String = {
  val spaces = statusbar.length / 2 - title.length / 2
  " " * spaces + Console.UNDERLINED + color + title + Console.RESET //use space x times and add title
}
