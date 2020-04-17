package dev.nicolaszein.linkinho.application.commands

import dev.nicolaszein.linkinho.domain.entities.Link

data class CreateLinkCommand(
    val longUrl: String,
    val title: String
) {
    fun toDomain() = Link(
        longUrl = this.longUrl,
        title = this.title
    )
}
