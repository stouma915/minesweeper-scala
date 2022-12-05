package net.st915.minesweeper.logic.refreshui.application

import org.scalajs.dom.*

object UpdateFlagPlaceModeButtonText {

  def apply[F[_]: UpdateFlagPlaceModeButtonText]: UpdateFlagPlaceModeButtonText[F] = implicitly

}

trait UpdateFlagPlaceModeButtonText[F[_]] {

  def update(text: String)(implicit document: HTMLDocument): F[Unit]

}
