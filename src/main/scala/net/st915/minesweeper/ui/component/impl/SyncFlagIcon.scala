package net.st915.minesweeper.ui.component.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.FlagIcon
import org.scalajs.dom.*

class SyncFlagIcon[F[_]: Sync: AppendElement: CreateElement: UpdateHTMLClass]
    extends FlagIcon[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def create(implicit document: HTMLDocument): F[HTMLDivElement] =
    for {
      flagIcon <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(flagIcon, "flag")
      flagPartTop <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(flagPartTop, "flagPart flagTop")
      _ <- AppendElement[F].append(flagIcon, flagPartTop)
      flagPartMiddle <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(flagPartMiddle, "flagPart flagMiddle")
      _ <- AppendElement[F].append(flagIcon, flagPartMiddle)
      flagPartBottom <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(flagPartBottom, "flagPart flagBottom")
      _ <- AppendElement[F].append(flagIcon, flagPartBottom)
    } yield flagIcon

}
