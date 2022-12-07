package net.st915.minesweeper.ui.consts

object ID {

  final val ToggleFlagModeButtonId = ID("toggleFlagPlaceMode")
  final val RestartButtonId = ID("restart")

}

case class ID(content: String) extends UIConstant(content) {

  override def asStr: String = content

}
