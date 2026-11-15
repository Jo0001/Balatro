package de.htwg.se.balatro.controller

import de.htwg.se.balatro.model.BlindType.Small
import de.htwg.se.balatro.model.CardRank.ACE
import de.htwg.se.balatro.model.{Blind, Card, Game, Round}
import de.htwg.se.balatro.util.Action.Play
import de.htwg.se.balatro.util.CardSymbol.DIAMONDS
import de.htwg.se.balatro.util.Phase.*
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.{a, should, shouldBe}
import org.scalatest.wordspec.AnyWordSpec

class PlayingStateSpec extends AnyWordSpec {
  "PlayingState" should {
    val state = PlayingState()
    "have its phase on Playing" in {
      state.phase should be(Playing)
    }
  }
  "PlayingState" should {
    val state = PlayingState()
    val round = Round(handcards = List(Card(DIAMONDS, ACE)), blind = Blind(Small, reward = 3, goal = 100, ante = 1), handCounter = 3, selected = List(0))
    val game = Game(currentRound = round)
    "transition to game over if remaining plays == 0" in {
      state.handle(Play, game)._2 shouldBe a[GameOverState]
    }
  }
  "PlayingState" should {
    val state = PlayingState()
    val round = Round(handcards = List(Card(DIAMONDS, ACE)), blind = Blind(Small, reward = 3, goal = 1, ante = 1), selected = List(0))
    val game = Game(currentRound = round)
    "transition to round won if the score is reached" in {
      state.handle(Play, game)._2 shouldBe a[RoundWonState]
    }
  }

}
