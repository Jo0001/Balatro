package de.htwg.se.balatro

enum Action:
  case Select(indices: List[Int])
  case Play
  case Discard
  case OpenMenu
  case UnknownCommand