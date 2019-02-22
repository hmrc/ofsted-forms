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
import uk.gov.hmrc.ofstedforms.models.DraftForm
import uk.gov.hmrc.ofstedforms.repositories.DraftFormRepository

import scala.concurrent.Future

@ImplementedBy(classOf[DefaultFormSubmissionService])
trait FormSubmissionService {
  def saveForm(df: DraftForm): Future[Option[DraftForm]]
}

class DefaultFormSubmissionService @Inject()
  (
    draftFormRepository: DraftFormRepository
  ) extends FormSubmissionService
{
  override def saveForm(draft: DraftForm): Future[Option[DraftForm]] = draftFormRepository.saveForm(draft)
}