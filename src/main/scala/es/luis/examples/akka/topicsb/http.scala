package es.luis.examples.akka.topicsb

import spray.json.{DefaultJsonProtocol, RootJsonFormat}

case class IpInfo(ip: String)

object JsonProtocolm extends DefaultJsonProtocol{
  implicit val format: RootJsonFormat[IpInfo] = jsonFormat1(IpInfo.apply)
}

object http {



}
