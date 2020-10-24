# Camel-Up
## Player Choices
There are 5 main player choices that we have identified. These choices can occur at any turn of a leg. Each turn is instigated by a while loop in the game state main method. This while loop continues until the winner boolean is triggered at the end of a turn. Inside this while loop we have a counter int currentPlayer (0-4) that represents the current player. This int can be accessed in other classes using the getCurrentPlayer method.
### Choice 1: Rolling the Die
According to the number of dice that are still false for whether it has been rolled or not the pyramid randomizes which die is rolled and the number that gets rolled whether it be 1 or 2 or 3. The boolean for the die is changed to true so it cannot be rolled again in the same leg. The number it rolled is shown on the screen. The camel is moved corresponding to the roll. After the leg all the dice are reset.
### Choice 2: Game Bet Winner
If, in a turn, a player decides to bet a card for the race, the card is taken out of the Player’s raceBet ArrayList and placed in the Board Class’s gameCards ArrayList. Each game bet card has 3 parameters: type which determines if its a winbet or a lose bet, owner, which helps distribute points after the game is over, and the camel that is bet upon.
Adds a GameBet based on the player and which camel they think will win to the the GameWin (ArrayList) stored in the GameState class.
*There are two ArrayLists called GameWin and GameLost that hold GameBets which is a object that stores a player number and a camel number, when the game ends the ArrayList is consumed in order giving or taking money from the corresponding player based on the order.*
### Choice 3: End Game Bet Loser
Adds an GameBet based on the player and which camel they think will lose to the the GameLost (ArrayList) stored in the GameState class.
### Choice 4: Leg Bet
Drawing: When a card is drawn in a turn, it runs the legBet() method in Player which takes an int which represents the camel they want to bet on for the leg. A LegCard for that camel is added to the player’s legDeck (ArrayList) and is removed from the Board’s arraylist of legCards. This legCard is always removed from the front of the legDeck.
Reset: At the end of the leg, the LegCards will go through the MoneyIncrement (LegCard) (this uses the getCamelOrder order in the track class and the legCard object parameters to increment money by the right amount) to increment the player money. They then return to the legDeck and are placed in the correct order.
### Choice 5: Trap
Placing/Flipping:  If the owning player decides to place the card on his turn(graphics shown above), the trapPlace method in the tile class it run and the trap object is passed to the tile. The appropriate trap object parameters are now changed (location and moveMod), and the tile on which it is placed will turn boolean trapIsPlaced to true. If a player decides to flip the trap it’s boolean moveMod is changed and its color changes upon repaint. 
Reset after Leg:At the beginning of every leg, the player has a trap object that contains parameters for isPlaced, location (null until placed), boolean moveMod (null until places), and int owner.  When the player reset method is run (triggered by the pyramid class when the leg ends), all traps are erased from the tile ArrayList and location and moveMod for all traps are set back to null.
Landing on a trap: If a trap is landed upon the player moneyIncrement() method will be run based on the int owner and boolean moveMod fields of the respective trap card, and movement will be affected (this is done by the move method accessing the hasTrap() for the tile it is projected to land on and moving appropriately based on the moveMod of the trap that the tile contains).
