package dev.nicolaszein.linkinho.web.controllers

import dev.nicolaszein.linkinho.application.commands.AddLinkClickCommand
import dev.nicolaszein.linkinho.application.services.LinkService
import dev.nicolaszein.linkinho.domain.repositories.LinksRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.view.RedirectView
import javax.servlet.http.HttpServletResponse

@Controller
class LinkRedirectController(
    private val linksRepository: LinksRepository,
    private val linkService: LinkService
) {
    @GetMapping("/{tag}")
    fun redirectToLongUrl(
        @PathVariable tag: String,
        response: HttpServletResponse,
        @RequestHeader headers: Map<String, String>
    ): RedirectView {
        val link = linksRepository.findByTag(tag)
        if (link == null) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Oops! You might have clicked on an invalid link")
        }

        val command = AddLinkClickCommand(
            link = link,
            userAgent = headers.getOrDefault("user-agent", ""),
            ip = headers.getOrDefault("x-forward-for", "")
        )
        linkService.addLinkClick(command)

        val redirect = RedirectView(link.longUrl)
        redirect.setStatusCode(HttpStatus.PERMANENT_REDIRECT)

        return redirect
    }


}
