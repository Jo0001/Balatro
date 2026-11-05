package de.htwg.se.balatro.view

import de.htwg.se.balatro.controller.Controller
import de.htwg.se.balatro.util.CardColor.*
import de.htwg.se.balatro.util.CardSymbol.*
import de.htwg.se.balatro.model.{Card, CardRank}
import de.htwg.se.balatro.util.{CardColor, CardSnapshot, CardSymbol}
import de.htwg.se.balatro.view.TUI
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class TUISpec extends AnyWordSpec {
  val controller = Controller()
  "TUI" should {
    "have a title with correct indentation" in {
      val title = "Small Blind"
      val tui = TUI(controller)
      val fullTitle = tui.createTitle(title, "<Score 20/300> || <Reward 3$> || <Ante 1/8> || <Round 1>".length, Console.RESET)
      val indentationSpaces = fullTitle.count(_ == ' ') - title.count(_ == ' ')
      indentationSpaces should be(23)
    }
  }
  "TUI" should {
    "have a statusbar" in {
      val tui = TUI(controller)
      val bar = tui.createStatusbar(20, 300, 3, 1, 8, 1)
      bar should be("<Score 20/300> || <Reward 3$> || <Ante 1/8> || <Round 1>")
    }
  }
  "TUI" should {
    "have a livescore" in {
      val tui = TUI(controller)
      val score = tui.createLiveScore(10, 2, "Pair")
      score should be(s"${Console.BLUE_B}10${Console.RESET}x${Console.RED_B}2${Console.RESET} Pair")
    }
  }
  "TUI" should {
    "have a menu" in {
      val tui = TUI(controller)
      tui.createMenu() should be(
        s"""${Console.UNDERLINED}Menu${Console.RESET}
           |Use 'select <cardnumber>' or 'select <cardnumberA cardnumberB ...> to select cards
           |Selecting the same card again will unselect it
           |Card numbers are starting with 1 (to not confuse non techy users)
           |Use 'play' to ... well play your selected cards
           |Use 'discard' to discard your selected cards
           |Use 'menu' to open the settings and exit the game""".stripMargin)
    }
  }
  "TUI" should {
    "have a diamond render" in {
      val tui = TUI(controller)
      tui.symbol2String(DIAMONDS) should be("♦")
    }
  }
  "TUI" should {
    "have a spades render" in {
      val tui = TUI(controller)
      tui.symbol2String(SPADES) should be("♠")
    }
  }
  "TUI" should {
    "have a hearts render" in {
      val tui = TUI(controller)
      tui.symbol2String(HEARTS) should be("♥")
    }
  }
  "TUI" should {
    "have a clubs render" in {
      val tui = TUI(controller)
      tui.symbol2String(CLUBS) should be("♣")
    }
  }

  "TUI" should {
    "have a mapping for the blue color render" in {
      val tui = TUI(controller)
      tui.color2ConsoleColor(BLUE) should be(Console.BLUE)
    }
  }
  "TUI" should {
    "have a mapping for the yellow color render" in {
      val tui = TUI(controller)
      tui.color2ConsoleColor(YELLOW) should be(Console.YELLOW)
    }
  }
  "TUI" should {
    "have a mapping for the red color render" in {
      val tui = TUI(controller)
      tui.color2ConsoleColor(RED) should be(Console.RED)
    }
  }
  "TUI" should {
    "have a mapping for the black color render" in {
      val tui = TUI(controller)
      tui.color2ConsoleColor(BLACK) should be(Console.BLACK + Console.WHITE_B)
    }
  }

  "TUI" should {
    "have a printable Yellow Diamonds Card" in {
      val tui = TUI(controller)
      val cardSnapshot = CardSnapshot(selected = false, symbol = CardSymbol.DIAMONDS, color = CardColor.YELLOW, rank = "Ace")
      tui.createCardRender(cardSnapshot) should be(s"${Console.YELLOW}Ace♦${Console.RESET}")
    }
  }
  "TUI" should {
    "have a printable selected Yellow Diamonds Card" in {
      val tui = TUI(controller)
      val cardSnapshot = CardSnapshot(selected = true, symbol = CardSymbol.DIAMONDS, color = CardColor.YELLOW, rank = "Ace")
      tui.createCardRender(cardSnapshot) should be(s"[${Console.YELLOW}Ace♦${Console.RESET}]")
    }
  }

  "TUI" should {
    "have a printable deck render" in {
      val tui = TUI(controller)
      val cards = List(
        CardSnapshot(selected = true, symbol = CardSymbol.DIAMONDS, color = CardColor.YELLOW, rank = "Ace"),
        CardSnapshot(selected = false, symbol = CardSymbol.HEARTS, color = CardColor.RED, rank = "King")
      )
      val bar = tui.createStatusbar(20, 300, 3, 1, 8, 1)
      //yes those spaces have to be there!
      tui.createHandView(bar.length, cards) should be(s"[${Console.YELLOW}Ace♦${Console.RESET}]                                             ${Console.RED}King♥${Console.RESET}")
    }
  }

  "TUI" should {
    "have a unknown command error" in {
      val tui = TUI(controller)
      tui.createCommandErrorMessage("ply") should be(s"${Console.RED}Unknown or misspelled command 'ply' ${Console.RESET}")
    }
  }
  "TUI" should {
    "have remaining hands/discards view" in {
      val tui = TUI(controller)
      tui.createRemaining(3, 2) should be(s"Hands:${Console.BLUE_B}2${Console.RESET} Discards:${Console.RED_B}3${Console.RESET}")
    }
  }
  "TUI" should {
    "have util method to distribute spaces (which ignores Console colors)" in {
      val tui = TUI(controller)
      //yes those spaces have to be there!
      tui.useSpace(20, List(s"Hands:${Console.BLUE_B}2${Console.RESET}", s"Discards:${Console.RED_B}3${Console.RESET}")) should be(s"Hands:${Console.BLUE_B}2${Console.RESET}   Discards:${Console.RED_B}3${Console.RESET}")
    }
  }
  "TUI" should {
    "have util method to distribute spaces (which works with single elements too)" in {
      val tui = TUI(controller)
      //yes those spaces have to be there!
      tui.useSpace(20, List(s"test")) should be(s"test")
    }
  }
}
