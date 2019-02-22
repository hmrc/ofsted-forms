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

import org.scalatest.concurrent.Eventually
import org.scalatest.{BeforeAndAfterEach, Matchers, WordSpec}
import play.modules.reactivemongo.ReactiveMongoComponent
import uk.gov.hmrc.mongo.{Awaiting, CurrentTime, MongoSpecSupport}
import uk.gov.hmrc.ofstedforms.models.{AuthenticatedUser, DraftForm, Occurrence}
import uk.gov.hmrc.play.test.LogCapturing

class DraftFormRepositorySpec extends WordSpec
  with Matchers
  with MongoSpecSupport
  with BeforeAndAfterEach
  with Awaiting
  with CurrentTime
  with Eventually
  with LogCapturing {

  implicit val reactiveMongoComponent = new ReactiveMongoComponent {
    override def mongoConnector = mongoConnectorForTest
  }

  val repository = new DefaultDraftFormRepository()

  override protected def beforeEach(): Unit = await(repository.drop)

  "A Draft Form Repository" can {
    "save" should {
      "insert a record" in {
        val id = java.util.UUID.randomUUID().toString
        val draft = DraftForm("1", "SC1", Occurrence(AuthenticatedUser("1234567890", "user@ofsted.com"), "2019-01-20"))

        await(repository.saveForm(draft)) shouldBe Some(draft)

//        val result = await(repository.findById(id))
//        result shouldEqual Some(draft)
      }
    }
  }
}