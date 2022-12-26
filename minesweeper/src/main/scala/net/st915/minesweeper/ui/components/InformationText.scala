package net.st915.minesweeper.ui.components

import cats.Monad
import net.st915.typesafescalajs.elements.*
import net.st915.typesafescalajs.elements.properties.*
import net.st915.typesafescalajs.nodes.TextNode

object InformationText {

  import net.st915.typesafescalajs.syntax.appendNodeSyntax.*

  def component[F[_]: Monad]: F[Div] =
    Monad[F].pure {
      Div(className = ClassName("informationText")) with_ (
        H1() with_ {
          TextNode("Currently Under Development.")
        },
        BR()
      )
    }

}
