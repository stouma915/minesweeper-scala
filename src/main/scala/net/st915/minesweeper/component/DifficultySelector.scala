package net.st915.minesweeper.component

import cats.effect.IO
import net.st915.minesweeper.Difficulty
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.{
  Document,
  Element,
  HTMLLinkElement,
  URL,
  Window
}

import scala.util.chaining.*

object DifficultySelector {

  import cats.effect.unsafe.implicits.global

  def changeDifficulty(diff: Difficulty)(implicit wind: Window): IO[Unit] = IO {
    val url = new URL(wind.location.href)

    val param =
      if (diff eq Difficulty.Default) "" else s"?d=${diff.name}"
    val newUrl = s"${url.origin}${url.pathname}$param"

    wind.location.href = newUrl
  }

  def make(implicit doc: Document, wind: Window): IO[Element] = IO {
    doc.createElement("div")
      .tap { div =>
        Difficulty.Difficulties.foreach { diff =>
          doc.createElement("a")
            .asInstanceOf[HTMLLinkElement]
            .tap(_.appendChild(diff.name.textNode))
            .tap(_.appendChild(doc.createElement("br")))
            .tap(_.href = "#")
            .tap(_.onclick = (_) =>
              changeDifficulty(diff).unsafeRunAndForget()
            )
            .tap(div.appendChild)
        }
      }
  }

}
