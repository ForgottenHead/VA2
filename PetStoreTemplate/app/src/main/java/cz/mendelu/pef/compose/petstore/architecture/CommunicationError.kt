package cz.mendelu.pef.compose.petstore.architecture

data class CommunicationError(
    val code: Int,
    var message: String?,
    var secondaryMessage: String? = null)