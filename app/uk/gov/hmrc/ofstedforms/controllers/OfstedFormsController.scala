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
import uk.gov.hmrc.ofstedforms.models.{AuthenticatedUser, Form, FormSnapshot, Occurrence}
import uk.gov.hmrc.ofstedforms.service.FormService
import uk.gov.hmrc.play.bootstrap.controller.BackendController

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton()
class OfstedFormsController @Inject()(fs: FormService, cc: MessagesControllerComponents) extends BackendController(cc) {

  //TODO: add logging
  //TODO: add auth action filter
  def createDraft(): Action[AnyContent] = Action.async {
    implicit request =>
      val draftForm = Form(randomUUID().toString, "", Occurrence(AuthenticatedUser("", ""), ""))
      fs.saveDraft(draftForm).map {
        case Some(form) => Ok(Json.toJson(form.id))
        case None => Conflict("Failed to create draft form entry")
      }.recover {
        case e: Throwable => InternalServerError(s"${e.getMessage}")
      }
  }

  def getForm(id: String): Action[AnyContent] = Action.async {
    implicit request =>
      fs.getForm(id).map { snapshot: FormSnapshot =>
        Ok(Json.toJson(snapshot))
      }.recover {
        case e: Throwable => InternalServerError(s"${e.getMessage}")
      }
  }

  def saveDraft() = Action.async(parse.json) {
    implicit request =>
      val formSubmission = request.body.validate[Form]
      formSubmission.fold(
        errors => {
          Future.successful(BadRequest(s"Invalid draft form payload: ${JsError.toJson(errors)}"))
        },
        draft => {
          fs.saveDraft(draft).map {
            case Some(form) => Ok(Json.toJson(form))
            case None => Conflict("Failed to save draft form entry")
          }.recover {
            case e: Throwable => InternalServerError(s"${e.getMessage}")
          }
        }
      )
  }

  def saveSubmission() = Action.async(parse.json) {
    implicit request =>
      val formSubmission = request.body.validate[Form]
      formSubmission.fold(
        errors => {
          Future.successful(BadRequest(s"Invalid submission payload: ${JsError.toJson(errors)}"))
        },
        submission => {
          fs.saveSubmission(submission).map {
            case Some(form) => Ok(Json.toJson(form))
            case None => Conflict("Failed to save submission form entry")
          }.recover {
            case e: Throwable => InternalServerError(s"${e.getMessage}")
          }
        }
      )
  }

  def saveAssessment() = Action.async(parse.json) {
    implicit request =>
      val formSubmission = request.body.validate[Form]
      formSubmission.fold(
        errors => {
          Future.successful(BadRequest(s"Invalid assessed form payload: ${JsError.toJson(errors)}"))
        },
        assessedForm => {
          fs.saveAssessment(assessedForm).map {
            case Some(form) => Ok(Json.toJson(form))
            case None => Conflict("Failed to save assessed form entry")
          }.recover {
            case e: Throwable => InternalServerError(s"${e.getMessage}")
          }
        }
      )
  }

  def getWorkList() = Action.async {
    implicit request =>
      fs.getWorkList().map(x => Ok(Json.toJson(x))).recover {
        case e: Throwable => InternalServerError(s"${e.getMessage}")
      }
  }

}