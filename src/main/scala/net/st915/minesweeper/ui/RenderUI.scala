package net.st915.minesweeper.ui

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.Difficulty
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.*
import net.st915.minesweeper.ui.component.impl.*
import net.st915.minesweeper.ui.impl.*
import org.scalajs.dom.*

object RenderUI {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](difficulty: Difficulty)(
    implicit document: HTMLDocument,
    window: Window,
    runtime: IORuntime
  ): F[Unit] = {
    // format: off
    implicit val _appendElement: AppendElement[F] = SyncAppendElement[F]
    implicit val _createTextNode: CreateTextNode[F] = SyncCreateTextNode[F]
    implicit val _appendTextNode: AppendTextNode[F] = SyncAppendTextNode[F]
    implicit val _createElement: CreateElement[F] = SyncCreateElement[F]
    implicit val _appendBR: AppendBR[F] = SyncAppendBR[F]
    implicit val _createDifficultyLink: CreateDifficultyLink[F] = SyncCreateDifficultyLink[F]
    implicit val _getMineCountColor: GetMineCountColor[F] = ApplicativeGetMineCountColor[F]
    implicit val _updateElementClickEvent: UpdateElementClickEvent[F] = SyncUpdateElementClickEvent[F]
    implicit val _updateElementID: UpdateElementID[F] = SyncUpdateElementID[F]
    implicit val _updateElementRightClickEvent: UpdateElementRightClickEvent[F] = SyncUpdateElementRightClickEvent[F]
    implicit val _updateElementTextColor: UpdateElementTextColor[F] = SyncUpdateElementTextColor[F]
    implicit val _updateHTMLClass: UpdateHTMLClass[F] = SyncUpdateHTMLClass[F]
    implicit val _createButton: CreateButton[F] = SyncCreateButton[F]
    implicit val _updateHyperlink: UpdateHyperlink[F] = SyncUpdateHyperlink[F]

    implicit val _aboutPage: AboutPage[F] = SyncAboutPage[F]
    implicit val _iconContainer: IconContainer[F] = SyncIconContainer[F]
    implicit val _mineCountContainer: MineCountContainer[F] = SyncMineCountContainer[F]
    implicit val _flagIcon: FlagIcon[F] = SyncFlagIcon[F]
    implicit val _flagPlaceholderIcon: FlagPlaceholderIcon[F] = SyncFlagPlaceholderIcon[F]
    implicit val _mineIcon: MineIcon[F] = SyncMineIcon[F]
    implicit val _cell: Cell[F] = SyncCell[F]
    implicit val _cellLine: CellLine[F] = SyncCellLine[F]
    implicit val _cellArray: CellArray[F] = SyncCellArray[F]
    implicit val _toggleFlagModeButton: ToggleFlagModeButton[F] = SyncToggleFlagModeButton[F]
    implicit val _restartButton: RestartButton[F] = SyncRestartButton[F]
    implicit val _gameScreen: GameScreen[F] = SyncGameScreen[F]
    implicit val _difficultySelector: DifficultySelector[F] = SyncDifficultySelector[F]
    implicit val _informationText: InformationText[F] = SyncInformationText[F]
    // format: on

    RenderUI(difficulty)
  }

  def apply[
    F[_]: Sync: AppendElement: AboutPage: DifficultySelector: GameScreen: InformationText
  ](difficulty: Difficulty)(
    implicit document: HTMLDocument,
    window: Window,
    runtime: IORuntime
  ): F[Unit] =
    for {
      informationText <- InformationText[F].create
      gameScreen <- GameScreen[F].create(difficulty)
      difficultySelector <- DifficultySelector[F].create
      aboutPage <- AboutPage[F].create
      _ <- AppendElement[F].append(document.body, informationText)
      _ <- AppendElement[F].append(document.body, gameScreen)
      _ <- AppendElement[F].append(document.body, difficultySelector)
      _ <- AppendElement[F].append(document.body, aboutPage)
    } yield ()

}
