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

package uk.gov.hmrc.ofstedforms.services

//
//class FormSubmissionServiceSpec extends UnitSpec
//  with ScalaFutures
//  with MockitoSugar
//  with BeforeAndAfterEach
//  with MongoSpecSupport
//  with Matchers {
//  implicit val reactiveMongoComponent = new ReactiveMongoComponent {
//    override def mongoConnector = mongoConnectorForTest
//  }
//
//  val repository = new DefaultDraftFormRepository()
//
//  override protected def beforeEach(): Unit = {
//    await(repository.drop)
//  }
//
//  val service = new DefaultFormSubmissionService(repository)
//
//  "saveFormSubmission" should {
//    "insert record" in {
//      await(service.saveForm(FormSubmission("1", "AAA"))) shouldBe DefaultWriteResult(true,1,List(),None,None,None)
//    }
//  }
//
//  "findFormSubmission" should {
//    "find record" in {
//      await(service.saveForm(FormSubmission("1", "AAA"))) shouldBe DefaultWriteResult(true,1,List(),None,None,None)
//      await(service.getFormId("1")) shouldBe Some(FormSubmission("1", "AAA"))
//    }
//  }
//
//  "removeFormSubmission" should {
//    "remove record" in {
//      await(service.saveForm(FormSubmission("1", "AAA"))) shouldBe DefaultWriteResult(true,1,List(),None,None,None)
//      await(service.deleteForm("1")) shouldBe 1
//    }
//  }
//
//}
