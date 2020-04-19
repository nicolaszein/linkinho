package dev.nicolaszein.linkinho.domain.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "links")
data class Link(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val longUrl: String,
    val title: String
) {
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "link")
    var clicks: MutableList<Click> = mutableListOf()

    @CreationTimestamp
    val createdAt: LocalDateTime? = null

    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null

    var tag: String? = null

    fun addClick(userAgent: String, ip: String) {
        this.clicks.add(Click(link = this, userAgent = userAgent, ip = ip))
    }
}
