package net.st915.minesweeper.ui.component.impl

import cats.effect.Sync
import net.st915.minesweeper.Consts.{CSSClass, Link, Text}
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.AboutPage
import org.scalajs.dom.*

class SyncAboutPage[
  F[
    _
  ]: Sync: AppendBR: AppendElement: AppendTextNode: CreateDiv: CreateLink: CreateP: UpdateHTMLClass: UpdateHyperlink
] extends AboutPage[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def create(implicit document: HTMLDocument): F[HTMLDivElement] =
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
