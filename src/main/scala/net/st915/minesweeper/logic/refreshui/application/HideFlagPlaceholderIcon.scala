package net.st915.minesweeper.logic.refreshui.application

import net.st915.minesweeper.Coordinate
import org.scalajs.dom.*

object HideFlagPlaceholderIcon {

  def apply[F[_]: HideFlagPlaceholderIcon]: HideFlagPlaceholderIcon[F] = implicitly

}

trait HideFlagPlaceholderIcon[F[_]] {

  def perform(coord: Coordinate)(implicit document: HTMLDocument): F[Unit]

}
