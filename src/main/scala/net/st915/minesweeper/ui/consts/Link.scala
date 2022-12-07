package net.st915.minesweeper.ui.consts

object Link {

  final val License = Link("https://github.com/stouma915/minesweeper-scala/blob/main/LICENSE")
  final val Repository = Link("https://github.com/stouma915/minesweeper-scala")
  final val GitHubPages = Link("https://pages.github.com")

}

case class Link(url: String) extends UIConstant(url) {

  override def asStr: String = url

}
