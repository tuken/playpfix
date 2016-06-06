package controllers

import com.google.inject.Inject
import models.Alias
import play.api.Logger
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{JsError, JsValue, Json}
import play.api.libs.json.Json._
import play.api.mvc._
import utils.Constants
import utils.JsonFormat._

import scala.concurrent.Future

class EmployeeController @Inject()(alias: Alias) extends Controller {

  import Constants._

  val logger = Logger(this.getClass())

  private def successResponse(data: JsValue, message: String) = {
    obj("status" -> SUCCESS, "data" -> data, "msg" -> message)
  }

  def list() = Action.async {
    alias.getAll().map { res =>
      logger.info("Emp list: " + res)
      Ok(successResponse(Json.toJson(res), "Getting Employee list successfully"))
    }
  }

  def create() = Action.async(parse.json) { request =>
    logger.info("Employee Json ===> " + request.body)
    request.body.validate[Alias].fold(error => Future.successful(BadRequest(JsError.toJson(error))), { emp =>
      alias.insert(emp).map { createdEmpId =>
        Ok(successResponse(Json.toJson(Map("id" ->createdEmpId)), "Employee has been created successfully."))
      }
    })
  }

  def delete(empId: Int) = Action.async { request =>
    alias.delete(empId).map { _ =>
      Ok(successResponse(Json.toJson("{}"), "Employee has been deleted successfully."))
    }
  }

  def edit(empId: Int): Action[AnyContent] = Action.async { request =>
    alias.getById(empId).map { empOpt =>
      empOpt.fold(Ok(obj("status" -> ERROR, "data" -> "{}", "msg" -> "Employee does not exist.")))(emp => Ok(
        successResponse(Json.toJson(emp), "Getting Employee successfully")))
    }
  }

  def update = Action.async(parse.json) { request =>
    logger.info("Employee Json ===> " + request.body)
    request.body.validate[Alias].fold(error => Future.successful(BadRequest(JsError.toJson(error))), { emp =>
      alias.update(emp).map { res => Ok(successResponse(Json.toJson("{}"), "Employee has been updated successfully.")) }
    })
  }
}
