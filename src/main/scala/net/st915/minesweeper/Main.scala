package net.st915.minesweeper

import cats.effect.IO
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.{
  Document,
  Element,
  HTMLLinkElement,
  document
}

import scala.util.chaining.*

@main def main(): Unit = {
  
  import cats.effect.unsafe.implicits.global

  implicit val _document: Document = document

  val makeTestMessage = IO[Element] {
    document.createElement("h1")
      .tap(_.appendChild("ABCD".textNode))
  }

  val makeAboutPage = IO[Element] {
    document.createElement("div")
      .tap { div =>
        document.createElement("p")
          .tap(_.appendChild("This site is licensed under the ".textNode))
          .tap { p =>
            document.createElement("a")
              .asInstanceOf[HTMLLinkElement]
              .tap(_.appendChild("MIT License".textNode))
              .tap(_.href =
                "https://github.com/stouma915/minesweeper-scala/blob/main/LICENSE"
              )
              .tap(p.appendChild)
          }
          .tap(_.appendChild(".".textNode))
          .tap(_.appendChild(document.createElement("br")))
          .tap(_.appendChild("This site is open source. ".textNode))
          .tap { p =>
            document.createElement("a")
              .asInstanceOf[HTMLLinkElement]
              .tap(_.appendChild("Improve this site".textNode))
              .tap(_.href =
                "https://github.com/stouma915/minesweeper-scala"
              )
              .tap(p.appendChild)
          }
          .tap(_.appendChild(".".textNode))
          .tap(_.appendChild(document.createElement("br")))
          .tap(_.appendChild("Powered by ".textNode))
          .tap { p =>
            document.createElement("a")
              .asInstanceOf[HTMLLinkElement]
              .tap(_.appendChild("GitHub Pages".textNode))
              .tap(_.href =
                "https://pages.github.com"
              )
              .tap(p.appendChild)
          }
          .tap(_.appendChild(".".textNode))
          .tap(div.appendChild)
      }
  }

  val appendToBody = (e: Element) => IO[Unit] {
    document.body.appendChild(e)
  }

  val task = for {
    testMessage <- makeTestMessage
    aboutPage <- makeAboutPage
    _ <- appendToBody(testMessage)
    _ <- appendToBody(aboutPage)
  } yield ()

  task.unsafeRunAndForget()

}
