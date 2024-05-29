package core.data.network.dto

object ApiConst {
    private const val API_KEY = "ADD YOUR API KEY HERE "
    const val TEXT_MODEL = "gemini-1.5-flash-latest"
    fun constructUrl(model: String = TEXT_MODEL) =
        "https://generativelanguage.googleapis.com/v1beta/models/$model:generateContent?key=$API_KEY"
}

