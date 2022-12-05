package net.st915.minesweeper.logic.refreshui.application

import net.st915.minesweeper.Coordinate
import org.scalajs.dom.*

object ShowMineIcon {

  def apply[F[_]: ShowMineIcon]: ShowMineIcon[F] = implicitly

}

trait ShowMineIcon[F[_]] {

  def perform(coord: Coordinate)(implicit document: HTMLDocument): F[Unit]

}
