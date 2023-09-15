package com.experiency.experiency.model

import java.time.LocalDate
import javax.persistence.*

@Entity
data class Reserva(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne
    val experiencia: Experiencia,
    @ManyToOne
    val cliente: Usuario,
    val fecha: LocalDate,
    val estado: String
)
