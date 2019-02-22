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

//
//class OfstedFormsRepositoriesSpec extends WordSpec
//  with Matchers
//  with MongoSpecSupport
//  with BeforeAndAfterEach
//  with Awaiting
//  with CurrentTime
//  with Eventually
//  with LogCapturing {
//
//  implicit val reactiveMongoComponent = new ReactiveMongoComponent {
//    override def mongoConnector = mongoConnectorForTest
//  }
//
//  val repository = new DefaultDraftFormRepository()
//
//  override protected def beforeEach(): Unit = await(repository.drop)
//
//  "Inserting a new record" should {
//    "be successful" in {
//      val formData = FormSubmission("1", "1111-AAAA-2222")
//      await(repository.insertFormData(formData))
//
//      val result = await(repository.findFormByUserId("1")).get
//      result shouldEqual formData
//    }
//  }
//
//  "Deleting a record" should {
//    "be successful" in {
//      val formData = FormSubmission("1", "1111-AAAA-2222")
//      await(repository.insertFormData(formData))
//
//      val result = await(repository.deleteFormByUserId("1"))
//      result shouldEqual 1
//    }
//  }
//
//}