package de.htwg.se.balatro.util

import de.htwg.se.balatro.model.Game
import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}
import scala.util.{Try, Using}

class FileGameStorageStrategy(path: String = "savegame.ser") extends GameStorageStrategy {
  def save(game: Game): Try[Unit] =
    Using(new ObjectOutputStream(new FileOutputStream(path))) { oos =>
      oos.writeObject(game)
    }

  def load(): Try[Game] =
    Using(new ObjectInputStream(new FileInputStream(path))) { ois =>
      ois.readObject().asInstanceOf[Game]
    }
}