package com.experiency.experiency.service

import com.experiency.experiency.model.Experiencia
import com.experiency.experiency.model.Reserva
import com.experiency.experiency.model.Usuario

class EspecificacionExperiencia {
    private var titulo: String? = null
    private var categoria: String? = null
    private var precioMin: Double? = null
    private var precioMax: Double? = null
    private var descripcion: String? = null
    private var duracion: Int? = null
    private var anfitrion: Usuario? = null
    private var reservas: List<Reserva> ? = emptyList()
    private var ubicacion: String? = null


    fun conTitulo(titulo: String?): EspecificacionExperiencia {
        this.titulo = titulo
        return this
    }

    fun conDescripcion(descripcion: String?): EspecificacionExperiencia {
        this.descripcion = descripcion
        return this
    }

    fun conDuracion(duracion: Int?): EspecificacionExperiencia {
        this.duracion = duracion
        return this
    }

    fun conAnfitrion(anfitrion: Usuario?): EspecificacionExperiencia {
        this.anfitrion = anfitrion
        return this
    }

    fun conReservas(reservas: List<Reserva>? ): EspecificacionExperiencia {
        this.reservas = reservas
        return this
    }

    fun conCategoria(categoria: String?): EspecificacionExperiencia {
        this.categoria = categoria
        return this
    }

    fun conPrecioMin(precioMin: Double?): EspecificacionExperiencia {
        this.precioMin = precioMin
        return this
    }

    fun conPrecioMax(precioMax: Double?): EspecificacionExperiencia {
        this.precioMax = precioMax
        return this
    }

    fun conUbicacion(ubicacion: String?): EspecificacionExperiencia {
        this.ubicacion = ubicacion
        return this
    }

    fun build(): Experiencia {
        return Experiencia(
            titulo = titulo ?: "",
            categoria = categoria ?: "",
            descripcion = descripcion ?: "",
            precio = precioMin ?: 0.0,  // Puedes definir un valor predeterminado aquí
            duracion = duracion ?: 0,
            anfitrion = anfitrion ?: Usuario(0,true),
            reservas = reservas ?: emptyList(),
            ubicacion = ubicacion ?: ""
        )
    }
}