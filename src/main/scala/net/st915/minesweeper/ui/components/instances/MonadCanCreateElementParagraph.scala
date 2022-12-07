package net.st915.minesweeper.ui.components.instances

import cats.Monad
import net.st915.minesweeper.ui.components.typeclasses.CanCreateElement
import org.scalajs.dom.*

class MonadCanCreateElementParagraph[F[_]: Monad] extends CanCreateElement[F, HTMLParagraphElement] {

  override def create(using HTMLDocument): F[HTMLParagraphElement] =
    Monad[F].pure {
      summon[HTMLDocument]
        .createElement("p")
        .asInstanceOf[HTMLParagraphElement]
    }

}
