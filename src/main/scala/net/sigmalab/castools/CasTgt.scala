package net.sigmalab.castools

import caseapp._
import com.lightbend.rp.reactivecli.http._
import com.lightbend.rp.reactivecli.concurrent._
import net.sigmalab.castools.tgt.Options
import slogging._

object CasTgt extends CaseApp[Options] with StrictLogging {

  override def main(args: Array[String]) = super.main(args)

  def run(options: Options, arg: RemainingArgs): Unit = {

    implicit val settings: HttpSettings = HttpSettings.default

    val request = new HttpRequest("http://localhost:8081/cas/v1/tickets").post

    val response = wrapFutureOption(
      for {
        response <- Http.apply(request)
      } yield response)

    System.out.println(response.isCompleted)
  }

}
