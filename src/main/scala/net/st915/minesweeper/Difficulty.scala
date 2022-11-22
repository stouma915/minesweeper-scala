package net.st915.minesweeper

object Difficulty {

  final val Easy = Difficulty(
    "easy",
    9,
    9,
    10
  )
  final val Normal = Difficulty(
    "normal",
    16,
    16,
    40
  )
  final val Hard = Difficulty(
    "hard",
    30,
    16,
    99
  )
  final val Impossible = Difficulty(
    "impossible",
    9,
    9,
    67
  )

  final val Difficulties = Seq(
    Easy,
    Normal,
    Hard,
    Impossible
  )

  final val Default = Easy

}

case class Difficulty(
    name: String,
    width: Int,
    height: Int,
    numOfMines: Int
)
