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

package uk.gov.hmrc.ofstedforms.service

import com.google.inject.ImplementedBy
import javax.inject.Inject
import play.api.Logger
import uk.gov.hmrc.ofstedforms.models.{Form, FormSnapshot}
import uk.gov.hmrc.ofstedforms.repositories.{AssessedFormRepository, DraftFormRepository, SubmittedFormRepository}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@ImplementedBy(classOf[DefaultFormService])
trait FormService {
  def saveDraft(form: Form): Future[Option[Form]]

  def saveSubmission(form: Form): Future[Option[Form]]

  def saveAssessment(form: Form): Future[Option[Form]]

  def getWorkList(): Future[Set[Form]]

  def getForm(id: String): Future[FormSnapshot]
}

class DefaultFormService @Inject()(
                                    draftFormRepository: DraftFormRepository,
                                    submissionFormRepository: SubmittedFormRepository,
                                    assessedFormRepository: AssessedFormRepository
                                  ) extends FormService {

  override def saveDraft(form: Form): Future[Option[Form]] = draftFormRepository.saveForm(form)

  override def saveSubmission(form: Form): Future[Option[Form]] = submissionFormRepository.saveForm(form)

  override def saveAssessment(form: Form): Future[Option[Form]] = assessedFormRepository.saveForm(form)

  override def getWorkList(): Future[Set[Form]] = {
    for {
      submissions <- submissionFormRepository.getForms()
      assessed <- assessedFormRepository.getForms()
    } yield (submissions ++ assessed).toSet
  }

  override def getForm(id: String): Future[FormSnapshot] = {
    for {
      draft <- draftFormRepository.getForm(id)
      submission <- submissionFormRepository.getForm(id)
      assessment <- assessedFormRepository.getForm(id)
    } yield FormSnapshot(draft, submission, assessment)
  }

}