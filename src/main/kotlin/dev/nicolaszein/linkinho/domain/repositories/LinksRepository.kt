package dev.nicolaszein.linkinho.domain.repositories

import dev.nicolaszein.linkinho.domain.entities.Link
import org.springframework.data.repository.Repository

interface LinksRepository: Repository<Link, Int> {
    fun save(link: Link): Link

    fun findByTag(tag: String): Link?
}
