package service;

import exception.MovieSearchException
import junit.framework.Assert.assertEquals
import org.junit.runner.RunWith

import org.junit.Test
import org.mockito.runners.MockitoJUnitRunner

val searchWord = "guardians+of+the+galaxy+2";
val transformedResponse = "[{\"overview\":\"The Guardians must fight to keep their newfound family together as they " +
        "unravel the mysteries of Peter Quill's true parentage.\",\"popularity_summary\":\"42.305 out of 10870\"," +
        "\"poster_image_url\":\"https://image.tmdb.org/t/p/w200//y4MBh0EjBlMuOzv9axM4qJlmhzz.jpg\",\"movie_id\":" +
        "\"283995\",\"title\":\"Guardians of the Galaxy Vol. 2\"}]"

var movieService = MovieService()

@RunWith(MockitoJUnitRunner::class)
class MovieServiceTest {

    @Test
    fun `test get results happy path`() {
        val results = movieService.getResults(searchWord)
        assertEquals(results, transformedResponse)
    }

    @Test(expected = MovieSearchException::class)
    fun `test get results 422`() {
        movieService.getResults("")
    }
}