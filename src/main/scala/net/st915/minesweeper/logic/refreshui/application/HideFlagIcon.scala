package net.st915.minesweeper.logic.refreshui.application

import net.st915.minesweeper.Coordinate
import org.scalajs.dom.*

object HideFlagIcon {

  def apply[F[_]: HideFlagIcon]: HideFlagIcon[F] = implicitly

}

trait HideFlagIcon[F[_]] {

  def perform(coord: Coordinate)(implicit document: HTMLDocument): F[Unit]

}
