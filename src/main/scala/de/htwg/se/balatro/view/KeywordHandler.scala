package de.htwg.se.balatro.view

import de.htwg.se.balatro.util.Action

class KeywordHandler(keyword: String, action: Action, next: InputHandler) extends ChainHandler(next) {
  def tryHandle(input: String): Option[Action] = Option.when(input == keyword)(action)
}

