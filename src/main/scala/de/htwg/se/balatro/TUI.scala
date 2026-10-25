package de.htwg.se.balatro

import de.htwg.se.balatro.CardColor.*
import de.htwg.se.balatro.CardSymbol.*

class TUI {
  def createRender(): String = ??? //todo calls the other methods here and creates the full gameboard as string

  def createTitle(title: String, statusbar: String, color: String): String = {
    val spaces = statusbar.length / 2 - title.length / 2
    " " * spaces + Console.UNDERLINED + color + title + Console.RESET //use space x times and add title
  }

  def createStatusbar(): String = ??? //todo

  def createMenu(): String =
    """Menu

      |Use 'select <cardnumber>' or 'select <cardnumberA cardnumberB ...> to select cards
      |Selecting the same card again will unselect it
      |Card numbers are starting with 1 (to not confuse non techy users)
      |Use 'play' to ... well play your selected cards
      |Use 'discard' to discard your selected cards
      |Use 'menu' to open the settings and exit the game""".stripMargin

  def createHandView(cards: List[Card]): String =
    cards.map(card => createCardRender(card)).mkString(" ")

  def createCardRender(card: Card): String =
    s"${color2ConsoleColor(card.color)}${card.rank.toString.toLowerCase.capitalize}${symbol2String(card.symbol)}${Console.RESET}"

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
      case BLACK => Console.BLACK
    }
}
