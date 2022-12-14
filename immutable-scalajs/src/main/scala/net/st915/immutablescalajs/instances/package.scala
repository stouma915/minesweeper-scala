package net.st915.immutablescalajs

package object instances {

  object canAddChildInstances extends CanAddChildInstances
  object canCreateElementInstances extends CanCreateElementInstances
  object canUpdateCSSClassInstances extends CanUpdateCSSClassInstances
  object canUpdateHyperlinkInstances extends CanUpdateHyperlinkInstances
  object canUpdateIDInstances extends CanUpdateIDInstances
  object canUpdateTextInstances extends CanUpdateTextInstances

  object all
      extends CanAddChildInstances
      with CanCreateElementInstances
      with CanUpdateCSSClassInstances
      with CanUpdateHyperlinkInstances
      with CanUpdateIDInstances
      with CanUpdateTextInstances

}
