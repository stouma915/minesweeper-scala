package net.st915.minesweeper.ui.component.application

import org.scalajs.dom.*

object DifficultySelector {

  def apply[F[_]: DifficultySelector]: DifficultySelector[F] = implicitly

}

trait DifficultySelector[F[_]] {

  def create(implicit document: HTMLDocument, window: Window): F[HTMLDivElement]

}
