package net.st915.minesweeper.ui.component.impl

import cats.effect.Sync
import net.st915.minesweeper.Difficulties
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.DifficultySelector
import org.scalajs.dom.*

class SyncDifficultySelector[F[
    _
]: Sync: AppendBR: AppendElement: AppendTextNode: CreateDifficultyLink: CreateElement: UpdateHTMLClass: UpdateHyperlink]
    extends DifficultySelector[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*
  import cats.syntax.traverse.*

  override def create(implicit
      document: HTMLDocument,
      window: Window
  ): F[HTMLDivElement] =
    for {
      containerDiv <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(
        containerDiv,
        "difficultySelectorContainer"
      )
      span <- CreateElement[F].create[HTMLElement]("span")
      _ <- AppendTextNode[F].append(span, "Difficulties:")
      _ <- AppendBR[F].append(span)
      _ <- AppendElement[F].append(containerDiv, span)
      links <- Difficulties.All.map { diff =>
        for {
          link <- CreateElement[F].create[HTMLLinkElement]("a")
          _ <- AppendTextNode[F].append(link, diff.displayName)
          _ <- AppendBR[F].append(link)
          hyperlink <- CreateDifficultyLink[F].create(diff)
          _ <- UpdateHyperlink[F].update(link, hyperlink)
        } yield link
      }.sequence
      _ <- links
        .map(link => AppendElement[F].append(containerDiv, link))
        .sequence
    } yield containerDiv

}
