package net.st915.minesweeper.ui.components

import cats.effect.Sync
import net.st915.minesweeper.Difficulty
import net.st915.minesweeper.ui.components.typeclasses.*
import net.st915.minesweeper.ui.components.instances.*
import net.st915.minesweeper.ui.consts.*
import net.st915.minesweeper.util.typeclasses.CanCreateDifficultyLink
import net.st915.minesweeper.util.instances.MonadCanCreateDifficultyLink
import org.scalajs.dom.*

object DifficultyLink {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](diff: Difficulty)(using HTMLDocument, Window): F[HTMLLinkElement] = {
    given CanCreateElement[F, HTMLLinkElement] = MonadCanCreateElementLink[F]
    given CanUpdateHyperlink[F] = SyncCanUpdateHyperlink[F]
    given CanUpdateTextContent[F] = SyncCanUpdateTextContent[F]

    given CanCreateDifficultyLink[F] = MonadCanCreateDifficultyLink[F]

    for {
      diffLink <- CanCreateElement[F, HTMLLinkElement].create
      _ <- CanUpdateTextContent[F].perform(diffLink, UIText(diff.displayName))

      hyperlink <- CanCreateDifficultyLink[F].create(diff)
      _ <- CanUpdateHyperlink[F].perform(diffLink, hyperlink)
    } yield diffLink
  }

}
