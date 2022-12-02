package net.st915.minesweeper.ui.component.application

import cats.effect.unsafe.IORuntime
import org.scalajs.dom.*

object RestartButton {

  def apply[F[_]: RestartButton]: RestartButton[F] = implicitly

}

trait RestartButton[F[_]] {

  def create(implicit document: HTMLDocument, runtime: IORuntime): F[HTMLDivElement]

}
