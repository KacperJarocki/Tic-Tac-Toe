package org.game
import org.http4k.core.Body
import org.http4k.core.ContentType.Companion.TEXT_HTML
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.isHtmx
import org.http4k.core.with
import org.http4k.routing.Router.Companion.orElse
import org.http4k.routing.bind
import org.http4k.routing.htmxWebjars
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer
import org.http4k.template.HandlebarsTemplates
import org.http4k.template.ViewModel
import org.http4k.template.viewModel
import java.util.Date


fun main() {
    val render = HandlebarsTemplates().CachingClasspath();
    val view = Body.viewModel(render,TEXT_HTML).toLens()
    val app = routes(
        htmxWebjars(),
        "/" bind GET to routes(
            // Respond to htmx powered requests
            Request.isHtmx bind { Response(OK).with(view of Index )},
            // Standard requests get routed to here
            orElse bind {
                Response(OK).with(view of Index)
            }
        ),
        "/clicked" bind GET to routes(
            Request.isHtmx bind { Response(OK).body("Clicked at ${Date()}") },
            orElse bind { Response(OK).body("Not htmx") })

    )
    app.asServer(SunHttp(9000)).start()
    System.err.println(render)
}
data object Index : ViewModel

