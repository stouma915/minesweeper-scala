package net.st915.minesweeper.beforeui.application

import org.scalajs.dom.*

object GetDifficultyParameter {

  def apply[F[_]: GetDifficultyParameter]: GetDifficultyParameter[F] =
    implicitly

}

trait GetDifficultyParameter[F[_]] {

  def get(implicit window: Window): F[Option[String]]

}
