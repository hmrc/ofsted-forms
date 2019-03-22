/*
 * Copyright 2019 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ofstedforms.controllers

import java.util.UUID.randomUUID

import javax.inject.{Inject, Singleton}
import play.api.libs.json.{JsError, Json}
import play.api.mvc._
import uk.gov.hmrc.ofstedforms.models.{AuthenticatedUser, Form, Occurrence}
import uk.gov.hmrc.ofstedforms.service.FormService
import uk.gov.hmrc.play.bootstrap.controller.BackendController

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton()
class OfstedFormsController @Inject()(fs: FormService, cc: MessagesControllerComponents) extends BackendController(cc) {

  //TODO: add logging
  //TODO: add auth filter
  def createDraft(): Action[AnyContent] = Action {
    implicit request =>
      val draftForm = Form(randomUUID().toString, "", Occurrence(AuthenticatedUser("", ""), ""))
      request.body.asJson.get.validate[Form].asOpt match {
        case Some(x) => Ok(Json.toJson(x))
        case None => BadRequest("Bad payload")
      }
  }

  //  request.body.asJson.get.validate[Form] match {
  //    case JsSuccess(value, path) => Json.toJson(value)
  //    //        {
  //    //          fs.saveDraft(draftForm).map {
  //    //            case Some(res) => Ok(Json.obj("id" -> res.id))
  //    //            case None => BadRequest("Initial draft form record was not inserted")
  //    //          }.recover {
  //    //            case e: Throwable => InternalServerError(s"${e.getMessage}")
  //    //          }
  //    //        }
  //    case JsError(errors) => InternalServerError(JsError.toJson(errors))
  //  }


  def getForm(id: String): Action[AnyContent] = Action.async {
    implicit request =>
      fs.getForm(id).map {
        case Some(form) => Ok(Json.toJson(form))
        case None => BadRequest(s"A form does not exist with id : {$id}")
      }.recover {
        case e: Throwable => InternalServerError(s"${e.getMessage}")
      }
  }


  def saveDraftForm() = Action.async(parse.json) {
    implicit request =>
      val formSubmission = request.body.validate[Form]
      formSubmission.fold(
        errors => {
          Future.successful(BadRequest(s"Invalid draft form payload: ${JsError.toJson(errors)}"))
        },
        form => {
          fs.saveDraft(form).map { _ => NoContent
          }.recover {
            case e: Throwable => InternalServerError(s"${e.getMessage}")
          }
        }
      )
  }

  def saveSubmittedForm() = Action.async(parse.json) {
    implicit request =>
      val formSubmission = request.body.validate[Form]
      formSubmission.fold(
        errors => {
          Future.successful(BadRequest(s"Invalid submission payload: ${JsError.toJson(errors)}"))
        },
        form => {
          fs.saveSubmission(form).map { _ => NoContent
          }.recover {
            case e: Throwable => InternalServerError(s"${e.getMessage}")
          }
        }
      )
  }

  def saveAssessedForm() = Action.async(parse.json) {
    implicit request =>
      val formSubmission = request.body.validate[Form]
      formSubmission.fold(
        errors => {
          Future.successful(BadRequest(s"Invalid assessed form payload: ${JsError.toJson(errors)}"))
        },
        form => {
          fs.saveAssessment(form).map { _ => NoContent
          }.recover {
            case e: Throwable => InternalServerError(s"${e.getMessage}")
          }
        }
      )
  }

  def getWorkList(): Action[AnyContent] = Action.async {
    implicit request =>
      fs.getWorkList().map {
        forms => Ok(Json.toJson(forms))
      }
  }

}