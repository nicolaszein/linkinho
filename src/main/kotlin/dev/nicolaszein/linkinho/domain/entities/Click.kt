package dev.nicolaszein.linkinho.domain.entities

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "clicks")
data class Click(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "link_id")
    val link: Link,

    val userAgent: String,
    val ip: String
) {
    @CreationTimestamp
    val createdAt: LocalDateTime? = null
}
