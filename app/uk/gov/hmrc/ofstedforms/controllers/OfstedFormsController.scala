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
import uk.gov.hmrc.ofstedforms.models.{AuthenticatedUser, DraftForm, Occurrence}
import uk.gov.hmrc.ofstedforms.service.FormSubmissionService
import uk.gov.hmrc.play.bootstrap.controller.BaseController

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton()
class OfstedFormsController @Inject()(fs: FormSubmissionService) extends BaseController {

  // user functions
  def createDraft(): Action[AnyContent] = Action.async {
    implicit request =>
      val draftForm = DraftForm(randomUUID().toString, "", Occurrence(AuthenticatedUser("", ""), ""))
      fs.saveForm(draftForm).map {
        case Some(res) => Ok(Json.obj("id" -> res.id))
        case None => BadRequest("Initial draft form record was not inserted")
      }.recover {
        case e: Throwable => InternalServerError(s"${e.getMessage}")
      }
  }

  def getForm(id: String): Action[AnyContent] = Action.async {
    implicit request =>
      fs.getForm(id).map {
        case Some(form) => Ok(Json.toJson(form))
        case None => BadRequest(s"A form does not exist with id : {$id}")
      }.recover {
        case e: Throwable => InternalServerError(s"${e.getMessage}")
      }
  }

  def submitForm(id: String) = Action.async(parse.json) {
    implicit request =>
      val formSubmission = request.body.validate[DraftForm]
      formSubmission.fold(
        errors => {
          Future.successful(BadRequest(s"Invalid submission payload: ${JsError.toJson(errors)}"))
        },
        form => {
          fs.updateForm(form).map { _ => NoContent
          }.recover {
            case e: Throwable => InternalServerError(s"${e.getMessage}")
          }
        }
      )
  }

  // admin functions

  //  def findDraft(userId: String, formType: String) = {
  //    //This represents the latest form which is in draft for this user and not in submitted - there should only be one of these
  //    // first get all the draft forms
  //    // then get all the submitted forms
  //  }

  //  def findSubmitted(userId: String, formType: String) = ??? //This represents the latest submitted form and not in annotated or assessed - there should only be one of these
  //  def findAnnotatedForm(userId: String, formType: String) = ??? //This represents the latest annotated form and not in assessed - there should only be one of these
  //  def findAssessed(userId: String, formType: String) = ??? // This represents the latest assessed - there should only be one of these

  //if multiple forms return the latest form by ordering on timestamp
  //  def findDraftForm(userId: String, formType: String) = ??? //This represents the latest submitted form and not in annotated or assessed - there should only be one of these
  //  def findSubmittedForm(userId: String, formType: String) = ??? //This represents the latest annotated form and not in assessed - there should only be one of these
  //  def findApprovedForm(userId: String, formType: String) = ??? // This represents the latest assessed - there should only be one of these
  //  def findRejectedForm(userId: String, formType: String) = ??? // This represents the latest assessed - there should only be one of these

  //  // POSTs
  //  def saveDraftForm() = {
  //    //
  //  }

  //  def saveSubmittedForm() = ???
  //
  //  def saveAnnotatedForm() = ???
  //
  //  def saveAssessedForm() = ???

  //
  //  def save() = Action.async(parse.json) {
  //    implicit request =>
  //      val formSubmission = request.body.validate[FormSubmission]
  //      formSubmission.fold(
  //        errors => {
  //          Future.successful(BadRequest(Json.obj("message" -> JsError.toJson(errors))))
  //        },
  //        fs => {
  //          fs.saveForm(fs).map { _ => Created
  //          }.recover {
  //            case t: Throwable ⇒ Logger.error(s"Failed to save the form identifiers")
  //              InternalServerError
  //          }
  //        }
  //      )
  //  }

  //  def get(userId: String): Action[AnyContent] = Action.async {
  //    implicit request =>
  //      fs.getFormId(userId).map {
  //        case Some(x) => Ok(Json.toJson(x))
  //        case None => BadRequest(s"No user exists with user id: ${userId}")
  //      }.recover {
  //        case t: Throwable ⇒ Logger.error(s"Failed to get form submission identifiers")
  //          InternalServerError
  //      }
  //  }
  //
  //  def delete(userId: String): Action[AnyContent] = Action.async {
  //    implicit request =>
  //      fs.deleteForm(userId).map(_ => Ok("")).recover {
  //        case t: Throwable ⇒ Logger.error(s"Failed to delete form submission identifiers")
  //          InternalServerError
  //      }
  //  }

}