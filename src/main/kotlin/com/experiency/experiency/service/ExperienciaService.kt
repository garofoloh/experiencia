package com.experiency.experiency.service

import com.experiency.experiency.model.Experiencia
import com.experiency.experiency.model.Reserva
import com.experiency.experiency.model.Usuario
import com.experiency.experiency.repository.ExperienciaRepository
import com.experiency.experiency.repository.ReservaRepository
import com.experiency.experiency.repository.UsuarioRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ExperienciaService(
    private val experienciaRepository: ExperienciaRepository,
    private val reservaRepository: ReservaRepository,
    private val usuarioRepository: UsuarioRepository
) {

    // Método para crear una nueva experiencia
    fun crearExperiencia(experiencia: Experiencia): Experiencia {
        // Validar que el anfitrión sea un usuario registrado y activo
        if (experiencia.anfitrion.id == null || usuarioRepository.existsByIdAndActivo(experiencia.anfitrion.id, true) != null) {
            throw Exception("El anfitrión debe ser un usuario registrado y activo")
        }
        // Guardar la experiencia en la base de datos
        return experienciaRepository.save(experiencia)
    }

    // Método para buscar experiencias por varios criterios
    fun buscarExperiencias(
        titulo: String?,
        categoria: String?,
        precioMin: Double?,
        precioMax: Double?,
        ubicacion: String?
    ): List<Experiencia> {
        // Crear un objeto de especificación para filtrar las experiencias según los criterios recibidos
        val especificacion = EspecificacionExperiencia()
            .conTitulo(titulo)
            .conCategoria(categoria)
            .conPrecioMin(precioMin)
            .conPrecioMax(precioMax)
            .conUbicacion(ubicacion)
        // Devolver las experiencias que cumplan con la especificación
        return experienciaRepository.findAll()
    }

    // Método para obtener el detalle de una experiencia por su id
    fun obtenerExperienciaPorId(id: Long): Experiencia {
        // Buscar la experiencia por su id
        return experienciaRepository.findById(id)
            .orElseThrow { Exception("No se encontró ninguna experiencia con el id $id") }
    }

    // Método para reservar una experiencia por su id y la fecha
    fun reservarExperienciaPorId(id: Long, reserva: Reserva): Reserva {
        // Buscar la experiencia por su id
        val experiencia = obtenerExperienciaPorId(id)
        // Validar que el cliente sea un usuario registrado y activo
        if (reserva.cliente.id == null || !usuarioRepository.existsByIdAndActivo(reserva.cliente.id, true)) {
            throw Exception("El cliente debe ser un usuario registrado y activo")
        }
        // Validar que el cliente no sea el mismo que el anfitrión
        if (reserva.cliente.id == experiencia.anfitrion.id) {
            throw Exception("No se puede reservar una experiencia propia")
        }
        // Validar que la fecha de la reserva sea posterior a la fecha actual
        if (reserva.fecha.isBefore(LocalDate.now())) {
            throw Exception("No se puede reservar una experiencia en una fecha pasada")
        }
        // Validar que la experiencia no esté reservada para la misma fecha por otro cliente
        if (reservaRepository.existsByExperienciaAndFechaAndClienteNot(experiencia, reserva.fecha, reserva.cliente)) {
            throw Exception("La experiencia ya está reservada para esa fecha por otro cliente")
        }
        // Crear la reserva con el estado pendiente de pago
        val reservaCreada = reserva.copy(
            experiencia = experiencia,
            estado = "PENDIENTE"
        )
        // Guardar la reserva en la base de datos
        return reservaRepository.save(reservaCreada)
    }

    // Método para pagar una reserva por su id
    fun pagarReservaPorId(id: Long): Reserva {
        // Buscar la reserva por su id
        val reserva = reservaRepository.findById(id)
            .orElseThrow { Exception("No se encontró ninguna reserva con el id $id") }
        // Validar que la reserva esté pendiente de pago
        if (reserva.estado != "PENDIENTE") {
            throw Exception("La reserva no está pendiente de pago")
        }
        // Simular el pago de la reserva (aquí se podría integrar con un servicio externo de pago)
        val pagoRealizado = true
        // Actualizar el estado de la reserva según el resultado del pago
        val reservaActualizada = if (pagoRealizado) {
            reserva.copy(estado = "PAGADA")
        } else {
            reserva.copy(estado = "RECHAZADA")
        }
        // Guardar la reserva en la base de datos
        return reservaRepository.save(reservaActualizada)
    }
}