package prosefa.util;

import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CodigoSeloGenerator {
    private final AtomicInteger sequencia = new AtomicInteger(1);
    public String gerarCodigo(){
        int numero = sequencia.getAndIncrement();
        return String.format("PROSEFA-%d-%06d", Year.now().getValue(), numero);
    }

}
