package de.htwg.se.balatro.view

import de.htwg.se.balatro.util.Action.*
import de.htwg.se.balatro.util.Result.*
import de.htwg.se.balatro.view.Parser
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class ParserSpec extends AnyWordSpec {
  "Parser" should {
    "parse the play command (no matter of spaces / case sensitivity)" in {
      val parser = Parser()
      parser.parse("Play") should be(Ok(Play))
    }
  }
  "Parser" should {
    "parse the discard command (no matter of spaces / case sensitivity)" in {
      val parser = Parser()
      parser.parse("discard  ") should be(Ok(Discard))
    }
  }
  "Parser" should {
    "parse the select command (no matter of spaces / case sensitivity) with one argument" in {
      val parser = Parser()
      parser.parse("select   1  ") should be(Ok(Select(List(0))))
    }
  }
  "Parser" should {
    "parse the select command (no matter of spaces / case sensitivity) with multiple arguments" in {
      val parser = Parser()
      parser.parse("select   1  3 5 8") should be(Ok(Select(List(0, 2, 4, 7))))
    }
  }
  "Parser" should {
    "parse the next command (no matter of case sensitivity)" in {
      val parser = Parser()
      parser.parse("neXt") should be(Ok(Next))
    }
  }

  "Parser" should {
    "parse the undo command (no matter of case sensitivity)" in {
      val parser = Parser()
      parser.parse("unDo") should be(Ok(Undo))
    }
  }

  "Parser" should {
    "parse the redo command (no matter of case sensitivity)" in {
      val parser = Parser()
      parser.parse("ReDo") should be(Ok(Redo))
    }
  }

  "Parser" should {
    "parse the restart command (no matter of case sensitivity)" in {
      val parser = Parser()
      parser.parse("RESTART") should be(Ok(Restart))
    }
  }
  "Parser" should {
    "detect invalid/malformed commands" in {
      val parser = Parser()
      val unknown = (input: String) => Err(s"Unknown or misspelled command '$input'")
      parser.parse("spielen") should be(unknown("spielen"))
      parser.parse("play 1 2") should be(unknown("play 1 2"))
      parser.parse("discard 1 2") should be(unknown("discard 1 2"))
      parser.parse("next 12") should be(unknown("next 12"))
    }
  }
  "Parser" should {
    "explain what is wrong with an invalid select" in {
      val parser = Parser()
      parser.parse("select 99") should be(Err("Card numbers must be between 1 and 8"))
      parser.parse("select 12") should be(Err("Card numbers must be between 1 and 8"))
      parser.parse("select abc") should be(Err("Card numbers must be between 1 and 8"))
    }
  }
  "SelectHandler" should {
    "reject a select without a usable argument" in {
      val handler = SelectHandler(EndOfChain())
      handler.parseSelect("select") should be(Err("Use 'select <cardnumber> ...' to select cards")) //with normal parse never reachable
    }
  }
}
