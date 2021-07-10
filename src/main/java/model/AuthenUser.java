/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import helper.SecurityHelper;

/**
 *
 * @author Ngan Do
 */
public class AuthenUser {
    private static final String SECRET_KEY = "ngandethuong";
    
    public static String genAuthenCookie(String username) {
        return username + "." + SecurityHelper.getMD5Hash(username + SECRET_KEY);
    }
    
    public static String getAuthenCookie(String cookie) {
        String[] arrCookie = cookie.split("\\.");
        if (arrCookie.length == 2) {
            String username = arrCookie[0];
            String hash = arrCookie[1];
            
            if (hash.equals(SecurityHelper.getMD5Hash(username + SECRET_KEY))) {
                return username;
            }
        }
        
        return "";
    }

}
