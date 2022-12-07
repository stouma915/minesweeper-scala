package net.st915.minesweeper.ui.components

import cats.effect.Sync
import net.st915.minesweeper.ui.components.typeclasses.*
import net.st915.minesweeper.ui.components.instances.*
import net.st915.minesweeper.ui.consts.*
import org.scalajs.dom.*

object AboutPage {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](using HTMLDocument): F[HTMLDivElement] = {
    given CanAppendBR[F] = SyncCanAppendBR[F]
    given CanAppendElement[F] = SyncCanAppendElement[F]
    given CanCreateElement[F, HTMLDivElement] = MonadCanCreateElementDiv[F]
    given CanCreateElement[F, HTMLLinkElement] = MonadCanCreateElementLink[F]
    given CanCreateElement[F, HTMLSpanElement] = MonadCanCreateElementSpan[F]
    given CanUpdateElementClass[F] = SyncCanUpdateElementClass[F]
    given CanUpdateHyperlink[F] = SyncCanUpdateHyperlink[F]
    given CanUpdateTextContent[F] = SyncCanUpdateTextContent[F]

    for {
      containerDiv <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(containerDiv, CSSClass.AboutPage)

      licensePrefix <- CanCreateElement[F, HTMLSpanElement].create
      _ <- CanUpdateTextContent[F].perform(licensePrefix, UIText.ThisSiteIsLicensedUnderThe)
      _ <- CanAppendElement[F].perform(containerDiv, licensePrefix)
      licenseLink <- CanCreateElement[F, HTMLLinkElement].create
      _ <- CanUpdateTextContent[F].perform(licenseLink, UIText.MITLicense)
      _ <- CanUpdateHyperlink[F].perform(licenseLink, Link.License)
      _ <- CanAppendElement[F].perform(containerDiv, licenseLink)
      licenseSuffix <- CanCreateElement[F, HTMLSpanElement].create
      _ <- CanUpdateTextContent[F].perform(licenseSuffix, UIText.Period)
      _ <- CanAppendElement[F].perform(containerDiv, licenseSuffix)

      _ <- CanAppendBR[F].perform(containerDiv)

      repoPrefix <- CanCreateElement[F, HTMLSpanElement].create
      _ <- CanUpdateTextContent[F].perform(repoPrefix, UIText.ThisSiteIsOpenSource)
      _ <- CanAppendElement[F].perform(containerDiv, repoPrefix)
      repoLink <- CanCreateElement[F, HTMLLinkElement].create
      _ <- CanUpdateTextContent[F].perform(repoLink, UIText.ImproveThisSite)
      _ <- CanUpdateHyperlink[F].perform(repoLink, Link.Repository)
      _ <- CanAppendElement[F].perform(containerDiv, repoLink)
      repoSuffix <- CanCreateElement[F, HTMLSpanElement].create
      _ <- CanUpdateTextContent[F].perform(repoSuffix, UIText.Period)
      _ <- CanAppendElement[F].perform(containerDiv, repoSuffix)

      _ <- CanAppendBR[F].perform(containerDiv)

      pagesPrefix <- CanCreateElement[F, HTMLSpanElement].create
      _ <- CanUpdateTextContent[F].perform(pagesPrefix, UIText.PoweredBy)
      _ <- CanAppendElement[F].perform(containerDiv, pagesPrefix)
      pagesLink <- CanCreateElement[F, HTMLLinkElement].create
      _ <- CanUpdateTextContent[F].perform(pagesLink, UIText.GitHubPages)
      _ <- CanUpdateHyperlink[F].perform(pagesLink, Link.GitHubPages)
      _ <- CanAppendElement[F].perform(containerDiv, pagesLink)
      pagesSuffix <- CanCreateElement[F, HTMLSpanElement].create
      _ <- CanUpdateTextContent[F].perform(pagesSuffix, UIText.Period)
      _ <- CanAppendElement[F].perform(containerDiv, pagesSuffix)
    } yield containerDiv
  }

}
