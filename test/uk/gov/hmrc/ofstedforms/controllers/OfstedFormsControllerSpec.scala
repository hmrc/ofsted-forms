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

class OfstedFormsControllerSpec extends ControllersSpecBase with OfstedMocks {

//  private implicit val materializer = mock[Materializer]
//
//  when(mockFormSubmissionService.getFormId("1")) thenReturn Some(FormSubmission("1", "AAA"))
//  when(mockFormSubmissionService.getFormId("2")) thenReturn None
//  when(mockFormSubmissionService.deleteForm("1")) thenReturn 1
//  when(mockFormSubmissionService.saveForm(FormSubmission("1", "AAA"))) thenReturn DefaultWriteResult(true, 1, List(), None, None, None)
//
//  val controller = new OfstedFormsController(mockFormSubmissionService)
//
//  "OfstedFormsController" should {
//    "should return ok if the form is requested and it exists" in {
//      val action: Future[Result] = controller.get("1").apply(FakeRequest())
//      status(action) shouldBe OK
//    }
//
//    "should return bad request if the form is requested and it does not exist" in {
//      val action: Future[Result] = controller.get("2").apply(FakeRequest())
//      status(action) shouldBe BAD_REQUEST
//    }
//
//    "should return ok if we delete a form and it exists " in {
//      val action: Future[Result] = controller.delete("1").apply(FakeRequest())
//      status(action) shouldBe OK
//    }
//
//    "should return created if a request is made to save a new form" in {
//      val fakeRequest = FakeRequest(method = "POST", uri = "", headers = FakeHeaders(Seq("Content-type" -> "application/json")), body = Json.obj("userId" -> "1", "formId" -> "AAA"))
//      val action: Future[Result] = controller.save().apply(fakeRequest)
//      status(action) shouldBe CREATED
//    }
//
//    "should return bad request if a request is made to save the form but the payload is not correct" in {
//      val fakeRequest = FakeRequest(method = "POST", uri = "", headers = FakeHeaders(Seq("Content-type" -> "application/json")), body = Json.obj("notaCol" -> "1", "formId" -> "AAA"))
//      val action: Future[Result] = controller.save().apply(fakeRequest)
//      status(action) shouldBe BAD_REQUEST
//    }
//
//  }

}