package net.st915.minesweeper.ui.components.typeclasses

import net.st915.minesweeper.util.UIText
import org.scalajs.dom.*

object CanUpdateTextContent {

  def apply[F[_]](using ev: CanUpdateTextContent[F]): CanUpdateTextContent[F] = ev

}

trait CanUpdateTextContent[F[_]] {

  def perform(element: HTMLElement, newContent: UIText): F[Unit]

}
