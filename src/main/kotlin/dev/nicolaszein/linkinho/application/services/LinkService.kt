package dev.nicolaszein.linkinho.application.services

import dev.nicolaszein.linkinho.application.commands.CreateLinkCommand
import dev.nicolaszein.linkinho.domain.entities.Link
import dev.nicolaszein.linkinho.domain.repositories.LinksRepository

class LinkService(
    private val linksRepository: LinksRepository
) {
    fun create(command: CreateLinkCommand): Link {
        return linksRepository.save(command.toDomain())
    }
}
