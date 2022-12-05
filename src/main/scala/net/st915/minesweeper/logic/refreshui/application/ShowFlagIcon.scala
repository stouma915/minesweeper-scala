package net.st915.minesweeper.logic.refreshui.application

import net.st915.minesweeper.Coordinate
import org.scalajs.dom.*

object ShowFlagIcon {

  def apply[F[_]: ShowFlagIcon]: ShowFlagIcon[F] = implicitly

}

trait ShowFlagIcon[F[_]] {

  def perform(coord: Coordinate)(implicit document: HTMLDocument): F[Unit]

}
