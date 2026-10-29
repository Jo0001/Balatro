package de.htwg.se.balatro

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

import scala.util.Random

class DeckSpec extends AnyWordSpec {
  "Deck" should {
    "have all possible cards" in {
      val deck = Deck()
      deck.deck.size should be(52)
    }
  }
  "Deck" should {
    "have a method to draw x cards" in {
      val deck = Deck()
      val (drawn, newDeck) = deck.draw(Random, 5)
      drawn.size should be(5)
      newDeck.deck.size should be(47)
      newDeck.olddeck should not be (deck.olddeck)
    }
  }
}
