package net.st915.minesweeper.eventlistener

import cats.effect.Sync
import net.st915.immutablescalajs.dom.properties.ID
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.*
import net.st915.minesweeper.eventlistener.handlers.*
import net.st915.minesweeper.util.DoNothing

object EventListener {

  import cats.syntax.flatMap.*

  import net.st915.minesweeper.util.instances.doNothingInstances.given

  def wired[F[_]: Sync](event: Event)(using GameState): F[GameState] =
    event match
      case e: CellClickEvent =>
        CellClickEventHandler.wired[F](e)
      case e: CellRightClickEvent =>
        CellRightClickEventHandler.wired[F](e)
      case ButtonClickEvent(id) =>
        id match
          case ID("toggleFlagPlaceMode") =>
            ToggleFlagModeButtonClickEventHandler.wired[F]
          case ID("restart") =>
            RestartButtonClickEventHandler.wired[F]
          case _ => DoNothing[F]()
      case _ => DoNothing[F]()

}
