package de.htwg.se.balatro.util

enum RoundError(message: String) extends Exception(message):
  case NoCardsSelected extends RoundError("No cards selected")
  case TooManyCardsSelected extends RoundError("Too many cards selected")
  case NoPlaysRemaining extends RoundError("No plays left")
  case NoDiscardsRemaining extends RoundError("No discards left")