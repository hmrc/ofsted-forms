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

import java.util.UUID
import java.util.UUID.randomUUID

import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.mvc.Result
import play.api.test.FakeRequest
import play.api.test.Helpers._
import uk.gov.hmrc.ofstedforms.models.{AuthenticatedUser, Form, Occurrence}
import uk.gov.hmrc.ofstedforms.service.FormService

import scala.concurrent.Future


class OfstedFormsControllerSpec extends PlaySpec with GuiceOneAppPerTest with MockitoSugar with PlayControllerStubs {

//  val formService: FormService = mock[FormService]
//
//  val form = Form(UUID.randomUUID().toString, "SC1", Occurrence(AuthenticatedUser(UUID.randomUUID().toString, "user.email@user.com"), "Jan 1 1900"))
//  "Ofsted Forms Controller POST" should {
//
//    "create an initial draft form" in {
//      val draftForm = Form(randomUUID().toString, "", Occurrence(AuthenticatedUser("", ""), ""))
//
//      val controller = new OfstedFormsController(formService, stubMessagesControllerComponents)
//      val response: Future[Result] = controller.createDraft().apply(FakeRequest(POST, "/form")
//        .withHeaders(CONTENT_TYPE -> JSON)
//        .withJsonBody(Json.toJson[Form](draftForm)))
//
//    }
//  }

}