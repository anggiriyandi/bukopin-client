/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.finnet.bukopinclient.controller;

import id.co.finnet.bukopinclient.BukopinPln;
import id.co.finnet.bukopinclient.PrepaidRequestDto;
import id.co.finnet.bukopinclient.ResponseDto;
import java.math.BigDecimal;
import org.jpos.iso.ISOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author anggi
 */
@RestController
public class PlnBukopinController {
    
    @Autowired
    private BukopinPln bukopinPln;
    
    
    @PostMapping("/inquiry/prepaid")
    public ResponseDto sendInquiryPrepaid(@RequestBody PrepaidRequestDto request) throws ISOException{
        return bukopinPln.sendInquiryPrepaid(request.getMsn() , request.getAmount());
    }
    
    
}
