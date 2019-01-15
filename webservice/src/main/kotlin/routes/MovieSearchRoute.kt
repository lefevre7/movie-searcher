package routes

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.content.TextContent
import io.ktor.http.ContentType
import io.ktor.response.respond
import io.ktor.routing.*
import service.MovieService

fun Route.movie(movieService: MovieService) {

    route("/movie") {

        get("/") {

            val movieName = call.parameters["search"]
            val results = movieService.getResults(movieName!!)
            call.respond(HttpStatusCode.OK, TextContent(results, ContentType.Application.Json))
        }
    }
}
