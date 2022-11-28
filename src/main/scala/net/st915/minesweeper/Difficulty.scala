package net.st915.minesweeper

case class Difficulty(
    id: String,
    displayName: String,
    width: Int,
    height: Int,
    numOfMines: Int
)
