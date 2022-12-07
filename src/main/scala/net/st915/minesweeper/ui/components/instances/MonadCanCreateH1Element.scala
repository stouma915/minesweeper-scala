package net.st915.minesweeper.ui.components.instances

import cats.Monad
import net.st915.minesweeper.ui.components.typeclasses.CanCreateH1Element
import org.scalajs.dom.*

class MonadCanCreateH1Element[F[_]: Monad] extends CanCreateH1Element[F] {

  override def create(using HTMLDocument): F[HTMLElement] =
    Monad[F].pure {
      summon[HTMLDocument]
        .createElement("h1")
        .asInstanceOf[HTMLElement]
    }

}
