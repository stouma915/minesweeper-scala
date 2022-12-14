package net.st915.minesweeper.ui.components.instances

import cats.Monad
import net.st915.minesweeper.ui.components.typeclasses.CanCreateElement
import org.scalajs.dom.*

class MonadCanCreateElementLink[F[_]: Monad] extends CanCreateElement[F, HTMLLinkElement] {

  override def create(using HTMLDocument): F[HTMLLinkElement] =
    Monad[F].pure {
      summon[HTMLDocument]
        .createElement("a")
        .asInstanceOf[HTMLLinkElement]
    }

}
