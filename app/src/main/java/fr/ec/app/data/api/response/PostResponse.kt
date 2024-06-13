package fr.ec.app.data.api.response

data class PostResponse(
    val id : String? = null,
    val name : String? = null,
    val tagline : String?= null,
    val thumbnail : Thumbnail?= null
)