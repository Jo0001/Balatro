package de.htwg.se.balatro.view

import de.htwg.se.balatro.util.Action.*
import de.htwg.se.balatro.view.Parser
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class ParserSpec extends AnyWordSpec {
  "Parser" should {
    "parse the play command (no matter of spaces / case sensitivity)" in {
      val parser = Parser()
      parser.parse("Play") should be(Play)
    }
  }
  "Parser" should {
    "parse the discard command (no matter of spaces / case sensitivity)" in {
      val parser = Parser()
      parser.parse("discard  ") should be(Discard)
    }
  }
  "Parser" should {
    "parse the select command (no matter of spaces / case sensitivity) with one argument" in {
      val parser = Parser()
      parser.parse("select   1  ") should be(Select(List(0)))
    }
  }
  "Parser" should {
    "parse the select command (no matter of spaces / case sensitivity) with multiple arguments" in {
      val parser = Parser()
      parser.parse("select   1  3 5 8") should be(Select(List(0, 2, 4, 7)))
    }
  }
  "Parser" should {
    "parse the next command (no matter of case sensitivity)" in {
      val parser = Parser()
      parser.parse("neXt") should be(Next)
    }
  }
  "Parser" should {
    "parse the restart command (no matter of case sensitivity)" in {
      val parser = Parser()
      parser.parse("RESTART") should be(Restart)
    }
  }
  "Parser" should {
    "detect invalid/malformed commands" in {
      val parser = Parser()
      parser.parse("spielen") should be(UnknownCommand)
      parser.parse("play 1 2") should be(UnknownCommand)
      parser.parse("discard 1 2") should be(UnknownCommand)
      parser.parse("select 99") should be(UnknownCommand)
      parser.parse("select 12") should be(UnknownCommand)
      parser.parse("next 12") should be(UnknownCommand)
    }
  }
}
