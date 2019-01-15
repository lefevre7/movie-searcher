package service

import java.util.ArrayList

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.typesafe.config.ConfigFactory
import dto.MovieSearchResponse
import exception.MovieSearchException
import khttp.responses.Response
import org.json.JSONArray
import org.slf4j.LoggerFactory

class MovieService {

    val logger = LoggerFactory.getLogger(MovieService::class.java)

    //todo tests
    fun getResults(movieName: String): String {
        logger.debug("Making tmdb call.")
        val result = khttp.get(
                url = ConfigFactory.load("application.conf").getString("tmdb.api.url"),
                params = mapOf("api_key" to "ed15796d14faa4afd54bcafbae1afc53",
                        "query" to movieName,
                        "include_adult" to "false",
                        "language" to "en-US",
                        "region" to "US") as Map<String, String>)

        if (result.statusCode.equals(200))
            return transformResults(result)
        else {
            logger.error("")
            throw MovieSearchException("Error, status code is: " + result.statusCode)
        }

    }

    fun transformResults(result: Response): String {

        logger.debug("Transforming results.")
        val mapper = jacksonObjectMapper().apply {
            setSerializationInclusion(JsonInclude.Include.NON_NULL)
        }

        val resp = mapper.readValue<MovieSearchResponse>(result.jsonObject.toString())
        val jsonArray = JSONArray(resp.results?.subList(0, if (resp.results.size > 8) 9 else resp.results.size) as Collection<Any>?)

        var i = 0;
        val fEResultArray = ArrayList<String>()
        for (jsonObj in jsonArray) {
            fEResultArray.add("{ \"movie_id\" : \"" + resp.results?.get(index = i)?.id.toString() + "\"," +
                    "\"title\" : \"" + resp.results?.get(index = i)?.title.toString() + "\"," +
                    "\"poster_image_url\" : \"" + "https://image.tmdb.org/t/p/w200/" + resp.results?.get(index = i)?.poster_path.toString() + "\"," +
//                           "\"popularity_summary\" : \"" + (resp.results?.get(index = i)?.popularity ?: 0).toString().toFloat() / (resp.results?.get(index = i)?.vote_count ?: 0).toString().toFloat() + "\"," +
                    "\"popularity_summary\" : \"" + resp.results?.get(index = i)?.popularity.toString() + " out of " + resp.results?.get(index = i)?.vote_count.toString() + "\"," +
                    "\"overview\" : \"" + resp.results?.get(index = i)?.overview.toString() + "\"}"
            )
            i += 1;
        }
        return fEResultArray.toString();
    }
}

