package net.st915.minesweeper

object Consts {

  final val DifficultyParameter = "d"
  final val EventLoopInterval = 100

  object CSSClass {

    final val AboutPage = "aboutPage"
    final val DifficultySelector = "difficultySelector"
    final val GameScreen = "gameScreen"
    final val InformationText = "informationText"

    final val CellArray = "cellArray"
    final val CellLine = "line"

    final val OpenedCell = "cell cellOpened"
    final val NotOpenedCell = "cell cellNotOpened"

    final val FlagIcon = "flag"
    final val FlagPartTop = "flagPart flagTop"
    final val FlagPartMiddle = "flagPart flagMiddle"
    final val FlagPartBottom = "flagPart flagBottom"

    final val FlagPlaceholderIcon = "flagPlaceholder"
    final val FlagPlaceholderPartTop = "flagPlaceholderPart flagPlaceholderTop"
    final val FlagPlaceholderPartMiddle = "flagPlaceholderPart flagPlaceholderMiddle"
    final val FlagPlaceholderPartBottom = "flagPlaceholderPart flagPlaceholderBottom"

    final val MineIcon = "mine"
    final val MinePartTop = "minePart mineTop"
    final val MinePartMiddleLeft = "minePart mineMiddleLeft"
    final val MinePartMiddleCenter = "minePart mineMiddleCenter"

    final val Button = "btn"
    final val ButtonInner = "btnText"

    final val IconContainer = "iconContainer"
    final val MineCountContainer = "mineCountContainer"

  }

  object Difficulties {

    final val Easy = Difficulty(
      "easy",
      "Easy",
      9,
      9,
      10
    )
    final val Normal = Difficulty(
      "normal",
      "Normal",
      16,
      16,
      40
    )
    final val Hard = Difficulty(
      "hard",
      "Hard",
      30,
      16,
      99
    )
    final val Impossible = Difficulty(
      "impossible",
      "Impossible",
      9,
      9,
      67
    )

    final val All = List(
      Easy,
      Normal,
      Hard,
      Impossible
    )

    final val Default = Easy

  }

  object ElementID {

    final val ToggleFlagModeButtonId = "toggleFlagPlaceMode"
    final val RestartButtonId = "restart"

  }

  object Link {

    final val License = "https://github.com/stouma915/minesweeper-scala/blob/main/LICENSE"
    final val Repository = "https://github.com/stouma915/minesweeper-scala"
    final val GitHubPages = "https://pages.github.com"

  }

  object Text {

    final val ThisSiteIsLicensedUnderThe = "This site is licensed under the "
    final val MITLicense = "MIT License"
    final val ThisSiteIsOpenSource = "This site is open source. "
    final val ImproveThisSite = "Improve this site"
    final val PoweredBy = "Powered by "
    final val GitHubPages = "GitHub Pages"

    final val DifficultiesColon = "Difficulties:"

    final val CurrentlyUnderDevelopment = "Currently Under Development."

    final val RestartButton = "Restart"
    final val EnterFlagPlaceMode = "Enter Flag Place Mode"
    final val ExitFlagPlaceMode = "Exit Flag Place Mode"

    final val Period = "."

  }

}
