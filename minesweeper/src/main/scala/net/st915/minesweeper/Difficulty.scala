package net.st915.minesweeper

object Difficulty {

  object Easy extends Difficulty(
        id = "easy",
        displayName = "Easy",
        width = 9,
        height = 9,
        numOfMines = 10
      )
  object Normal extends Difficulty(
        id = "normal",
        displayName = "Normal",
        width = 16,
        height = 16,
        numOfMines = 40
      )
  object Hard extends Difficulty(
        id = "hard",
        displayName = "Hard",
        width = 30,
        height = 16,
        numOfMines = 99
      )
  object Impossible extends Difficulty(
        id = "impossible",
        displayName = "Impossible",
        width = 9,
        height = 9,
        numOfMines = 67
      )

  final val All = List(
    Easy,
    Normal,
    Hard,
    Impossible
  )

  final val Default = Easy

}

sealed case class Difficulty(
  id: String,
  displayName: String,
  width: Int,
  height: Int,
  numOfMines: Int
)
