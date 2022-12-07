package net.st915.minesweeper.ui.components

import cats.Monad
import net.st915.minesweeper.ui.components.typeclasses.*
import net.st915.minesweeper.ui.components.instances.*
import org.scalajs.dom.*

object InformationText {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Monad](using HTMLDocument): F[HTMLDivElement] = {
    given CanCreateElement[F, HTMLDivElement] = MonadCanCreateElementDiv[F]
    given CanCreateElement[F, HTMLElement] = MonadCanCreateElementH1[F]

    for {
      containerDiv <- CanCreateElement[F, HTMLDivElement].create
    } yield containerDiv
  }

}
