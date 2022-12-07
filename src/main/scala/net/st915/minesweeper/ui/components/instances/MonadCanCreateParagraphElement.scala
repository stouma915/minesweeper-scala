package net.st915.minesweeper.ui.components.instances

import cats.Monad
import net.st915.minesweeper.ui.components.typeclasses.CanCreateParagraphElement
import org.scalajs.dom.*

class MonadCanCreateParagraphElement[F[_]: Monad] extends CanCreateParagraphElement[F] {

  override def create(using HTMLDocument): F[HTMLParagraphElement] =
    Monad[F].pure {
      summon[HTMLDocument]
        .createElement("p")
        .asInstanceOf[HTMLParagraphElement]
    }

}
