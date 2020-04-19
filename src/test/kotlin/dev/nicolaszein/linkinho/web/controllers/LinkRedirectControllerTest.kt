package dev.nicolaszein.linkinho.web.controllers

import dev.nicolaszein.linkinho.helpers.LinkFactory
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
    lateinit var linkFactory: LinkFactory

    @Test
    fun `redirect to longUrl if link exists`() {
        val link = linkFactory.create()

        mockMvc.perform(get("/${link.tag}")).andExpect(redirectedUrl(link.longUrl))
    }

    @Test
    fun `returns 404 if link does not exist`() {
        mockMvc.perform(get("/invalid-tag")).andExpect(status().isNotFound)
    }
}
