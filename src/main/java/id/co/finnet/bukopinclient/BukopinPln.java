/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.finnet.bukopinclient;

import javax.annotation.PostConstruct;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.q2.iso.QMUX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author anggi
 */
@Service
public class BukopinPln {

    @Autowired
    private QMUX qmux ;

    private boolean isSignon = false;

    @PostConstruct
    public void sendSignOn() throws ISOException {
        ISOMsg signonRequest = new ISOMsg("2800");
        signonRequest.set(12, "20181214153300");
        signonRequest.set(33, "1234567");
        signonRequest.set(40, "001");
        signonRequest.set(41, "0000000000000001");
        signonRequest.set(48, "0000000");
        ISOMsg response = qmux.request(signonRequest, 10000);
        
        if (response != null && response.getString(39).equals("0000")) {
              isSignon = true;
        }
    }

    @Scheduled(fixedRate = 5000)
    private void sendEcho() throws ISOException {
        if (isSignon) {
            ISOMsg signonRequest = new ISOMsg("2800");
            signonRequest.set(12, "20181214153300");
            signonRequest.set(33, "1234567");
            signonRequest.set(40, "301");
            signonRequest.set(41, "0000000000000001");
            signonRequest.set(48, "0000000");
            ISOMsg response = qmux.request(signonRequest, 10000);
        }
    }

}
