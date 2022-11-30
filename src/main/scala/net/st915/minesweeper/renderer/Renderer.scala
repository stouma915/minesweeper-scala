package net.st915.minesweeper.renderer

import cats.effect.Sync
import net.st915.minesweeper.component.{AboutPage, InformationText}
import net.st915.minesweeper.renderer.repo.WriteDocument
import org.scalajs.dom.HTMLDocument

object Renderer {

  import cats.syntax.flatMap.*

  def apply[F[_]: Sync: WriteDocument]()(implicit
      document: HTMLDocument
  ): F[Unit] = {
    val informationText = InformationText.make
    val aboutPage = AboutPage.make

    WriteDocument[F].write(informationText) >>
      WriteDocument[F].write(aboutPage)
  }

}
