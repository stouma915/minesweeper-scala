package net.st915.minesweeper.ui.components.instances

import cats.Monad
import net.st915.minesweeper.ui.components.typeclasses.CanCreateElement
import org.scalajs.dom.*

class MonadCanCreateElementBR[F[_]: Monad] extends CanCreateElement[F, HTMLBRElement] {

  override def create(using HTMLDocument): F[HTMLBRElement] =
    Monad[F].pure {
      summon[HTMLDocument]
        .createElement("br")
        .asInstanceOf[HTMLBRElement]
    }

}
