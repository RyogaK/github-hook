package github.enums

sealed abstract class PullRequestAction(val name: String) {
  override def toString = name
}

object PullRequestAction {
  case object assigned    extends PullRequestAction("assigned")
  case object unassigned  extends PullRequestAction("unassigned")
  case object labeled     extends PullRequestAction("labeled")
  case object unlabeled   extends PullRequestAction("unlabeled")
  case object opened      extends PullRequestAction("opened")
  case object closed      extends PullRequestAction("closed")
  case object reopened    extends PullRequestAction("reopened")
  case object synchronize extends PullRequestAction("synchronize")

  val values = Array(
    assigned,
    unassigned,
    labeled,
    unlabeled,
    opened,
    closed,
    reopened,
    synchronize
  )

  def fromString(str: String) = values.filter(_.name == str).head
}

