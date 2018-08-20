# Minesweeper_Challenge
Using your favorite programming language, implement a console version of the
Minesweeper game, following these guidelines:

● As initial user input, your program must ask for the board’s height, width, and number
of mines (e.g. an input of “8 15 10” determines a 8x15 game board with 10 mines;
that is, 120 cells, with 10 random cells containing mines).

● After the initial input, the user must be presented with the empty game board on the
console, and a prompt for selecting a cell.

● The following characters are the ones you should use for the representation of the
board:

○ ‘.’ : Unselected cell (can be uncovered or marked)

○ ‘-’ : Disable cell (a cell that can not be modified by the user)

○ ‘*’ : Represents a mine

○ ‘P’ : Represents a flag, used when the user marks a cell (use your
imagination, it’s a flag)

○ ‘1’ .. ‘9’ : Represents the number of adjacent mines to an specific cell

○ ‘ ’ (space) : Separates each column

○ Example:

    . . . . . . . .
    . . . . . . . .
    . . 2 1 3 . . .
    . . 2 - 3 P . .
    . . 1 - 2 . . .
    . . 4 2 2 . . .
    . . . . . . . .
    . . . . . . . .
