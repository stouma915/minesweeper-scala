package net.st915.minesweeper.ui.component

import cats.effect.Sync
import net.st915.minesweeper.Consts.{CSSClass, Link, Text}
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.impl.*
import org.scalajs.dom.*

object AboutPage {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](implicit document: HTMLDocument): F[HTMLDivElement] = {
    implicit val _appendElement: AppendElement[F] = SyncAppendElement[F]
    implicit val _createElement: CreateElement[F] = SyncCreateElement[F]
    implicit val _createBR: CreateBR[F] = SyncCreateBR[F]
    implicit val _createDiv: CreateDiv[F] = SyncCreateDiv[F]
    implicit val _createLink: CreateLink[F] = SyncCreateLink[F]
    implicit val _createP: CreateP[F] = SyncCreateP[F]
    implicit val _createTextNode: CreateTextNode[F] = SyncCreateTextNode[F]
    implicit val _appendTextNode: AppendTextNode[F] = SyncAppendTextNode[F]
    implicit val _appendBR: AppendBR[F] = SyncAppendBR[F]
    implicit val _updateHTMLClass: UpdateHTMLClass[F] = SyncUpdateHTMLClass[F]
    implicit val _updateHyperlink: UpdateHyperlink[F] = SyncUpdateHyperlink[F]

    AboutPage()
  }

  def apply[
    F[_]: Sync: AppendBR: AppendElement: AppendTextNode: CreateDiv: CreateLink: CreateP: UpdateHTMLClass: UpdateHyperlink
  ]()(implicit document: HTMLDocument): F[HTMLDivElement] =
    for {
      containerDiv <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(containerDiv, CSSClass.AboutPage)
      innerText <- CreateP[F].create
      _ <- AppendTextNode[F].append(innerText, Text.ThisSiteIsLicensedUnderThe)
      licenseLink <- CreateLink[F].create
      _ <- AppendTextNode[F].append(licenseLink, Text.MITLicense)
      _ <- UpdateHyperlink[F].update(licenseLink, Link.License)
      _ <- AppendElement[F].append(innerText, licenseLink)
      _ <- AppendTextNode[F].append(innerText, Text.Period)
      _ <- AppendBR[F].append(innerText)
      _ <- AppendTextNode[F].append(innerText, Text.ThisSiteIsOpenSource)
      repositoryLink <- CreateLink[F].create
      _ <- AppendTextNode[F].append(repositoryLink, Text.ImproveThisSite)
      _ <- UpdateHyperlink[F].update(repositoryLink, Link.Repository)
      _ <- AppendElement[F].append(innerText, repositoryLink)
      _ <- AppendTextNode[F].append(innerText, Text.Period)
      _ <- AppendBR[F].append(innerText)
      _ <- AppendTextNode[F].append(innerText, Text.PoweredBy)
      gitHubPagesLink <- CreateLink[F].create
      _ <- AppendTextNode[F].append(gitHubPagesLink, Text.GitHubPages)
      _ <- UpdateHyperlink[F].update(gitHubPagesLink, Link.GitHubPages)
      _ <- AppendElement[F].append(innerText, gitHubPagesLink)
      _ <- AppendTextNode[F].append(innerText, Text.Period)
      _ <- AppendElement[F].append(containerDiv, innerText)
    } yield containerDiv

}
