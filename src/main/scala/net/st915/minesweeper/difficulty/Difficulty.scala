package net.st915.minesweeper.difficulty

case class Difficulty(
    id: String,
    displayName: String,
    width: Int,
    height: Int,
    numOfMines: Int
)
