package dev.nicolaszein.linkinho.web.controllers

import dev.nicolaszein.linkinho.domain.entities.Link
import dev.nicolaszein.linkinho.domain.repositories.LinksRepository
import dev.nicolaszein.linkinho.infra.Base62
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class LinkRedirectControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var linksRepository: LinksRepository

    @Autowired
    lateinit var base62: Base62

    @Test
    fun `redirect to longUrl if link exists`() {
        val link = Link(longUrl = "https://youtube.com", title = "Youtube URL")
        linksRepository.save(link)
        link.tag = base62.encode(link?.id?.toBigInteger()!!)
        linksRepository.save(link)

        mockMvc.perform(get("/${link.tag}")).andExpect(redirectedUrl(link.longUrl))
    }

    @Test
    fun `returns 404 if link does not exist`() {
        mockMvc.perform(get("/invalid-tag")).andExpect(status().isNotFound())
    }
}
