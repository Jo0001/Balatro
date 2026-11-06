package de.htwg.se.balatro.model

import de.htwg.se.balatro.controller.Controller
import de.htwg.se.balatro.model.Game
import de.htwg.se.balatro.util.*
import de.htwg.se.balatro.util.Action.*
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

import scala.util.Random

class ControllerSpec extends AnyWordSpec with Matchers {

  private def freshController(): Controller =
    new Controller(Game.start(new Random(42)))

  "Controller" should {

    "save the handed over game" in {
      val game = Game.start(new Random(42))

      val controller = new Controller(game)

      controller.currentGame shouldBe game
    }

    "select and deselect cards with select()" in {
      val controller = freshController()

      controller.handle(Select(List(0, 2)))

      controller.currentGame.currentRound.selected.toSet shouldBe
        Set(0, 2)

      controller.handle(Select(List(0)))

      controller.currentGame.currentRound.selected.toSet shouldBe
        Set(2)
    }

    "discard selected cards with discard()" in {
      val controller = freshController()
      controller.handle(Select(List(0, 2)))
      val before = controller.currentGame.currentRound

      controller.handle(Discard)

      val after = controller.currentGame.currentRound

      after.discardCounter shouldBe (before.discardCounter + 1)
      after.selected shouldBe empty
      after.handcards.size shouldBe before.handcards.size
      after.score shouldBe before.score
      after.handCounter shouldBe before.handCounter
    }

    "play selected cards with play()" in {
      val controller = freshController()
      controller.handle(Select(List(0, 2)))
      val before = controller.currentGame.currentRound
      val handScore = before.currentHand().scoreTotal

      controller.handle(Play)

      val after = controller.currentGame.currentRound

      after.handCounter shouldBe (before.handCounter + 1)
      after.score shouldBe (before.score + handScore)
      after.selected shouldBe empty
      after.discardCounter shouldBe before.discardCounter
    }

    "leave the game in its current state when OpenMenu or UnknownCommand is called" in {
      List(OpenMenu, UnknownCommand).foreach { action =>
        val controller = freshController()
        val before = controller.currentGame

        controller.handle(action)

        controller.currentGame shouldBe before
      }
    }

    "Inform Observer one time everytime something changes" in {
      val actions: List[Action] =
        List(Select(List(0, 2)), Discard, Play,
          OpenMenu, UnknownCommand)

      actions.foreach { action =>
        val controller = freshController()
        var updates = 0
        var observedGame: Option[Game] = None

        val observer = new Observer {
          override def update(): Unit = {
            updates += 1
            observedGame = Some(controller.currentGame)
          }
        }

        controller.add(observer)
        controller.handle(action)

        updates shouldBe 1
        observedGame shouldBe Some(controller.currentGame)
      }
    }

    "transfer data into Snapshot" in {
      val controller = freshController()
      val initial = controller.currentGame

      // Bewusst Werte verwenden, die nicht den Startwerten entsprechen.
      controller.currentGame = initial.copy(
        roundCounter = 3,
        currentRound = initial.currentRound.copy(
          score = 120,
          handCounter = 2,
          discardCounter = 1
        )
      )

      val snapshot = controller.gameSnapshot
      val game = controller.currentGame
      val round = game.currentRound

      snapshot.score shouldBe round.score
      snapshot.roundCounter shouldBe game.roundCounter
      snapshot.hands shouldBe round.remainingPlays
      snapshot.discards shouldBe round.remainingDiscards

      snapshot.blindName shouldBe round.blind.blindType.toString
      snapshot.goal shouldBe round.blind.goal
      snapshot.reward shouldBe round.blind.reward
      snapshot.ante shouldBe round.blind.ante
      snapshot.maxAnte shouldBe game.ante

      snapshot.chips shouldBe 0
      snapshot.multiplier shouldBe 0
      snapshot.chipsTotal shouldBe 0
      snapshot.handName shouldBe "EMPTY"
    }

    "card information and selection transmitted into snapshot" in {
      val controller = freshController()
      controller.handle(Select(List(0, 2)))

      val cards = controller.currentGame.currentRound.handcards
      val expected = cards.zipWithIndex.map { case (card, index) =>
        CardSnapshot(
          selected = Set(0, 2).contains(index),
          rank = card.rank.toString,
          color = card.color,
          symbol = card.symbol
        )
      }

      controller.createHandCards() shouldBe expected
      controller.gameSnapshot.handcards shouldBe expected
    }
  }
}
