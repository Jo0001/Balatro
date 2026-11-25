package de.htwg.se.balatro.controller

import de.htwg.se.balatro.model.Game
import de.htwg.se.balatro.util.*
import scala.util.{Success, Failure}

class Controller(startGame: Game = Game.start(), gameStorage: GameStorageStrategy = new MockGameStorageStrategy()) extends Observable {
  var currentGame: Game = gameStorage.load().getOrElse(startGame)
  var currentState: GameState = PlayingState()
  var lastError: Option[RoundError] = None
  private val undoManager = UndoManager()

  def gameSnapshot: GameSnapshot = GameSnapshot(
    blindName = currentGame.currentRound.blind.blindType.toString,
    score = currentGame.currentRound.score,
    goal = currentGame.currentRound.blind.goal,
    reward = currentGame.currentRound.blind.reward,
    ante = currentGame.currentRound.blind.ante,
    maxAnte = currentGame.ante,
    roundCounter = currentGame.roundCounter,
    hands = currentGame.currentRound.remainingPlays,
    discards = currentGame.currentRound.remainingDiscards,
    chips = currentGame.currentRound.currentHand().chips,
    multiplier = currentGame.currentRound.currentHand().mult,
    chipsTotal = currentGame.currentRound.currentHand().chipsTotal,
    handName = currentGame.currentRound.currentHand().hand.toString,
    handcards = createHandCards(),
    phase = currentState.phase,
    lastError = lastError
  )

  def handle(action: Action): Unit = {
    action match {
      case Action.Undo =>
        undoManager.undoStep()
      case Action.Redo =>
        undoManager.redoStep()
      case Action.Select(_) =>
        applyDirectly(action)
      case Action.Restart =>
        applyDirectly(action)
        undoManager.clear()
      case _ =>
        undoManager.doStep(new HandleCommand(this, action))
    }
    notifyObservers()
    gameStorage.save(currentGame)
  }

  private def applyDirectly(action: Action): Unit = {
    currentState.handle(action, currentGame) match {
      case Success((game, state)) =>
        currentGame = game
        currentState = state
        lastError = None
      case Failure(error: RoundError) =>
        lastError = Some(error)
      case Failure(other) =>
        throw other
    }
  }

  def createHandCards(): List[CardSnapshot] = {
    currentGame.currentRound.handcards.zipWithIndex.map { (card, index) =>
      CardSnapshot(selected = currentGame.currentRound.selected.contains(index), rank = card.rank.toString, symbol = card.symbol, color = card.color)
    }
  }

}