package net.st915.minesweeper.ui.consts

object CSSClass {

  final val AboutPage = CSSClass("aboutPage")
  final val DifficultySelector = CSSClass("difficultySelector")
  final val GameScreen = CSSClass("gameScreen")
  final val InformationText = CSSClass("informationText")

  final val CellArray = CSSClass("cellArray")
  final val CellLine = CSSClass("line")

  final val OpenedCell = CSSClass("cell", "cellOpened")
  final val NotOpenedCell = CSSClass("cell", "cellNotOpened")

  final val FlagIcon = CSSClass("flag")
  final val FlagPartTop = CSSClass("flagPart", "flagTop")
  final val FlagPartMiddle = CSSClass("flagPart", "flagMiddle")
  final val FlagPartBottom = CSSClass("flagPart", "flagBottom")

  final val FlagPlaceholderIcon = CSSClass("flagPlaceholder")
  final val FlagPlaceholderPartTop = CSSClass("flagPlaceholderPart", "flagPlaceholderTop")
  final val FlagPlaceholderPartMiddle = CSSClass("flagPlaceholderPart", "flagPlaceholderMiddle")
  final val FlagPlaceholderPartBottom = CSSClass("flagPlaceholderPart", "flagPlaceholderBottom")

  final val MineIcon = CSSClass("mine")
  final val MinePartTop = CSSClass("minePart", "mineTop")
  final val MinePartMiddleLeft = CSSClass("minePart", "mineMiddleLeft")
  final val MinePartMiddleCenter = CSSClass("minePart", "mineMiddleCenter")

  final val Button = CSSClass("btn")
  final val ButtonInner = CSSClass("btnText")

  final val IconContainer = CSSClass("iconContainer")
  final val MineCountContainer = CSSClass("mineCountContainer")

}

case class CSSClass(classNames: String*) extends UIConstant(classNames) {

  override def asStr: String = classNames.mkString(" ")

}
