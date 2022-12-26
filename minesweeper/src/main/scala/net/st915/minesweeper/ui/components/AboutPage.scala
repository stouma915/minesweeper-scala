package net.st915.minesweeper.ui.components

import cats.Monad
import net.st915.typesafescalajs.elements.*
import net.st915.typesafescalajs.elements.properties.*
import net.st915.typesafescalajs.nodes.TextNode

object AboutPage {

  import net.st915.typesafescalajs.syntax.appendNodeSyntax.*

  def component[F[_]: Monad]: F[Div] =
    Monad[F].pure {
      Div(className = ClassName("aboutPage")) with_ (
        Span() with_ (
          TextNode("This site is licensed under the "),
          Anchor(href =
            Link("https://github.com/stouma915/minesweeper-scala/blob/main/LICENSE")) with_ {
            TextNode("MIT License")
          },
          TextNode(".")
        ),
        BR(),
        Span() with_ (
          TextNode("This site is open source. "),
          Anchor(href =
            Link("https://github.com/stouma915/minesweeper-scala")) with_ {
            TextNode("Improve this site")
          },
          TextNode(".")
        ),
        BR(),
        Span() with_ (
          TextNode("Powered by "),
          Anchor(href = Link("https://pages.github.com")) with_ {
            TextNode("GitHub Pages")
          },
          TextNode(".")
        ),
        BR()
      )
    }

}
