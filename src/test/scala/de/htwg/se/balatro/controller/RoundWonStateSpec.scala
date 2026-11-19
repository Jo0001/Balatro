
package de.htwg.se.balatro.controller

import de.htwg.se.balatro.model.BlindType.{Big, Boss, Small}
import de.htwg.se.balatro.model.CardRank.ACE
import de.htwg.se.balatro.model.{Blind, BlindType, Card, Game, Player, Round}
import de.htwg.se.balatro.util.Action.{Discard, Next, Play, Select}
import de.htwg.se.balatro.util.CardSymbol.DIAMONDS
import de.htwg.se.balatro.util.Phase.RoundWon
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class RoundWonStateSpec extends AnyWordSpec {
  def wonGame(blindType: BlindType = Small, ante: Int = 1): Game =
    Game(
      player = Player(5),
      currentRound = Round(
        handcards = List(Card(DIAMONDS, ACE)),
        blind = Blind(blindType, reward = 3, goal = 100, ante = ante),
        handCounter = 2,
        score = 120,
        selected = List(0)
      )
    )

  "RoundWonState" should {
    val state = RoundWonState()
    val game = wonGame()

    "have its phase on RoundWon" in {
      state.phase shouldBe RoundWon
    }

    List(Play, Discard, Select(List(0))).foreach { action =>
      s"ignore $action and wait for the player" in {
        val (newGame, newState) = state.handle(action, game)
        newGame shouldBe game
        newState shouldBe state
      }
    }

    "go to PlayingState on Next" in {
      state.handle(Next, game)._2 shouldBe a[PlayingState]
    }

    "pay the blind reward on Next" in {
      state.handle(Next, game)._1.player.money shouldBe 8
    }

    "count the next round on Next" in {
      state.handle(Next, game)._1.roundCounter shouldBe 2
    }

    "start a fresh round on Next" in {
      val round = state.handle(Next, game)._1.currentRound
      round.score shouldBe 0
      round.handCounter shouldBe 0
      round.discardCounter shouldBe 0
      round.selected shouldBe empty
      round.handcards.size shouldBe 8
    }

    List((Small, 1, Big, 1), (Big, 1, Boss, 1), (Boss, 1, Small, 2)).foreach { (from, ante, to, nextAnte) =>
      s"move from $from (ante $ante) to $to (ante $nextAnte) on Next" in {
        val blind = state.handle(Next, wonGame(from, ante))._1.currentRound.blind
        blind.blindType shouldBe to
        blind.ante shouldBe nextAnte
      }
    }
  }
}
 