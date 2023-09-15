package com.experiency.experiency.repository

import com.experiency.experiency.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long> {
    fun existsByIdAndActivo(id: Long, b: Boolean): Boolean
}