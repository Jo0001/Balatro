package de.htwg.se.balatro.util

trait Command {
  def doStep(): Unit
  def undoStep(): Unit
  def redoStep(): Unit
}
