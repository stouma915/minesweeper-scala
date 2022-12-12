package net.st915.minesweeper

import scala.concurrent.duration.*

object Consts {

  final val DifficultyParameter = "d"
  final val EventLoopInterval = 100.milliseconds

  object Difficulties {

    final val Easy = Difficulty(
      "easy",
      "Easy",
      9,
      9,
      10
    )
    final val Normal = Difficulty(
      "normal",
      "Normal",
      16,
      16,
      40
    )
    final val Hard = Difficulty(
      "hard",
      "Hard",
      30,
      16,
      99
    )
    final val Impossible = Difficulty(
      "impossible",
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

}
