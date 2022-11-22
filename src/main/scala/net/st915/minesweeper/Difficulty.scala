package net.st915.minesweeper

object Difficulty {

  val Easy = Difficulty(
    "easy",
    9,
    9,
    10
  )
  val Normal = Difficulty(
    "normal",
    16,
    16,
    40
  )
  val Hard = Difficulty(
    "hard",
    30,
    16,
    99
  )
  val Impossible = Difficulty(
    "impossible",
    9,
    9,
    67
  )

  val Difficulties = Seq(
    Easy,
    Normal,
    Hard,
    Impossible
  )

  val Default = Easy

}

case class Difficulty(
  name: String,
  width: Int,
  height: Int,
  numOfMines: Int
)
