package dev.nicolaszein.linkinho.helpers

import dev.nicolaszein.linkinho.domain.entities.Link
import dev.nicolaszein.linkinho.domain.repositories.LinksRepository
import dev.nicolaszein.linkinho.infra.Base62
import org.springframework.stereotype.Service

@Service
class LinkFactory(
    private val linksRepository: LinksRepository,
    private val base62: Base62
) {
    fun create(): Link {
        val link = Link(longUrl = "https://youtube.com", title = "Youtube URL")
        linksRepository.save(link)
        link.tag = base62.encode(link?.id?.toBigInteger()!!)
        linksRepository.save(link)

        return link
    }
}
