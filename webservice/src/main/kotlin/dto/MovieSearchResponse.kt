package dto

data class MovieSearchResponse(
        val page: Int?,
        val total_results: Int?,
        val total_pages: Int?,
        val results: ArrayList<Result>?
) {
}