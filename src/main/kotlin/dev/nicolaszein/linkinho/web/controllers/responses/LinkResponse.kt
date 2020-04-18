package dev.nicolaszein.linkinho.web.controllers.responses

import dev.nicolaszein.linkinho.domain.entities.Link
import java.time.LocalDateTime

data class LinkResponse(
    val title: String,
    val tag: String,
    val shortUrl: String,
    val longUrl: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

fun Link.toResponse(host: String) = LinkResponse(
    title = this.title,
    tag = "/${this.tag}",
    shortUrl = "${host}/${this.tag}",
    longUrl = this.longUrl,
    createdAt = this.createdAt!!,
    updatedAt = this.updatedAt!!
)
