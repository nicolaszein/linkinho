package dev.nicolaszein.linkinho.application.commands

import dev.nicolaszein.linkinho.domain.entities.Link

data class AddLinkClickCommand(
    val link: Link,
    val userAgent: String,
    val ip: String
)
