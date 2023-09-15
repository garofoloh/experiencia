package com.experiency.experiency.repository

import com.experiency.experiency.model.Experiencia
import org.springframework.data.jpa.repository.JpaRepository

interface ExperienciaRepository: JpaRepository<Experiencia, Long> {
}