package de.htwg.se.balatro.controller

import de.htwg.se.balatro.model.Game
import de.htwg.se.balatro.util.*
import de.htwg.se.balatro.util.Action.*

class Controller(startGame: Game = Game.start()) extends Observable {

  var currentGame: Game = startGame //todo avoid var

  def game: GameSnapshot = GameSnapshot(
    blindName = currentGame.currentRound.blind.toString,
    score = currentGame.currentRound.score,
    goal = currentGame.currentRound.blind.goal,
    reward = currentGame.currentRound.blind.reward,
    ante = currentGame.currentRound.blind.ante,
    roundCounter = currentGame.roundCounter,
    hands = currentGame.currentRound.handCounter,
    discards = currentGame.currentRound.discardCounter,
    chips = currentGame.currentRound.currentHand().chips,
    multiplier = currentGame.currentRound.currentHand().mult,
    chipsTotal = currentGame.currentRound.currentHand().chipsTotal,
    handName = currentGame.currentRound.currentHand().hand.toString,
    handcards = createHandCards()
  )

  def handle(action: Action): Unit = {
    currentGame = action match {
      case Discard => currentGame.copy(currentRound = currentGame.currentRound.discard())
      case Play => currentGame.copy(currentRound = currentGame.currentRound.play())
      //internal its 0 based but user uses 1 based indices
      case Select(indices) => currentGame.copy(currentRound = currentGame.currentRound.select(indices.map(e => e - 1)))
      case _ => currentGame //todo OpenMenu (and everything else) does not change the model yet
    }
    notifyObservers()
  }

  def createHandCards(): List[CardSnapshot] = {
    currentGame.currentRound.handcards.zipWithIndex.map { (card, index) =>
      CardSnapshot(selected = currentGame.currentRound.selected.contains(index), rank = card.rank.toString, symbol = card.symbol, color = card.color)
    }
  }

}