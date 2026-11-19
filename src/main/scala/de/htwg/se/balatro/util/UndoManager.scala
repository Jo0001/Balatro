package de.htwg.se.balatro.util

class UndoManager {
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  // stacks gehen nicht mit val

  def doStep(c: Command): Unit = {
    c.doStep()
    undoStack = c :: undoStack
    redoStack = Nil
  }

  def undoStep(): Unit = undoStack match {
    case c :: rest =>
      c.undoStep()
      undoStack = rest
      redoStack = c :: redoStack
    case Nil =>
      ()
  }

  def redoStep(): Unit = redoStack match {
    case c :: rest =>
      c.redoStep()
      undoStack = c :: undoStack
      redoStack = rest
    case Nil =>
      ()
  }

  def clear(): Unit = {
    undoStack = Nil
    redoStack = Nil
  }
}
