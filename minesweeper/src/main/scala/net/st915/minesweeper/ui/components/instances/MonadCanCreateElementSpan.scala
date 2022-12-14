package net.st915.minesweeper.ui.components.instances

import cats.Monad
import net.st915.minesweeper.ui.components.typeclasses.CanCreateElement
import org.scalajs.dom.*

class MonadCanCreateElementSpan[F[_]: Monad] extends CanCreateElement[F, HTMLSpanElement] {

  override def create(using HTMLDocument): F[HTMLSpanElement] =
    Monad[F].pure {
      summon[HTMLDocument]
        .createElement("span")
        .asInstanceOf[HTMLSpanElement]
    }

}
