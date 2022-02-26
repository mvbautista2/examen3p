/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.examen3p.service;

import ec.edu.espe.arqsoftware.examen3p.model.PagoPrestamo;
import org.springframework.stereotype.Service;
import ec.edu.espe.arqsoftware.examen3p.dao.PagoPrestamoRepository;


@Service
public class PagoPrestamoService {

     private final PagoPrestamoRepository pagoPrestamoRepository;

    public PagoPrestamoService(PagoPrestamoRepository pagoPrestamoRepository) {
        this.pagoPrestamoRepository = pagoPrestamoRepository;
    }

    public void guardarPago(PagoPrestamo p) {
        this.pagoPrestamoRepository.save(p);
    }
}
