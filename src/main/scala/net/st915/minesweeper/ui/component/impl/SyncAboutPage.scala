package net.st915.minesweeper.ui.component.impl

import cats.effect.Sync
import net.st915.minesweeper.Consts.{CSSClass, Link}
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.AboutPage
import org.scalajs.dom.*

class SyncAboutPage[
  F[
    _
  ]: Sync: AppendBR: AppendElement: AppendTextNode: CreateElement: UpdateHTMLClass: UpdateHyperlink
] extends AboutPage[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def create(implicit document: HTMLDocument): F[HTMLDivElement] =
    for {
      containerDiv <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(containerDiv, CSSClass.AboutPage)
      innerText <- CreateElement[F].create[HTMLElement]("p")
      _ <- AppendTextNode[F].append(innerText, "This site is licensed under the ")
      licenseLink <- CreateElement[F].create[HTMLLinkElement]("a")
      _ <- AppendTextNode[F].append(licenseLink, "MIT License")
      _ <- UpdateHyperlink[F].update(licenseLink, Link.License)
      _ <- AppendElement[F].append(innerText, licenseLink)
      _ <- AppendTextNode[F].append(innerText, ".")
      _ <- AppendBR[F].append(innerText)
      _ <- AppendTextNode[F].append(innerText, "This site is open source. ")
      repositoryLink <- CreateElement[F].create[HTMLLinkElement]("a")
      _ <- AppendTextNode[F].append(repositoryLink, "Improve this site")
      _ <- UpdateHyperlink[F].update(repositoryLink, Link.Repository)
      _ <- AppendElement[F].append(innerText, repositoryLink)
      _ <- AppendTextNode[F].append(innerText, ".")
      _ <- AppendBR[F].append(innerText)
      _ <- AppendTextNode[F].append(innerText, "Powered by ")
      gitHubPagesLink <- CreateElement[F].create[HTMLLinkElement]("a")
      _ <- AppendTextNode[F].append(gitHubPagesLink, "GitHub Pages")
      _ <- UpdateHyperlink[F].update(gitHubPagesLink, Link.GitHubPages)
      _ <- AppendElement[F].append(innerText, gitHubPagesLink)
      _ <- AppendTextNode[F].append(innerText, ".")
      _ <- AppendElement[F].append(containerDiv, innerText)
    } yield containerDiv

}
