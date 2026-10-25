package de.htwg.se.balatro.ui

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*
import de.htwg.se.balatro.Action.*

class ParserSpec extends AnyWordSpec {
  "Parser" should {
    "parse the menu command (no matter of spaces / case sensitivity)" in {
      val parser = Parser()
      parser.parse(" mEnu ") should be(OpenMenu)
    }
  }
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
      parser.parse("select   1  ") should be(Select(List(1)))
    }
  }
  "Parser" should {
    "parse the select command (no matter of spaces / case sensitivity) with multiple arguments" in {
      val parser = Parser()
      parser.parse("select   1  3 5 ") should be(Select(List(1, 3, 5)))
    }
  }
  "Parser" should {
    "detect invalid/malformed commands" in {
      val parser = Parser()
      parser.parse("spielen") should be(UnknownCommand)
      parser.parse("play 1 2") should be(UnknownCommand)
      parser.parse("discard 1 2") should be(UnknownCommand)
      parser.parse("select 99") should be(UnknownCommand)
      parser.parseSelect("select") should be(UnknownCommand) //with normal parse never reachable
    }
  }
}
