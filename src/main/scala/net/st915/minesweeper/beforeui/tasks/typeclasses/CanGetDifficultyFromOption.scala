package net.st915.minesweeper.beforeui.tasks.typeclasses

import net.st915.minesweeper.Difficulty

object CanGetDifficultyFromOption {

  def apply[F[_]](using ev: CanGetDifficultyFromOption[F]): CanGetDifficultyFromOption[F] = ev

}

trait CanGetDifficultyFromOption[F[_]] {

  def get(opt: Option[String]): F[Difficulty]

}
