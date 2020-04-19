package dev.nicolaszein.linkinho.web.api.exceptions

data class ResourceNotFound(override val message: String) : Exception(message)
