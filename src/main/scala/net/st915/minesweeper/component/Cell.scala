package net.st915.minesweeper.component

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.implicits.*
import net.st915.minesweeper.{Constants, Coordinate}
import org.scalajs.dom.*

import scala.util.chaining.*

object Cell {

  def updateCellClassName(coord: Coordinate, newClassName: String)(implicit
      doc: Document
  ): IO[Unit] = IO {
    doc
      .getElementByIdWithType[HTMLDivElement](s"${coord.x}_${coord.y}")
      .tap { cellElem =>
        if (cellElem.className != newClassName)
          cellElem.className = newClassName
      }
  }

  def make(
      coord: Coordinate,
      onClick: Coordinate => IO[Unit],
      onRightClick: Coordinate => IO[Unit]
  )(implicit doc: Document, runtime: IORuntime): IO[Element] =
    for {
      flagIcon <- FlagIcon.make
      flagPlaceholderIcon <- FlagPlaceholderIcon.make
      mineIcon <- MineIcon.make
      flagContainer <- IconContainer.make(
        s"flagContainer_${coord.x}_${coord.y}",
        flagIcon
      )
      flagPlaceholderContainer <- IconContainer.make(
        s"flagPlaceholderContainer_${coord.x}_${coord.y}",
        flagPlaceholderIcon
      )
      mineContainer <- IconContainer.make(
        s"mineContainer_${coord.x}_${coord.y}",
        mineIcon
      )
      component <- IO {
        doc
          .createElementWithType[HTMLDivElement]("div")
          .tap(_.className = Constants.NotOpenedCellClasses)
          .tap(_.id = s"${coord.x}_${coord.y}")
          .tap(_.onclick = e => {
            e.preventDefault()
            onClick(coord).unsafeRunAndForget()
          })
          .tap(_.oncontextmenu = e => {
            e.preventDefault()
            onRightClick(coord).unsafeRunAndForget()
          })
          .tap(_.appendChild(flagContainer))
          .tap(_.appendChild(flagPlaceholderContainer))
          .tap(_.appendChild(mineContainer))
          .tap { cell =>
            (1 to 8).foreach { i =>
              val program = for {
                mineCountContainer <- MineCountContainer.make(
                  s"mineCount_${i}_${coord.x}_${coord.y}",
                  i
                )
                _ <- IO(cell.appendChild(mineCountContainer))
              } yield ()

              program.unsafeRunAndForget()
            }
          }
      }
    } yield component

}
