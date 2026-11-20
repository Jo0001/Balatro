
package de.htwg.se.balatro.controller

import de.htwg.se.balatro.model.BlindType.Small
import de.htwg.se.balatro.model.CardRank.ACE
import de.htwg.se.balatro.model.{Blind, Card, Game, Round}
import de.htwg.se.balatro.util.Action.{Discard, Play, Restart, Select}
import de.htwg.se.balatro.util.CardSymbol.DIAMONDS
import de.htwg.se.balatro.util.Phase.GameOver
import org.scalatest.matchers.should.Matchers.{a, should, shouldBe}
import org.scalatest.wordspec.AnyWordSpec

class GameOverStateSpec extends AnyWordSpec {

  val round = Round(
    handcards = List(Card(DIAMONDS, ACE)),
    blind = Blind(Small, reward = 3, goal = 100, ante = 1),
    handCounter = 4,
    score = 10,
    selected = List(0)
  )
  val game = Game(currentRound = round)

  "GameOverState" should {
    val state = GameOverState()

    "have its phase on GameOver" in {
      state.phase shouldBe GameOver
    }

    List(Play, Discard, Select(List(0))).foreach { action =>
      s"ignore $action and keep the finished game on screen" in {
        val (newGame, newState) = state.handle(action, game).get
        newGame shouldBe game
        newState shouldBe state
      }
    }

    "start a fresh game on Restart" in {
      val (newGame, _) = state.handle(Restart, game).get
      newGame.currentRound.handCounter shouldBe 0
      newGame.currentRound.score shouldBe 0
      newGame.currentRound.handcards.size shouldBe 8
      newGame.roundCounter shouldBe 1
    }

    "go back to PlayingState on Restart" in {
      state.handle(Restart, game).get._2 shouldBe a[PlayingState]
    }
  }
}
 