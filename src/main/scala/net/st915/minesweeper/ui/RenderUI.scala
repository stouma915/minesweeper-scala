package net.st915.minesweeper.ui

import cats.effect.Sync
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.*
import net.st915.minesweeper.ui.component.impl.*
import net.st915.minesweeper.ui.impl.*
import org.scalajs.dom.HTMLDocument

object RenderUI {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](implicit document: HTMLDocument): F[Unit] = {
    implicit val _appendElement: AppendElement[F] = SyncAppendElement[F]
    implicit val _createTextNode: CreateTextNode[F] = SyncCreateTextNode[F]
    implicit val _appendTextNode: AppendTextNode[F] = SyncAppendTextNode[F]
    implicit val _createElement: CreateElement[F] = SyncCreateElement[F]
    implicit val _appendBR: AppendBR[F] = SyncAppendBR[F]
    implicit val _updateHTMLClass: UpdateHTMLClass[F] = SyncUpdateHTMLClass[F]
    implicit val _updateHyperlink: UpdateHyperlink[F] = SyncUpdateHyperlink[F]

    implicit val _aboutPage: AboutPage[F] = SyncAboutPage[F]
    implicit val _informationText: InformationText[F] = SyncInformationText[F]

    RenderUI()
  }

  def apply[F[_]: Sync: AppendElement: AboutPage: InformationText]()(implicit
      document: HTMLDocument
  ): F[Unit] =
    for {
      informationText <- InformationText[F].create
      aboutPage <- AboutPage[F].create
      _ <- AppendElement[F].append(document.body, informationText)
      _ <- AppendElement[F].append(document.body, aboutPage)
    } yield ()

}
