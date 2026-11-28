package de.htwg.se.balatro.util

object Exit {
  //extra just to completely ignore it in tests
  def exit(fake: Boolean = false): Unit = {
    if (!fake) {
      sys.exit(0)
    }
  }
}
