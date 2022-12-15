package net.st915.minesweeper.ui.components.instances

import cats.Monad
import net.st915.dom.HTMLH1Element
import net.st915.minesweeper.ui.components.typeclasses.CanCreateElement
import org.scalajs.dom.*

class MonadCanCreateElementH1[F[_]: Monad] extends CanCreateElement[F, HTMLH1Element] {

  override def create(using HTMLDocument): F[HTMLH1Element] =
    Monad[F].pure {
      summon[HTMLDocument]
        .createElement("h1")
        .asInstanceOf[HTMLH1Element]
    }

}