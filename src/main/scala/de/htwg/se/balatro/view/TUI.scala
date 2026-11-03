package de.htwg.se.balatro.view

import de.htwg.se.balatro.controller.Controller
import de.htwg.se.balatro.util.CardColor.*
import de.htwg.se.balatro.util.CardSymbol.*
import de.htwg.se.balatro.model.{Card, Game}
import de.htwg.se.balatro.util.Action.UnknownCommand
import de.htwg.se.balatro.util.{CardColor, CardSymbol, Observer}

import scala.annotation.tailrec
import scala.io.StdIn.readLine

class TUI(controller: Controller) extends Observer {
  //all parts are already tested individually
  // $COVERAGE-OFF$
  def createRender(game: Game): String = {
    val statusbar = createStatusbar(game.currentRound.score, game.currentRound.blind.goal, game.currentRound.blind.reward, game.currentRound.blind.ante, game.roundCounter)
    val remaining = createRemaining(game.currentRound.discardCounter, game.currentRound.maxDiscards, game.currentRound.handCounter, game.currentRound.maxPlays)
    val livescore = createLiveScore(game.currentRound.currentHand().chipsTotal, game.currentRound.currentHand().mult, game.currentRound.currentHand().name)
    s"${createTitle(game.currentRound.blind.blindType.toString, statusbar, Console.BLUE_B)}\n$statusbar\n${useSpace(statusbar.length, List(livescore, remaining))}\n\n${createHandView(statusbar, game.currentRound.handcards, game.currentRound.selected)}\n\n${createMenu()}"
  }
  // $COVERAGE-ON$

  def createTitle(title: String, statusbar: String, color: String): String = {
    val spaces = statusbar.length / 2 - title.length / 2
    " " * spaces + Console.UNDERLINED + color + title + Console.RESET //use space x times and add title
  }

  def createStatusbar(current: Int, goal: Int, reward: Int, ante: Int, round: Int): String =
    s"<Score $current/$goal> || <Reward $reward$$> || <Ante $ante/8> || <Round $round>"

  def createLiveScore(chips: Int, mult: Int, name: String): String =
    s"${Console.BLUE_B}$chips${Console.RESET}x${Console.RED_B}$mult${Console.RESET} $name"

  def createRemaining(discardCounter: Int, maxDiscards: Int, handCounter: Int, maxPlays: Int): String =
    s"Hands:${Console.BLUE_B}${maxPlays - handCounter}${Console.RESET} Discards:${Console.RED_B}${maxDiscards - discardCounter}${Console.RESET}"

  def createMenu(): String =
    s"""${Console.UNDERLINED}Menu${Console.RESET}
       |Use 'select <cardnumber>' or 'select <cardnumberA cardnumberB ...> to select cards
       |Selecting the same card again will unselect it
       |Card numbers are starting with 1 (to not confuse non techy users)
       |Use 'play' to ... well play your selected cards
       |Use 'discard' to discard your selected cards
       |Use 'menu' to open the settings and exit the game""".stripMargin

  def createHandView(statusbar: String, cards: List[Card], selectedIndices: List[Int]): String =
    useSpace(statusbar.length, cards.zipWithIndex.map(card => createCardRender(card._1, selectedIndices.contains(card._2))))

  def createCardRender(card: Card, selected: Boolean): String =
    if (selected) {
      s"[${color2ConsoleColor(card.color)}${card.rank.toString.toLowerCase.capitalize}${symbol2String(card.symbol)}${Console.RESET}]"
    } else {
      s"${color2ConsoleColor(card.color)}${card.rank.toString.toLowerCase.capitalize}${symbol2String(card.symbol)}${Console.RESET}"
    }

  def createCommandErrorMessage(misspelled: String): String =
    s"${Console.RED}Unknown or misspelled command '$misspelled' ${Console.RESET}"

  def useSpace(length: Int, elements: List[String]): String =
    val ansiRegex = "\u001B\\[[0-?]*[ -/]*[@-~]"
    val visibleLength = elements.map(_.replaceAll(ansiRegex, "").length).sum
    val spaces = length - visibleLength
    val gaps = elements.length - 1
    val spacesPerGap = if (gaps > 0) spaces / gaps else 0

    elements.zipWithIndex.map { (element, i) =>
      element + (if (i < gaps) " " * spacesPerGap else "")
    }.mkString


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

  def color2ConsoleColor(cardColor: CardColor): String = {
    cardColor match {
      case BLUE => Console.BLUE
      case YELLOW => Console.YELLOW
      case RED => Console.RED
      case BLACK => Console.BLACK + Console.WHITE_B
    }
  }

  override def update(): Unit = {
    println(createRender(controller.game))
    print(">")
  }

  val parser = Parser()

  def run(): Unit = {
    update() //initial render
    runloop()
  }


  @tailrec
  final def runloop(): Unit =
    val line = readLine()
    parser.parse(line) match {
      case UnknownCommand =>
        println(createCommandErrorMessage(line))
        print(">")
      case action =>
        controller.handle(action)
    }
    runloop()
}
  

