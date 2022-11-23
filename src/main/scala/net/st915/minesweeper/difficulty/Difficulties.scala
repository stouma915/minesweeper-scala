package net.st915.minesweeper.difficulty

object Difficulties {

  final val Easy = Difficulty(
    "e",
    "Easy",
    9,
    9,
    10
  )
  final val Normal = Difficulty(
    "n",
    "Normal",
    16,
    16,
    40
  )
  final val Hard = Difficulty(
    "h",
    "Hard",
    30,
    16,
    99
  )
  final val Impossible = Difficulty(
    "i",
    "Impossible",
    9,
    9,
    67
  )

  final val All = List(
    Easy,
    Normal,
    Hard,
    Impossible
  )

  final val Default = Easy

}
