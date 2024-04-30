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
    val game = Game(3);
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
        "/clicked1" bind GET to routes(
            Request.isHtmx bind {
                if(game.whoseTurn)game.putX(0,0) else game.putO(0,0)
                Response(OK).body("<div id=\"cell1\" hx-get=\"/clicked1\" hx-trigger=\"click\">"+game.getIndex(0,0)+"<div> ")
                                },
            orElse bind { Response(OK).body("Not htmx") }),
        "/clicked2" bind GET to routes(
            Request.isHtmx bind {
                if(game.whoseTurn)game.putX(1,0) else game.putO(1,0)
                Response(OK).body("<div id=\"cell2\" hx-get=\"/clicked2\" hx-trigger=\"click\">"+game.getIndex(1,0)+"<div> ")
            },
            orElse bind { Response(OK).body("Not htmx") }),
        "/clicked3" bind GET to routes(
            Request.isHtmx bind {
                if(game.whoseTurn)game.putX(2,0) else game.putO(2,0)
                println(game.Board)
                Response(OK).body("<div id=\"cell3\" hx-get=\"/clicked3\" hx-trigger=\"click\">"+game.getIndex(2,0)+"<div> ")
            },
            orElse bind { Response(OK).body("Not htmx") }),
        "/clicked4" bind GET to routes(
            Request.isHtmx bind {
                if(game.whoseTurn)game.putX(0,1) else game.putO(0,1)
                Response(OK).body("<div id=\"cell4\" hx-get=\"/clicked4\" hx-trigger=\"click\">"+game.getIndex(0,1)+"<div> ")
            },
            orElse bind { Response(OK).body("Not htmx") }
        ),
        "/clicked5" bind GET to routes(
            Request.isHtmx bind {
                if(game.whoseTurn)game.putX(1,1) else game.putO(1,1)
                Response(OK).body("<div id=\"cell5\" hx-get=\"/clicked5\" hx-trigger=\"click\">"+game.getIndex(1,1)+"<div> ")
            },
            orElse bind { Response(OK).body("Not htmx") }),
        "/clicked6" bind GET to routes(
            Request.isHtmx bind {
                if(game.whoseTurn)game.putX(2,1) else game.putO(2,1)
                Response(OK).body("<div id=\"cell6\" hx-get=\"/clicked6\" hx-trigger=\"click\">"+game.getIndex(2,1)+"<div> ")
            },
            orElse bind { Response(OK).body("Not htmx") }),
        "/clicked7" bind GET to routes(
            Request.isHtmx bind {
                if(game.whoseTurn)game.putX(0,2) else game.putO(0,2)
                Response(OK).body("<div id=\"cell7\" hx-get=\"/clicked7\" hx-trigger=\"click\">"+game.getIndex(0,2)+"<div> ")
            },
            orElse bind { Response(OK).body("Not htmx") }),
        "/clicked8" bind GET to routes(
            Request.isHtmx bind {
                if(game.whoseTurn)game.putX(1,2) else game.putO(1,2)
                Response(OK).body("<div id=\"cell8\" hx-get=\"/clicked8\" hx-trigger=\"click\">"+game.getIndex(1,2)+"<div> ")
            },
            orElse bind { Response(OK).body("Not htmx") }),
        "/clicked9" bind GET to routes(
            Request.isHtmx bind {
                if(game.whoseTurn)game.putX(2,2) else game.putO(2,2)
                Response(OK).body("<div id=\"cell9\" hx-get=\"/clicked9\" hx-trigger=\"click\">"+game.getIndex(2,2)+"<div> ")
            },
            orElse bind { Response(OK).body("Not htmx") }),
        "/whosturn" bind {
            val status = if(game.checkWin() == 1) "X wins" else if(game.checkWin() == 2) "O wins" else game.whoseTurnIsIt().toString();
            Response(OK).body(status)
                         },
        "/reset"    bind {game.restart();Response(OK).body("Game restarted")}

    )
    app.asServer(SunHttp(9000)).start()
    System.err.println(render)
}

data object Index : ViewModel

