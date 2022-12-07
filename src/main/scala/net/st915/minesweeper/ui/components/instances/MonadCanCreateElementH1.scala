package net.st915.minesweeper.ui.components.instances

import cats.Monad
import net.st915.minesweeper.ui.components.typeclasses.CanCreateElement
import org.scalajs.dom.*

class MonadCanCreateElementH1[F[_]: Monad] extends CanCreateElement[F, HTMLElement] {

  override def create(using HTMLDocument): F[HTMLElement] =
    Monad[F].pure {
      summon[HTMLDocument]
        .createElement("div")
        .asInstanceOf[HTMLElement]
    }

}
