package net.st915.minesweeper.logic.refreshui.application

import net.st915.minesweeper.Coordinate
import org.scalajs.dom.*

object ShowFlagPlaceholderIcon {

  def apply[F[_]: ShowFlagPlaceholderIcon]: ShowFlagPlaceholderIcon[F] = implicitly

}

trait ShowFlagPlaceholderIcon[F[_]] {

  def perform(coord: Coordinate)(implicit document: HTMLDocument): F[Unit]

}
