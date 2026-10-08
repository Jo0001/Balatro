println("                      Small Blind")
println("<Score 0/300> || <Reward 3$> || <Ante 1/8> || <Round 1>")
println()
println("0 x 0")
println(Console.BLUE + "Ace ♣  " + Console.YELLOW + "Ace ♦  " + Console.BLUE + "Queen ♣  " +
  Console.RED + "10 ♥  9 ♥  " + Console.YELLOW + "4 ♦  " + Console.BLUE + "2 ♣" + Console.RESET)


// ♠ = Black, ♣ = Blue, ♥ = Red, ♦ = Yellow

println("Menu")
//Alternative print("[0] Play hand [1-8] (de)select card [9] discard selected [-1] open settings")

println("Use 'select <cardnumber>' or 'select <cardnumberA cardnumberB ...> to select cards")
println("Selecting the same card again will unselect it")
println("Card numbers are starting with 1 (to not confuse non techy users)")
println("Use 'play' to ... well play your selected cards")
println("Use 'discard' to discard your selected cards")
println("Use 'menu' to open the settings and exit the game")