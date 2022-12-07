package net.st915.minesweeper.ui.components.instances

import cats.Monad
import net.st915.minesweeper.ui.components.typeclasses.CanCreateSpanElement
import org.scalajs.dom.*

class MonadCanCreateSpanElement[F[_]: Monad] extends CanCreateSpanElement[F] {

  override def create(using HTMLDocument): F[HTMLSpanElement] =
    Monad[F].pure {
      summon[HTMLDocument]
        .createElement("span")
        .asInstanceOf[HTMLSpanElement]
    }

}
