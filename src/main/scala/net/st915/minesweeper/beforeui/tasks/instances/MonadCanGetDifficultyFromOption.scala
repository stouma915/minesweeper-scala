package net.st915.minesweeper.beforeui.tasks.instances

import cats.Monad
import net.st915.minesweeper.Consts.Difficulties
import net.st915.minesweeper.Difficulty
import net.st915.minesweeper.beforeui.tasks.typeclasses.CanGetDifficultyFromOption

class MonadCanGetDifficultyFromOption[F[_]: Monad] extends CanGetDifficultyFromOption[F] {

  override def get(opt: Option[String]): F[Difficulty] =
    Monad[F].pure {
      opt match
        case Some(str) =>
          Difficulties.All
            .find(_.id eq str)
            .getOrElse(Difficulties.Default)
        case None => Difficulties.Default
    }

}
