package es.luis.examples.akka.streaming

import akka.actor.ActorSystem
import akka.stream.scaladsl.{Flow, Sink, Source}
import akka.stream.Materializer

object Starting extends App {

  implicit val system: ActorSystem = ActorSystem("StreamingStarting")

  implicit val materializer: Materializer = Materializer(system)


  // Sources
  val source = Source(0 to 100)

  // Sink
  val sink = Sink.foreach[Int](println(_))

  val graph = source.to(sink)
//  graph.run()

  // Flows
  val flow = Flow[Int].map(_ + 1).filter( _ % 2 == 0)
  val sourceAndFlow = source.via(flow)
//  val flowAndSink = flow.to(sink)

  sourceAndFlow.to(sink).run()

}
