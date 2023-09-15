package com.experiency.experiency.controller

import com.experiency.experiency.model.Experiencia
import com.experiency.experiency.model.Reserva
import com.experiency.experiency.service.ExperienciaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/experiencias")
class ExperienciaController(
    private val experienciaService: ExperienciaService
) {

    // Método para crear una nueva experiencia
    @PostMapping("/crear")
    fun crearExperiencia(@RequestBody experiencia: Experiencia): ResponseEntity<Experiencia> {
        return ResponseEntity.ok(experienciaService.crearExperiencia(experiencia))
    }

    // Método para buscar experiencias por varios criterios
    @GetMapping("/buscar")
    fun buscarExperiencias(
        @RequestParam(required = false) titulo: String?,
        @RequestParam(required = false) categoria: String?,
        @RequestParam(required = false) precioMin: Double?,
        @RequestParam(required = false) precioMax: Double?,
        @RequestParam(required = false) ubicacion: String?
    ): ResponseEntity<List<Experiencia>> {
        return ResponseEntity.ok(experienciaService.buscarExperiencias(titulo, categoria, precioMin, precioMax, ubicacion))
    }

    // Método para obtener el detalle de una experiencia por su id
    @GetMapping("/{id}")
    fun obtenerExperienciaPorId(@PathVariable id: Long): ResponseEntity<Experiencia> {
        return ResponseEntity.ok(experienciaService.obtenerExperienciaPorId(id))
    }

    // Método para reservar una experiencia por su id y la fecha
    @PostMapping("/{id}/reservar")
    fun reservarExperienciaPorId(@PathVariable id: Long, @RequestBody reserva: Reserva): ResponseEntity<Reserva> {
        return ResponseEntity.ok(experienciaService.reservarExperienciaPorId(id, reserva))
    }

    // Método para pagar una reserva por su id
    @PostMapping("/pagar/{id}")
    fun pagarReservaPorId(@PathVariable id: Long): ResponseEntity<Reserva> {
        return ResponseEntity.ok(experienciaService.pagarReservaPorId(id))
    }
}