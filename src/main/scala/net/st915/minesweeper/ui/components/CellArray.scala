package net.st915.minesweeper.ui.components

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.ui.components.instances.*
import net.st915.minesweeper.ui.components.typeclasses.*
import net.st915.minesweeper.ui.consts.CSSClasses
import net.st915.minesweeper.{Coordinate, Difficulty}
import org.scalajs.dom.*

object CellArray {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*
  import cats.syntax.traverse.*

  def wired[F[_]: Sync](diff: Difficulty)(using HTMLDocument, IORuntime): F[HTMLDivElement] = {
    given CanAppendElement[F] = SyncCanAppendElement[F]
    given CanCreateElement[F, HTMLDivElement] = MonadCanCreateElementDiv[F]
    given CanUpdateElementClass[F] = SyncCanUpdateElementClass[F]

    for {
      containerDiv <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(containerDiv, CSSClasses.CellArray)

      lines <- (0 until diff.height)
        .toList
        .map { y => CellLine.wired[F](y, diff) }
        .sequence
      _ <- lines
        .map { line => CanAppendElement[F].perform(containerDiv, line) }
        .sequence
    } yield containerDiv
  }

}
