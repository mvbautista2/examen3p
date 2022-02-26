/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.examen3p.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@Table(name = "pago_prestamo")

public class PagoPrestamo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_prestamo", nullable = false)
    private Integer codigo;
    
    @Column(name = "numero_cuota", nullable = false)
    private Integer numeroCuota;
    
    @Column(name = "fecha_pago")
    private Date fechaPago;
    
    @Column(name = "hora_pago")
    private LocalTime horaPago;
        
    @Column(name = "valor_pago")
    private BigDecimal valorPago;

    

    
    
}