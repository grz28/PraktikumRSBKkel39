/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.session;

import java.text.DecimalFormat;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Notebook
 */
@Stateless
@LocalBean
public class konversitekanan {
    
    DecimalFormat a = new DecimalFormat("0.0000");
    String result;
    
    public String atm_bar(double param) {
        return result =a.format(param*1.01325);
    }
    public String atm_mpa(double param) {
        return result =a.format(param*0.10132);
    }
    public String atm_psi(double param) {
        return result =a.format(param*14.696);
    }
    public String bar_atm(double param) {
        return result =a.format(param*0.98692);
    }
    
}
