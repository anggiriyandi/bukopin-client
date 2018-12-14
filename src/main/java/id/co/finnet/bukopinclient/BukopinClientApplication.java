package id.co.finnet.bukopinclient;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.q2.Q2;
import org.jpos.q2.iso.QMUX;
import org.jpos.util.NameRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BukopinClientApplication {

	public static void main(String[] args) throws NameRegistrar.NotFoundException, InterruptedException, ISOException {
                
                Q2 q2 = new Q2();
                q2.start();
                
                Thread.sleep(5 * 1000);
                
                QMUX sender = (QMUX) NameRegistrar.get("mux.bukopin");
                
                ISOMsg signonRequest = new ISOMsg("0800");
                signonRequest.set(12, "20181214153300");
                signonRequest.set(33, "1234567");
                signonRequest.set(40, "001");
                signonRequest.set(41, "0000000000000001");
                signonRequest.set(48, "0000000");
                
                ISOMsg response = sender.request(signonRequest, 10000);
                
                if(response == null){
                    System.out.println("ERROR SIGNON : TIDAK MENDAPAT RESPONSE SAMPAI TIMEOUT");
                }
                
		SpringApplication.run(BukopinClientApplication.class, args);
	}

}

