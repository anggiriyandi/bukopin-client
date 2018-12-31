/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.finnet.bukopinclient;

import java.math.BigDecimal;

/**
 *
 * @author anggi
 */
public class PrepaidRequestDto {
    private String msn;
    private BigDecimal amount;

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    
}
