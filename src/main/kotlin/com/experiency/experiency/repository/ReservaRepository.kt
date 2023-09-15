package com.experiency.experiency.repository

import com.experiency.experiency.model.Experiencia
import com.experiency.experiency.model.Reserva
import com.experiency.experiency.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface ReservaRepository: JpaRepository<Reserva, Long> {
    fun existsByExperienciaAndFechaAndClienteNot(experiencia: Experiencia, fecha: LocalDate, cliente: Usuario): Boolean
}