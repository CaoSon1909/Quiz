/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ACER
 */
public class DateHelper implements Serializable {

    static final String PATTERN = "dd/MM/yyyy";
    public static String formatDateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(PATTERN);
        return format.format(date);
    }
    public static Date formatStringToDate(String stringDate) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat(PATTERN);
        return format.parse(stringDate);
    }
}
