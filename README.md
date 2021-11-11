# Tic Tac Toe
Simple Tic Tac Toe game made with Android Studio.

* Really basic game made with android studio.
* The computer is making random movements, so it's really easy to beat him.

![Image](https://github.com/giuraionut/android-tictactoe/blob/main/presentation/img.png)

* We generate a matrix of 3x3 buttons with no text.
* When the player clicks a button we add `X` as Text.
* After the player clicks a button computer makes a random move.
  * We create a list with all the positions of the 3x3 matrix.
  * If the player clicked on a position, we remove it from the matrix
  * Computer is using `Random` to pick a position from list, this way he will never click where player clicked already

### Win Condition
* To check if someone win, we have a `Player class`
* Player class has access to `Matrix class` which contains a List with the winnable states of the game.
* When a player makes a move we add the position to a list.
* After each move we check if the list of moves made by the player is equal to any of the lists from `Matrix class`
* In case we got a winner we restart the game and increase the score.
