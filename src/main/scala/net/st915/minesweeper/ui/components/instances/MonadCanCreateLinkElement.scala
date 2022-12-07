package net.st915.minesweeper.ui.components.instances

import cats.Monad
import net.st915.minesweeper.ui.components.typeclasses.CanCreateLinkElement
import org.scalajs.dom.*

class MonadCanCreateLinkElement[F[_]: Monad] extends CanCreateLinkElement[F] {

  override def create(using HTMLDocument): F[HTMLLinkElement] =
    Monad[F].pure {
      summon[HTMLDocument]
        .createElement("a")
        .asInstanceOf[HTMLLinkElement]
    }

}
