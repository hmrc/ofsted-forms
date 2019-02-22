import play.core.PlayVersion.current
import sbt._

object AppDependencies {

  val compile = Seq(

    "uk.gov.hmrc" %% "simple-reactivemongo" % "7.9.0-play-25",
    "uk.gov.hmrc"             %% "bootstrap-play-25"        % "4.9.0"
  )

  val test = Seq(
    "org.mockito"              % "mockito-core"             % "2.7.22"                % "test,it",
    "uk.gov.hmrc"             %% "hmrctest"                 % "3.0.0"                 % "test,it",
    "org.scalatest"           %% "scalatest"                % "3.0.4"                 % "test",
    "com.typesafe.play"       %% "play-test"                % current                 % "test",
    "org.pegdown"             %  "pegdown"                  % "1.6.0"                 % "test, it",
    "uk.gov.hmrc"             %% "service-integration-test" % "0.2.0"                 % "test, it",
    "org.scalatestplus.play"  %% "scalatestplus-play"       % "2.0.0"                 % "test, it",
    "uk.gov.hmrc"             %% "reactivemongo-test"       % "3.1.0"                 % "test,it",
    "com.github.tomakehurst"   % "wiremock"                 % "2.6.0"                 % "test,it"
  )

}
