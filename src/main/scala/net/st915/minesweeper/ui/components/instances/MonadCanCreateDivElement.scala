package net.st915.minesweeper.ui.components.instances

import cats.Monad
import net.st915.minesweeper.ui.components.typeclasses.CanCreateDivElement
import org.scalajs.dom.*

class MonadCanCreateDivElement[F[_]: Monad] extends CanCreateDivElement[F] {

  override def create(using HTMLDocument): F[HTMLDivElement] =
    Monad[F].pure {
      summon[HTMLDocument]
        .createElement("div")
        .asInstanceOf[HTMLDivElement]
    }

}
