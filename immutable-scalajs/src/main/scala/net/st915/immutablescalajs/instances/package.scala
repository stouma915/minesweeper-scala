package net.st915.immutablescalajs

package object instances {

  object canCreateElementInstances extends CanCreateElementInstances
  object canUpdateCSSClassInstances extends CanUpdateCSSClassInstances
  object canUpdateHyperlinkInstances extends CanUpdateHyperlinkInstances
  object canUpdateIDInstances extends CanUpdateIDInstances
  object canUpdateTextInstances extends CanUpdateTextInstances

  object all
      extends CanCreateElementInstances
      with CanUpdateCSSClassInstances
      with CanUpdateHyperlinkInstances
      with CanUpdateIDInstances
      with CanUpdateTextInstances

}
