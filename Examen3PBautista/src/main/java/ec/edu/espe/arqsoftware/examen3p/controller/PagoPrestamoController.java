/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.examen3p.controller;

import ec.edu.espe.arqsoftware.examen3p.model.PagoPrestamo;
import ec.edu.espe.arqsoftware.examen3p.service.PagoPrestamoService;
//import ec.edu.espe.arquitectura.kafka.service.MensajeService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/")
public class PagoPrestamoController {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final PagoPrestamoService pagoPrestamoService;

    public PagoPrestamoController(KafkaTemplate<String, Object> kafkaTemplate, PagoPrestamoService pagoPrestamoService) {
        this.kafkaTemplate = kafkaTemplate;
        this.pagoPrestamoService = pagoPrestamoService;
    }

    @PostMapping
    public void publish(@RequestBody PagoPrestamo p) {
        kafkaTemplate.send("pagos", p);
    }

    @PostMapping("pago")
    public void enviarMensaje(@RequestBody PagoPrestamo p) {
        this.pagoPrestamoService.guardarPago(p);
    }
}
