package net.st915.minesweeper.beforeui.impl

import cats.Applicative
import net.st915.minesweeper.beforeui.application.ConvertOptionToDifficulty
import net.st915.minesweeper.{Difficulties, Difficulty}

class ApplicativeConvertOptionToDifficulty[F[_]: Applicative] extends ConvertOptionToDifficulty[F] {

  override def convert(option: Option[String]): F[Difficulty] =
    Applicative[F].pure {
      option match
        case Some(str) =>
          Difficulties.All
            .find(_.id eq str)
            .getOrElse(Difficulties.Default)
        case None => Difficulties.Default
    }

}