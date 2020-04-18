package dev.nicolaszein.linkinho.web.api.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

data class CreateLinkBody(
    val longUrl: String?,
    val title: String?
)

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class LinkControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @Test
    fun `returns bad request if missing attributes`() {
        val linkBody = CreateLinkBody(longUrl = null, title = null)
        val params = mapper.writeValueAsString(linkBody)

        mockMvc.perform(
            post("/api/links").contentType(MediaType.APPLICATION_JSON).content(params)
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun `returns bad request if missing longUrl`() {
        val linkBody = CreateLinkBody(longUrl = null, title = "A good title")
        val params = mapper.writeValueAsString(linkBody)

        mockMvc.perform(
            post("/api/links").contentType(MediaType.APPLICATION_JSON).content(params)
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun `returns bad request if longUrl is not a valid url`() {
        val linkBody = CreateLinkBody(longUrl = "not_valid", title = "A title")
        val params = mapper.writeValueAsString(linkBody)

        mockMvc.perform(
            post("/api/links").contentType(MediaType.APPLICATION_JSON).content(params)
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun `returns bad request if missing title`() {
        val linkBody = CreateLinkBody(longUrl = "https://long.url", title = null)
        val params = mapper.writeValueAsString(linkBody)

        mockMvc.perform(
            post("/api/links").contentType(MediaType.APPLICATION_JSON).content(params)
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun `returns created if valid body`() {
        val linkBody = CreateLinkBody(longUrl = "https://long.url", title = "A title")
        val params = mapper.writeValueAsString(linkBody)

        mockMvc.perform(
            post("/api/links").contentType(MediaType.APPLICATION_JSON).content(params)
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.longUrl").value("https://long.url"))
            .andExpect(jsonPath("$.title").value("A title"))
            .andExpect(jsonPath("$.tag").isString())
            .andExpect(jsonPath("$.shortUrl").isString())
            .andExpect(jsonPath("$.createdAt").isString())
            .andExpect(jsonPath("$.updatedAt").isString())
    }
}
