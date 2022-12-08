package net.st915.minesweeper.ui.components.typeclasses

import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.util.{ID, UIText}
import org.scalajs.dom.*

object CanCreateButton {

  def apply[F[_]](using ev: CanCreateButton[F]): CanCreateButton[F] = ev

}

trait CanCreateButton[F[_]] {

  def create(id: ID, text: UIText)(using HTMLDocument, IORuntime): F[HTMLDivElement]

}
