package de.htwg.se.balatro.ui

import de.htwg.se.balatro.CardColor.*
import de.htwg.se.balatro.CardSymbol.*
import de.htwg.se.balatro.{Card, CardColor, CardSymbol, Game}

class TUI {
  def createRender(game: Game): String = { //todo add test
    val statusbar = createStatusbar(game.currentRound.currentScore, game.currentRound.blind.goal, game.currentRound.blind.reward, game.currentRound.blind.ante, game.roundCounter)
    s"${createTitle(game.currentRound.blind.blindType.toString, statusbar, Console.BLUE_B)}\n${statusbar}\n\n${createHandView(game.currentRound.handcards, game.currentRound.selected)}\n\n${createMenu()}"
  }

  def createTitle(title: String, statusbar: String, color: String): String = {
    val spaces = statusbar.length / 2 - title.length / 2
    " " * spaces + Console.UNDERLINED + color + title + Console.RESET //use space x times and add title
  }

  def createStatusbar(current: Int, goal: Int, reward: Int, ante: Int, round: Int): String =
    s"<Score $current/$goal> || <Reward $reward$$> || <Ante $ante/8> || <Round $round>"

  def createMenu(): String =
    """Menu

      |Use 'select <cardnumber>' or 'select <cardnumberA cardnumberB ...> to select cards
      |Selecting the same card again will unselect it
      |Card numbers are starting with 1 (to not confuse non techy users)
      |Use 'play' to ... well play your selected cards
      |Use 'discard' to discard your selected cards
      |Use 'menu' to open the settings and exit the game""".stripMargin

  def createHandView(cards: List[Card], selectedIndices: List[Int]): String =
    cards.zipWithIndex.map(card => createCardRender(card._1, selectedIndices.contains(card._2))).mkString(" ")

  def createCardRender(card: Card, selected: Boolean): String =
    if (selected) {
      s"[${color2ConsoleColor(card.color)}${card.rank.toString.toLowerCase.capitalize}${symbol2String(card.symbol)}${Console.RESET}]"
    } else {
      s"${color2ConsoleColor(card.color)}${card.rank.toString.toLowerCase.capitalize}${symbol2String(card.symbol)}${Console.RESET}"
    }

  def createCommandErrorMessage(misspelled: String): String =
    s"${Console.RED}Unknown or misspelled command '$misspelled' ${Console.RESET}"

  def symbol2String(symbol: CardSymbol): String =
    symbol match {
      case DIAMONDS =>
        "♦"
      case SPADES =>
        "♠"
      case HEARTS =>
        "♥"
      case CLUBS =>
        "♣"
    }

  def color2ConsoleColor(cardColor: CardColor): String =
    cardColor match {
      case BLUE => Console.BLUE
      case YELLOW => Console.YELLOW
      case RED => Console.RED
      case BLACK => Console.BLACK + Console.WHITE_B
    }
}
