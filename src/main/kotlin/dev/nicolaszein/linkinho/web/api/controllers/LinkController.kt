package dev.nicolaszein.linkinho.web.api.controllers

import dev.nicolaszein.linkinho.application.commands.CreateLinkCommand
import dev.nicolaszein.linkinho.application.services.LinkService
import dev.nicolaszein.linkinho.web.api.controllers.responses.LinkResponse
import dev.nicolaszein.linkinho.web.api.controllers.responses.toResponse
import dev.nicolaszein.linkinho.web.api.controllers.validators.CreateLink
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class LinkController(
    private val linkService: LinkService
) {
    @PostMapping("/links")
    fun createLink(
        @Valid @RequestBody requestBody: CreateLink,
        request: HttpServletRequest
    ): ResponseEntity<LinkResponse> {
        val command = CreateLinkCommand(longUrl = requestBody.longUrl, title = requestBody.title)

        val link = linkService.create(command)

        return ResponseEntity(link.toResponse(host = getHost(request)), HttpStatus.CREATED)
    }

    private fun getHost(request: HttpServletRequest): String {
        val currentUrl = request.requestURL.toString()
        val currentUri = request.requestURI.toString()
        return currentUrl.replace(currentUri, "")
    }
}
