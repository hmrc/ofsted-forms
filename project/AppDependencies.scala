import play.core.PlayVersion.current
import sbt._

object AppDependencies {

  val compile = Seq(

    "uk.gov.hmrc"             %% "simple-reactivemongo"     % "7.16.0-play-26",
    "uk.gov.hmrc"             %% "bootstrap-play-26"        % "0.37.0"
  )

  val test = Seq(
    "uk.gov.hmrc"             %% "reactivemongo-test"       % "4.10.0-play-26"        % "test",
    "org.scalatest"           %% "scalatest"                % "3.0.4"                 % "test",
    "org.mockito"              % "mockito-core"             % "2.7.22"                % "test,it",
    "uk.gov.hmrc"             %% "hmrctest"                 % "3.0.0"                 % "test,it",
    "com.typesafe.play"       %% "play-test"                % current                 % "test",
    "org.pegdown"             %  "pegdown"                  % "1.6.0"                 % "test, it",
    "com.github.tomakehurst"   % "wiremock"                 % "2.6.0"                 % "test,it",
    "org.mockito"             %% "mockito-scala"            % "1.1.4"                 % "test",
    "uk.gov.hmrc"             %% "service-integration-test" % "0.6.0-play-26"         % "test, it",
    "org.scalatestplus.play"  %% "scalatestplus-play"       % "3.1.0"                 % "test, it",
    "com.github.tomakehurst"  %  "wiremock-jre8"            % "2.21.0"                % "test"
  )

}
