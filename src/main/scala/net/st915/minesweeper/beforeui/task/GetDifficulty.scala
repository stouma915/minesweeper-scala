package net.st915.minesweeper.beforeui.task

import cats.effect.Sync
import net.st915.minesweeper.Difficulty
import net.st915.minesweeper.beforeui.application.*
import net.st915.minesweeper.beforeui.impl.*
import org.scalajs.dom.*

object GetDifficulty {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](implicit window: Window): F[Difficulty] = {
    implicit val _getDifficultyParameter: GetDifficultyParameter[F] = SyncGetDifficultyParameter[F]
    implicit val _convertOptionToDifficulty: ConvertOptionToDifficulty[F] =
      ApplicativeConvertOptionToDifficulty[F]

    GetDifficulty()
  }

  def apply[F[_]: Sync: ConvertOptionToDifficulty: GetDifficultyParameter]()(implicit
  window: Window): F[Difficulty] =
    for {
      diffParam <- GetDifficultyParameter[F].get
      diff <- ConvertOptionToDifficulty[F].convert(diffParam)
    } yield diff

}
