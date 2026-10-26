package de.htwg.se.balatro

case class Card(symbol: CardSymbol, rank: CardRank):
  val value = rank match
    case CardRank.ACE => 11
    case CardRank.KING | CardRank.QUEEN | CardRank.JACK | CardRank.TEN => 10
    case CardRank.NINE => 9
    case CardRank.EIGHT => 8
    case CardRank.SEVEN => 7
    case CardRank.SIX => 6
    case CardRank.FIVE => 5
    case CardRank.FOUR => 4
    case CardRank.THREE => 3
    case CardRank.TWO => 2

  // ♠ spades = Black, ♣clubs = Blue, ♥ hearts = Red, ♦ diamonds = Yellow
  val color = symbol match
    case CardSymbol.SPADES => CardColor.BLACK
    case CardSymbol.CLUBS => CardColor.BLUE
    case CardSymbol.HEARTS => CardColor.RED
    case CardSymbol.DIAMONDS => CardColor.YELLOW
