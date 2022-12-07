package net.st915.minesweeper.ui.components

import cats.effect.Sync
import net.st915.minesweeper.Consts.Difficulties
import net.st915.minesweeper.ui.components.instances.*
import net.st915.minesweeper.ui.components.typeclasses.*
import net.st915.minesweeper.ui.consts.*
import org.scalajs.dom.*

object DifficultyLinks {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*
  import cats.syntax.traverse.*

  def wired[F[_]: Sync](using HTMLDocument, Window): F[HTMLDivElement] = {
    given CanAppendBR[F] = SyncCanAppendBR[F]
    given CanAppendElement[F] = SyncCanAppendElement[F]
    given CanCreateElement[F, HTMLDivElement] = MonadCanCreateElementDiv[F]

    for {
      containerDiv <- CanCreateElement[F, HTMLDivElement].create
      diffLinks <- Difficulties.All.map(DifficultyLink.wired[F]).sequence
      _ <- diffLinks
        .map { diffLink =>
          CanAppendElement[F].perform(containerDiv, diffLink) >>
            CanAppendBR[F].perform(containerDiv)
        }
        .sequence
    } yield containerDiv
  }

}
