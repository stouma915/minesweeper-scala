package net.st915.minesweeper.beforeui.tasks.typeclasses

import org.scalajs.dom.*

object CanGetDifficultyParameter {

  def apply[F[_]](using ev: CanGetDifficultyParameter[F]): CanGetDifficultyParameter[F] = ev

}

trait CanGetDifficultyParameter[F[_]] {

  def get(using Window): F[Option[String]]

}
