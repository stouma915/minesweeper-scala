package net.st915.minesweeper.beforeui.tasks.instances

import cats.Monad
import net.st915.minesweeper.Difficulty
import net.st915.minesweeper.beforeui.tasks.typeclasses.CanGetDifficultyFromOption

class MonadCanGetDifficultyFromOption[F[_]: Monad] extends CanGetDifficultyFromOption[F] {

  override def get(opt: Option[String]): F[Difficulty] =
    Monad[F].pure {
      opt
        .map { str => Difficulty.All.find(_.id eq str) }
        .flatten
        .getOrElse(Difficulty.Default)
    }

}
