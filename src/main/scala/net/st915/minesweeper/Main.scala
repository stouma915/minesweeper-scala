package net.st915.minesweeper

import cats.effect.IO
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.{Document, HTMLLinkElement, document}

import scala.util.chaining.*

@main def main(): Unit = {
  
  import cats.effect.unsafe.implicits.global

  implicit val _document: Document = document

  val printTestMessage = IO {
    println("TEST")
  }

  val appendTestMessage = IO {
    document.createElement("h1")
      .tap(_.appendChild("ABCD".textNode))
      .tap(document.body.appendChild)
  }

  val appendAboutPage = IO {
    document.createElement("div")
      .tap(div =>
        document.createElement("p")
          .tap(_.appendChild("This site is licensed under the ".textNode))
          .tap(p =>
            document.createElement("a")
              .asInstanceOf[HTMLLinkElement]
              .tap(_.appendChild("MIT License".textNode))
              .tap(_.href =
                "https://github.com/stouma915/minesweeper-scala/blob/main/LICENSE"
              )
              .tap(p.appendChild)
          )
          .tap(_.appendChild(".".textNode))
          .tap(_.appendChild(document.createElement("br")))
          .tap(_.appendChild("This site is open source. ".textNode))
          .tap(p =>
            document.createElement("a")
              .asInstanceOf[HTMLLinkElement]
              .tap(_.appendChild("Improve this site".textNode))
              .tap(_.href =
                "https://github.com/stouma915/minesweeper-scala"
              )
              .tap(p.appendChild)
          )
          .tap(_.appendChild(".".textNode))
          .tap(_.appendChild(document.createElement("br")))
          .tap(_.appendChild("Powered by ".textNode))
          .tap(p =>
            document.createElement("a")
              .asInstanceOf[HTMLLinkElement]
              .tap(_.appendChild("GitHub Pages".textNode))
              .tap(_.href =
                "https://pages.github.com"
              )
              .tap(p.appendChild)
          )
          .tap(_.appendChild(".".textNode))
          .tap(div.appendChild)
      )
      .tap(document.body.appendChild)
  }

  val task = for {
    _ <- printTestMessage
    _ <- appendTestMessage
    _ <- appendAboutPage
  } yield ()

  task.unsafeRunAndForget()

}
