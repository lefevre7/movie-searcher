package dto

data class FEResult(
        val movie_id: Int?,
        val title: String?,
        val poster_image_url: String?,
        val popularity_summary: Float?,
        val overview: String?
) {
}