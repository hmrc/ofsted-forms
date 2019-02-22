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
import play.modules.reactivemongo.ReactiveMongoComponent
import reactivemongo.bson.BSONDocument
import reactivemongo.play.json.ImplicitBSONHandlers._
import uk.gov.hmrc.mongo.ReactiveRepository
import uk.gov.hmrc.ofstedforms.models.DraftForm

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@ImplementedBy(classOf[DefaultDraftFormRepository])
trait DraftFormRepository {
  def saveForm(d: DraftForm): Future[Option[DraftForm]]

  def findById(id: String): Future[Option[DraftForm]]
}

@Singleton
class DefaultDraftFormRepository @Inject()(reactiveMongoComponent: ReactiveMongoComponent)
  extends ReactiveRepository(
    "draft-forms",
    reactiveMongoComponent.mongoConnector.db,
    DraftForm.draftFormFormat)
    with DraftFormRepository {

  override def saveForm(draftForm: DraftForm): Future[Option[DraftForm]] = {
    collection
      .findAndUpdate(BSONDocument("id" -> draftForm.id), draftForm, fetchNewObject = true, upsert = true)
      .map(_.result[DraftForm])
  }

  override def findById(id: String): Future[Option[DraftForm]] =
    collection.find(BSONDocument("id" -> id)).one[DraftForm]

}


