package de.htwg.se.balatro

  enum CardSymbol:
    case SPADES, DIAMONDS, HEARTS, CLUBS

  enum CardColor:
    case BLUE, YELLOW, RED, BLACK

  enum Rank:
    case ACE, KING, QUEEN, JACK, TEN, NINE, EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO

  case class Card(symbol: CardSymbol, rank: Rank):
    val value = rank match
      case Rank.ACE => 11
      case Rank.KING | Rank.QUEEN | Rank.JACK | Rank.TEN => 10
      case Rank.NINE => 9
      case Rank.EIGHT => 8
      case Rank.SEVEN => 7
      case Rank.SIX => 6
      case Rank.FIVE => 5
      case Rank.FOUR => 4
      case Rank.THREE => 3
      case Rank.TWO => 2

    // ♠ spades = Black, ♣clubs = Blue, ♥ hearts = Red, ♦ diamonds = Yellow
    val color = symbol match
      case CardSymbol.SPADES => CardColor.BLACK
      case CardSymbol.CLUBS => CardColor.BLUE
      case CardSymbol.HEARTS => CardColor.RED
      case CardSymbol.DIAMONDS => CardColor.YELLOW