package dto

data class Result(
        val vote_count: Int,
        val id: Int?,
        val video: Boolean?,
        val vote_average: Float?,
        val title: String?,
        val popularity: Float,
        val poster_path: String?,
        val original_language: String?,
        val original_title: String?,
        val genre_ids: ArrayList<Int>?,
        val backdrop_path: String?,
        val adult: Boolean?,
        val overview: String?,
        val release_date: String?
) {
}