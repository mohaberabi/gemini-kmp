package core.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class GeminiResponse(
    val candidates: List<Candidate>
)

@Serializable

data class Candidate(
    val content: Content,
    val finishReason: String? = null,
    val index: Int? = null,
    val safetyRatings: List<SafetyRating>? = null
)


@Serializable
data class SafetyRating(
    val category: String? = null,
    val probability: String? = null
)