package de.htwg.se.balatro.util

enum Action:
  case Select(indices: List[Int])
  case Play
  case Discard
  case Next
  case OpenMenu
  case UnknownCommand