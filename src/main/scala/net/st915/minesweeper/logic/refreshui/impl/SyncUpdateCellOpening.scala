package net.st915.minesweeper.logic.refreshui.impl

import cats.effect.Sync
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.logic.refreshui.application.*
import net.st915.minesweeper.ui.application.UpdateHTMLClassWithID
import net.st915.minesweeper.util.application.IDFactory
import org.scalajs.dom.*

class SyncUpdateCellOpening[F[_]: Sync: UpdateHTMLClassWithID: IDFactory]
    extends UpdateCellOpening[F] {

  import cats.syntax.flatMap.*

  override def update(coord: Coordinate, opening: Boolean)(implicit
  document: HTMLDocument): F[Unit] =
    IDFactory[F].cell(coord) >>= { id =>
      UpdateHTMLClassWithID[F].update(
        id,
        if (opening)
          CSSClass.OpenedCell
        else
          CSSClass.NotOpenedCell
      )
    }

}
