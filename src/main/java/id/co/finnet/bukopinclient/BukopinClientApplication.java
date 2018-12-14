package id.co.finnet.bukopinclient;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.q2.Q2;
import org.jpos.q2.iso.QMUX;
import org.jpos.util.NameRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BukopinClientApplication {

    public static void main(String[] args) throws NameRegistrar.NotFoundException, InterruptedException, ISOException {

        Q2 q2 = new Q2();
        q2.start();
        SpringApplication.run(BukopinClientApplication.class, args);
    }

    @Bean
    public QMUX qmux() throws NameRegistrar.NotFoundException {
        return NameRegistrar.get("mux.bukopin");
    }

}
