/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.finnet.bukopinclient;

import java.math.BigDecimal;
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
    private QMUX qmux;

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

//    @Scheduled(fixedRate = 5000)
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

    public ResponseDto sendInquiryPrepaid(String msn, BigDecimal amount) throws ISOException {
        ISOMsg prepaidInquiryRequest = new ISOMsg("2200");
        prepaidInquiryRequest.set(2, "99502");
        
        //harusnya stan dibuat generate increment
        prepaidInquiryRequest.set(11, "1234567");
        
//        bit 12 seharusnya ambil dari tanggal sekarang
        prepaidInquiryRequest.set(12, "20181214213600");
        prepaidInquiryRequest.set(26, "6010");
        prepaidInquiryRequest.set(32, "0000000");
        prepaidInquiryRequest.set(33, "0000000");
        prepaidInquiryRequest.set(41, "1111111111111111");
        
        StringBuilder bit48 = new StringBuilder();
        bit48.append("0000000");
        bit48.append(msn);
        bit48.append("000000000000");
        bit48.append("0");
        
        prepaidInquiryRequest.set(48,bit48.toString());
        
        ISOMsg response = qmux.request(prepaidInquiryRequest, 10000);
        
        ResponseDto responseDto = new ResponseDto();
        
        if(response == null){
            responseDto.setResponseCode("Tidak mendapatkan response sampai timeout");
        }else{
            responseDto.setResponseCode(response.getString(39));
        }
        
        return responseDto;
    }

}
