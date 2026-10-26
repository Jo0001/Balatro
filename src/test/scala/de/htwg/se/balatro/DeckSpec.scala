package de.htwg.se.balatro

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class DeckSpec extends AnyWordSpec{
"Deck" should {
  "have all possile cards" in {
    val deck = Deck()
    println(deck.deck)
  }
}
}
