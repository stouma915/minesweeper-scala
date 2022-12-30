package net.st915.minesweeper.ui.components

import cats.Monad
import cats.effect.IO
import net.st915.minesweeper.EventQueue
import net.st915.minesweeper.event.ButtonClickEvent
import net.st915.typesafescalajs.elements.*
import net.st915.typesafescalajs.elements.properties.*
import net.st915.typesafescalajs.nodes.*
import net.st915.typesafescalajs.nodes.properties.InnerText

object Button {

  import cats.syntax.flatMap.*
  import net.st915.typesafescalajs.syntax.appendNodeSyntax.*

  private def buttonText[F[_]: Monad](containerID: ID, text: InnerText): F[Span] =
    Monad[F].pure {
      Span(className = ClassName("btnText"), id = containerID) with_ {
        TextNode(text)
      }
    }

  def component[F[_]: Monad](containerID: ID, text: InnerText): F[Div] =
    buttonText[F](containerID, text) >>= { buttonInner =>
      Monad[F].pure {
        Div(
          className = ClassName("btn"),
          clickEvent = ClickEvent(EventQueue.queue[IO](ButtonClickEvent(containerID)))
        ) with_ buttonInner
      }
    }

}
