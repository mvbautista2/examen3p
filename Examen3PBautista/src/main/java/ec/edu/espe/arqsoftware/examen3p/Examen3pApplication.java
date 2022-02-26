package ec.edu.espe.arqsoftware.examen3p;

import com.github.javafaker.Faker;
import ec.edu.espe.arqsoftware.examen3p.model.PagoPrestamo;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

@SpringBootApplication
@Slf4j
public class Examen3pApplication {

	public static void main(String[] args) {
		SpringApplication.run(Examen3pApplication.class, args);
	}
         @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, Object> kafkaTemplate) {
        return args -> {
            Faker faker = new Faker();
            PagoPrestamo pago = new PagoPrestamo();
            for (int i = 0; i < 10000; i++) {
              
                pago.setCodigo(faker.numerify("#######").length());
                pago.setFechaPago(faker.date().past(300, TimeUnit.DAYS));
                pago.setNumeroCuota(faker.number().randomDigit());
                pago.setValorPago(new BigDecimal(faker.number().randomDouble(2, 1, 2)));
                pago.setHoraPago(LocalTime.now());
                log.info("Pago Prestamo: {}", pago);
                ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send("pagos", pago);
                send.addCallback(new KafkaSendCallback<String, Object>() {
                    @Override
                    public void onSuccess(SendResult<String, Object> result) {
                        log.info("Mensaje enviado: {}",result.getRecordMetadata());
                    }
                    
                    @Override
                    public void onFailure(Throwable ex) {
                        log.error("Error al enviar el mensaje {}",ex);
                    }
                    
                    @Override
                    public void onFailure(KafkaProducerException ex) {
                         log.error("Error al enviar el mensaje {}",ex); //To change body of generated methods, choose Tools | Templates.
                    }
                    
                });
            }
        };
    }

}
