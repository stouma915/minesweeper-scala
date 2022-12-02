package net.st915.minesweeper.ui.component.application

import cats.effect.unsafe.IORuntime
import org.scalajs.dom.*

object ToggleFlagModeButton {

  def apply[F[_]: ToggleFlagModeButton]: ToggleFlagModeButton[F] = implicitly

}

trait ToggleFlagModeButton[F[_]] {

  def create(implicit document: HTMLDocument, runtime: IORuntime): F[HTMLDivElement]

}
