package dev.nicolaszein.linkinho.domain.repositories

import dev.nicolaszein.linkinho.domain.entities.Link
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
@RunWith(SpringRunner::class)
class LinksRepositoryTest{
    @Autowired
    private lateinit var repository: LinksRepository

    @Test
    fun `find link by tag`() {
        val persistedLink = Link(longUrl = "https://long.url", title = "A long url")
        persistedLink.tag = "a-tag"
        repository.save(persistedLink)

        val link = repository.findByTag("a-tag")

        assertEquals(persistedLink.id, link?.id)
    }
}
