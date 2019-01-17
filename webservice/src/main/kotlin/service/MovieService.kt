package service

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.typesafe.config.ConfigFactory
import dto.MovieSearchResponse
import exception.MovieSearchException
import khttp.responses.Response
import org.json.JSONArray
import org.json.JSONObject
import org.slf4j.LoggerFactory

class MovieService {

    val logger = LoggerFactory.getLogger(MovieService::class.java)
    val conf = ConfigFactory.load("application.conf")

    //todo test with mockito, okhttp, and retrofit to enqueue responses and not make live calls - timeout test
    fun getResults(movieName: String): String {
        logger.debug("Making tmdb call.")
        val result = khttp.get(
                url = conf.getString("tmdb.api.url"),
                params = mapOf("api_key" to conf.getString("tmdb.api.key"),
                        "query" to movieName,
                        "include_adult" to "false",
                        "language" to "en-US",
                        "region" to "US") as Map<String, String>)

        if (result.statusCode.equals(200))
            return transformResults(result)
        else {
            logger.error("Error, status code is: " + result.statusCode)
            throw MovieSearchException("Error, status code is: " + result.statusCode)
        }
    }

    fun transformResults(result: Response): String {

        logger.debug("Transforming results.")
        val mapper = jacksonObjectMapper().apply {
            setSerializationInclusion(JsonInclude.Include.NON_NULL)
        }

        val resp = mapper.readValue<MovieSearchResponse>(result.jsonObject.toString())
        val jsonArray = JSONArray(resp.results?.subList(0, if (resp.results.size > 10) 10 else resp.results.size) as Collection<Any>?)

        var i = 0;
        val feJSONArray = JSONArray()
        for (jsonObj in jsonArray) {
            val feJSONObject = JSONObject()
            feJSONObject.put("movie_id", resp.results?.get(index = i)?.id.toString())
            feJSONObject.put("title", resp.results?.get(index = i)?.title.toString())
            feJSONObject.put("poster_image_url", conf.getString("tmdb.img.url") + resp.results?.get(index = i)?.poster_path.toString())
            feJSONObject.put("popularity_summary", resp.results?.get(index = i)?.popularity.toString() + " out of " + resp.results?.get(index = i)?.vote_count.toString())
            feJSONObject.put("overview", resp.results?.get(index = i)?.overview.toString())
            feJSONArray.put(feJSONObject)
            i += 1;
        }
        return feJSONArray.toString();
    }
}

