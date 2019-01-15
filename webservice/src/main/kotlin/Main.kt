import com.typesafe.config.ConfigFactory
import io.ktor.application.*
import io.ktor.features.CORS
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import routes.movie
import service.MovieService
import java.time.Duration


fun Application.module() {
    val movieService = MovieService()

    install(Routing) {
        movie(movieService)
    }

    install(CORS)
    {
        method(HttpMethod.Options)
        method(HttpMethod.Get)
        header(HttpHeaders.XForwardedProto)
        header(HttpHeaders.Accept)
        header(HttpHeaders.AccessControlAllowHeaders)
        header(HttpHeaders.AccessControlAllowMethods)
        header(HttpHeaders.AccessControlAllowOrigin)
        header(HttpHeaders.AccessControlAllowCredentials)
        anyHost()
        allowCredentials = true
        maxAge = Duration.ofDays(1)
    }

}

fun main(args: Array<String>) {
    embeddedServer(Netty, ConfigFactory.load("application.conf").getInt("ktor.deployment.port"), watchPaths = listOf("MainKt"), module = Application::module).start()
}