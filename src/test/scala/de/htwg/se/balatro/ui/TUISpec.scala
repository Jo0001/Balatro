package de.htwg.se.balatro.ui

import de.htwg.se.balatro.CardColor.*
import de.htwg.se.balatro.CardSymbol.*
import de.htwg.se.balatro.ui.TUI
import de.htwg.se.balatro.{Card, CardSymbol, Rank}
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class TUISpec extends AnyWordSpec {
  "TUI" should {
    "have a title with correct indentation" in {
      val title = "Small Blind"
      val tui = TUI()
      val fullTitle = tui.createTitle(title, "<Score 20/300> || <Reward 3$> || <Ante 1/8> || <Round 1>", Console.RESET)
      val indentationSpaces = fullTitle.count(_ == ' ') - title.count(_ == ' ')
      indentationSpaces should be(23)
    }
  }
  "TUI" should {
    "have a statusbar" in {
      val tui = TUI()
      val bar = tui.createStatusbar(20, 300, 3, 1, 1)
      bar should be("<Score 20/300> || <Reward 3$> || <Ante 1/8> || <Round 1>")
    }
  }
  "TUI" should {
    "have a menu" in {
      val tui = TUI()
      tui.createMenu() should be(
        """Menu

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
      val tui = TUI()
      tui.symbol2String(DIAMONDS) should be("♦")
    }
  }
  "TUI" should {
    "have a spades render" in {
      val tui = TUI()
      tui.symbol2String(SPADES) should be("♠")
    }
  }
  "TUI" should {
    "have a hearts render" in {
      val tui = TUI()
      tui.symbol2String(HEARTS) should be("♥")
    }
  }
  "TUI" should {
    "have a clubs render" in {
      val tui = TUI()
      tui.symbol2String(CLUBS) should be("♣")
    }
  }

  "TUI" should {
    "have a mapping for the blue color render" in {
      val tui = TUI()
      tui.color2ConsoleColor(BLUE) should be(Console.BLUE)
    }
  }
  "TUI" should {
    "have a mapping for the yellow color render" in {
      val tui = TUI()
      tui.color2ConsoleColor(YELLOW) should be(Console.YELLOW)
    }
  }
  "TUI" should {
    "have a mapping for the red color render" in {
      val tui = TUI()
      tui.color2ConsoleColor(RED) should be(Console.RED)
    }
  }
  "TUI" should {
    "have a mapping for the black color render" in {
      val tui = TUI()
      tui.color2ConsoleColor(BLACK) should be(Console.BLACK + Console.WHITE_B)
    }
  }

  "TUI" should {
    "have a printable Yellow Diamonds Card" in {
      val tui = TUI()
      val card = Card(symbol = CardSymbol.DIAMONDS, rank = Rank.ACE)
      tui.createCardRender(card, false) should be(s"${Console.YELLOW}Ace♦${Console.RESET}")
    }
  }
  "TUI" should {
    "have a printable selected Yellow Diamonds Card" in {
      val tui = TUI()
      val card = Card(symbol = CardSymbol.DIAMONDS, rank = Rank.ACE)
      tui.createCardRender(card, true) should be(s"[${Console.YELLOW}Ace♦${Console.RESET}]")
    }
  }

  "TUI" should {
    "have a printable deck render" in {
      val tui = TUI()
      val cards = List(Card(symbol = CardSymbol.DIAMONDS, rank = Rank.ACE), Card(symbol = CardSymbol.HEARTS, rank = Rank.KING))
      val selectedIndices = List(0)
      tui.createHandView(cards, selectedIndices) should be(s"[${Console.YELLOW}Ace♦${Console.RESET}] ${Console.RED}King♥${Console.RESET}")
    }
  }

  "TUI" should {
    "have a unknown command error" in {
      val tui = TUI()
      tui.createCommandErrorMessage("ply") should be(s"${Console.RED}Unknown or misspelled command 'ply' ${Console.RESET}")
    }
  }
}
