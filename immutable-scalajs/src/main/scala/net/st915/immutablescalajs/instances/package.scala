package net.st915.immutablescalajs

package object instances {

  object canAppendChildInstances extends CanAppendChildInstances
  object canCreateElementInstances extends CanCreateElementInstances
  object canCreateScalaJSElementInstances extends CanCreateScalaJSElementInstances
  object canUpdateCSSClassInstances extends CanUpdateCSSClassInstances
  object canUpdateHyperlinkInstances extends CanUpdateHyperlinkInstances
  object canUpdateIDInstances extends CanUpdateIDInstances
  object canUpdateTextInstances extends CanUpdateTextInstances

  private[immutablescalajs] object canUpdateScalaJSElementCSSClassInstances
      extends CanUpdateScalaJSElementCSSClassInstances
  private[immutablescalajs] object canUpdateScalaJSElementIDInstances
      extends CanUpdateScalaJSElementIDInstances
  private[immutablescalajs] object canUpdateScalaJSElementHyperlinkInstances
      extends CanUpdateScalaJSElementHyperlinkInstances
  private[immutablescalajs] object canUpdateScalaJSElementTextInstances
      extends CanUpdateScalaJSElementTextInstances

  object all
      extends CanAppendChildInstances
      with CanCreateElementInstances
      with CanCreateScalaJSElementInstances
      with CanUpdateCSSClassInstances
      with CanUpdateHyperlinkInstances
      with CanUpdateIDInstances
      with CanUpdateTextInstances

}
