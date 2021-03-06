package controllers

import play.api._
import play.api.mvc._
import play.api.Play.current
import play.api.libs.json.Json
import play.api.libs.concurrent.Akka
import akka.actor.Props
import models.GitHubService
import models.ActionManager
import github.GitHubEvent

object Application extends Controller {

  private val gh = Akka.system.actorOf(Props(
    new GitHubService(
      sys.env("GITHUB_TOKEN"),
      ActionManager
    )
  ))

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def hook = Action(parse.tolerantJson) { request =>
    val name = request.headers("X-Github-Event")
    val msg = GitHubEvent(name, request.body)
    gh ! msg
    Ok("OK")
  }

}