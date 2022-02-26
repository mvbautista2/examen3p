/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.examen3p;

import ec.edu.espe.arqsoftware.examen3p.controller.PagoPrestamoController;
import ec.edu.espe.arqsoftware.examen3p.model.PagoPrestamo;
//import ec.edu.espe.arquitectura.kafka.service.MensajeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class KafkaListeners {

    private final PagoPrestamoController mensajeController;
    private RestTemplate restTemplate = new RestTemplate();

    public KafkaListeners(PagoPrestamoController mensajeController) {
        this.mensajeController = mensajeController;
    }

    @KafkaListener(
            topics = "pagos",
            groupId = "groupId"
    )
    public void Listener(PagoPrestamo p) {
        log.info("Data recibida sin procesar nada: {}", p);
        this.restTemplate.postForObject("http://localhost:8080/api/v1/pago", p, PagoPrestamo.class);
    }
}
