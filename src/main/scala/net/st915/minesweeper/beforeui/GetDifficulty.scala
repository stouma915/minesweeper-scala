package net.st915.minesweeper.beforeui

import cats.effect.Sync
import net.st915.minesweeper.Difficulty
import net.st915.minesweeper.beforeui.application.*
import net.st915.minesweeper.beforeui.impl.*
import org.scalajs.dom.*

object GetDifficulty {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](implicit window: Window): F[Difficulty] = {
    // format: off
    implicit val _convertOptionToDifficulty: ConvertOptionToDifficulty[F] = ApplicativeConvertOptionToDifficulty[F]
    implicit val _getDifficultyParameter: GetDifficultyParameter[F] = SyncGetDifficultyParameter[F]
    // format: on

    GetDifficulty()
  }

  def apply[F[_]: Sync: ConvertOptionToDifficulty: GetDifficultyParameter]()(implicit
  window: Window): F[Difficulty] =
    for {
      diffParam <- GetDifficultyParameter[F].get
      diff <- ConvertOptionToDifficulty[F].convert(diffParam)
    } yield diff

}
