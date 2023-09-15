package com.experiency.experiency.model

import javax.persistence.*

@Entity
data class Experiencia(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val titulo: String,
    val descripcion: String,
    val categoria: String,
    val precio: Double,
    val duracion: Int,
    val ubicacion: String,
    @ManyToOne
    val anfitrion: Usuario,
    @OneToMany(mappedBy = "experiencia")
    val reservas: List<Reserva> = emptyList()
)
