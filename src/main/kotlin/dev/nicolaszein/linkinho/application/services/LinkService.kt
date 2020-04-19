package dev.nicolaszein.linkinho.application.services

import dev.nicolaszein.linkinho.application.commands.AddLinkClickCommand
import dev.nicolaszein.linkinho.application.commands.CreateLinkCommand
import dev.nicolaszein.linkinho.domain.entities.Link
import dev.nicolaszein.linkinho.domain.repositories.LinksRepository
import dev.nicolaszein.linkinho.infra.Base62
import org.springframework.stereotype.Service

@Service
class LinkService(
    private val linksRepository: LinksRepository,
    private val base62: Base62
) {
    fun create(command: CreateLinkCommand): Link {
        val link = linksRepository.save(command.toDomain())

        link.tag = base62.encode(link.id?.toBigInteger()!!)

        return linksRepository.save(link)
    }

    fun addLinkClick(command: AddLinkClickCommand): Link {
        val link = command.link
        link.addClick(userAgent = command.userAgent, ip = command.ip)

        return linksRepository.save(link)
    }
}
