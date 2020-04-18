package dev.nicolaszein.linkinho.web.controllers.validators

import dev.nicolaszein.linkinho.application.commands.CreateLinkCommand
import org.hibernate.validator.constraints.URL
import javax.validation.constraints.NotEmpty

class CreateLink {
    @field:URL
    @field:NotEmpty
    val longUrl: String = ""

    @field:NotEmpty
    val title: String = ""

    fun toCommand() = CreateLinkCommand(longUrl = this.longUrl, title = this.title)
}
