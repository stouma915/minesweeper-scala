package net.st915.minesweeper.logic.refreshui.application

import net.st915.minesweeper.Coordinate
import org.scalajs.dom.*

object HideMineIcon {

  def apply[F[_]: HideMineIcon]: HideMineIcon[F] = implicitly

}

trait HideMineIcon[F[_]] {

  def perform(coord: Coordinate)(implicit document: HTMLDocument): F[Unit]

}
