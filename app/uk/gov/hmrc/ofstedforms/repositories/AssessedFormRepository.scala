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

package uk.gov.hmrc.ofstedforms.repositories

import com.google.inject.ImplementedBy
import javax.inject.{Inject, Singleton}
import play.api.libs.json.{JsObject, Json}
import play.modules.reactivemongo.ReactiveMongoComponent
import reactivemongo.play.json.ImplicitBSONHandlers._
import uk.gov.hmrc.mongo.ReactiveRepository
import uk.gov.hmrc.ofstedforms.models._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@ImplementedBy(classOf[DefaultAssessedFormRepository])
trait AssessedFormRepository {
  def saveForm(form: Form): Future[Option[Form]]

  def getForm(id: String): Future[Option[Form]]

  def getForms(): Future[List[Form]]
}

@Singleton
class DefaultAssessedFormRepository @Inject()(reactiveMongoComponent: ReactiveMongoComponent)
  extends ReactiveRepository(
    "assessments",
    reactiveMongoComponent.mongoConnector.db,
    Form.formFormat)
    with AssessedFormRepository {

  override def saveForm(assessedForm: Form): Future[Option[Form]] =
    collection
      .findAndUpdate(Json.obj("id" -> assessedForm.id), assessedForm, fetchNewObject = true, upsert = true)
      .map(_.result[Form])


  override def getForm(id: String): Future[Option[Form]] =
    collection
      .find(Json.obj("id" -> id), projection = Option.empty[JsObject])
      .one[Form]


  override def getForms(): Future[List[Form]] = findAll()

}

