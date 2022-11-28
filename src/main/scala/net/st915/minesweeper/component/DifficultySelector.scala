package net.st915.minesweeper.component

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.implicits.*
import net.st915.minesweeper.{Difficulties, Difficulty}
import org.scalajs.dom.*

import scala.util.chaining.*

object DifficultySelector {

  private def changeDifficulty(
      diff: Difficulty
  )(implicit wind: Window): IO[Unit] = IO {
    val url = new URL(wind.location.href)

    val param =
      if (diff eq Difficulties.Default) "" else s"?d=${diff.id}"
    val newUrl = s"${url.origin}${url.pathname}$param"

    wind.location.href = newUrl
  }

  def make(implicit
      doc: Document,
      wind: Window,
      runtime: IORuntime
  ): IO[Element] = IO {
    doc
      .createElement("div")
      .tap { div =>
        doc
          .createElement("span")
          .tap(_.appendChild("Difficulties:".textNode))
          .tap(_.appendChild(doc.makeBR))
          .tap(div.appendChild)
      }
      .tap { div =>
        Difficulties.All.foreach { diff =>
          doc
            .createElementWithType[HTMLLinkElement]("a")
            .tap(_.appendChild(diff.displayName.textNode))
            .tap(_.appendChild(doc.makeBR))
            .tap(_.href = "#")
            .tap(_.onclick = e => {
              e.preventDefault()
              changeDifficulty(diff).unsafeRunAndForget()
            })
            .tap(div.appendChild)
        }
      }
  }

}
