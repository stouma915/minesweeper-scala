package net.st915.minesweeper.ui.consts

object UIText {

  final val ThisSiteIsLicensedUnderThe = UIText("This site is licensed under the ")
  final val MITLicense = UIText("MIT License")
  final val ThisSiteIsOpenSource = UIText("This site is open source. ")
  final val ImproveThisSite = UIText("Improve this site")
  final val PoweredBy = UIText("Powered by ")
  final val GitHubPages = UIText("GitHub Pages")

  final val DifficultiesColon = UIText("Difficulties:")

  final val CurrentlyUnderDevelopment = UIText("Currently Under Development.")

  final val RestartButton = UIText("Restart")
  final val EnterFlagPlaceMode = UIText("Enter Flag Place Mode")
  final val ExitFlagPlaceMode = UIText("Exit Flag Place Mode")

  final val Period = UIText(".")

}

case class UIText(content: String) extends UIConstant(content) {

  override def asStr: String = content

}
