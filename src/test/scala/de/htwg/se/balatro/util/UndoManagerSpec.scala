package de.htwg.se.balatro.util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.collection.mutable.ListBuffer

class UndoManagerSpec extends AnyWordSpec with Matchers {

  class RecordingCommand(name: String, log: ListBuffer[String]) extends Command {
    override def doStep(): Unit = log += s"do $name"
    override def undoStep(): Unit = log += s"undo $name"
    override def redoStep(): Unit = log += s"redo $name"
  }

  def fixture() = (new UndoManager, ListBuffer[String]())

  "An UndoManager" when {

    "doStep is called" should {
      "execute the command" in {
        val (um, log) = fixture()
        um.doStep(new RecordingCommand("a", log))
        log.toList shouldBe List("do a")
      }
    }

    "undoStep is called" should {
      "do nothing on an empty stack" in {
        val (um, log) = fixture()
        noException should be thrownBy um.undoStep()
        log shouldBe empty
      }
      "undo the last command" in {
        val (um, log) = fixture()
        um.doStep(new RecordingCommand("a", log))
        um.undoStep()
        log.toList shouldBe List("do a", "undo a")
      }
      "undo commands in reverse order (LIFO)" in {
        val (um, log) = fixture()
        um.doStep(new RecordingCommand("a", log))
        um.doStep(new RecordingCommand("b", log))
        um.undoStep()
        um.undoStep()
        log.toList shouldBe List("do a", "do b", "undo b", "undo a")
      }
      "do nothing once everything is undone" in {
        val (um, log) = fixture()
        um.doStep(new RecordingCommand("a", log))
        um.undoStep()
        um.undoStep() // zweites Mal: Stack leer
        log.toList shouldBe List("do a", "undo a")
      }
    }

    "redoStep is called" should {
      "do nothing on an empty redo stack" in {
        val (um, log) = fixture()
        um.redoStep()
        log shouldBe empty
      }
      "do nothing without a preceding undo" in {
        val (um, log) = fixture()
        um.doStep(new RecordingCommand("a", log))
        um.redoStep()
        log.toList shouldBe List("do a")
      }
      "redo the last undone command" in {
        val (um, log) = fixture()
        um.doStep(new RecordingCommand("a", log))
        um.undoStep()
        um.redoStep()
        log.toList shouldBe List("do a", "undo a", "redo a")
      }
      "redo commands in original order" in {
        val (um, log) = fixture()
        um.doStep(new RecordingCommand("a", log))
        um.doStep(new RecordingCommand("b", log))
        um.undoStep()
        um.undoStep()
        um.redoStep()
        um.redoStep()
        log.toList shouldBe List("do a", "do b", "undo b", "undo a", "redo a", "redo b")
      }
      "put the redone command back on the undo stack" in {
        val (um, log) = fixture()
        um.doStep(new RecordingCommand("a", log))
        um.undoStep()
        um.redoStep()
        um.undoStep() // muss wieder möglich sein
        log.toList shouldBe List("do a", "undo a", "redo a", "undo a")
      }
    }

    "a new command is done after an undo" should {
      "discard the redo stack" in {
        val (um, log) = fixture()
        um.doStep(new RecordingCommand("a", log))
        um.undoStep()
        um.doStep(new RecordingCommand("b", log))
        um.redoStep() // "a" darf nicht mehr zurückkommen
        log.toList shouldBe List("do a", "undo a", "do b")
      }
    }

    "clear is called" should {
      "empty the undo stack" in {
        val (um, log) = fixture()
        um.doStep(new RecordingCommand("a", log))
        um.clear()
        um.undoStep()
        log.toList shouldBe List("do a")
      }
      "empty the redo stack" in {
        val (um, log) = fixture()
        um.doStep(new RecordingCommand("a", log))
        um.undoStep()
        um.clear()
        um.redoStep()
        log.toList shouldBe List("do a", "undo a")
      }
    }
  }
}
