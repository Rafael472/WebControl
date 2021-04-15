package com.SystemsSolutions.WebControl.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class SegurancaServices {
	
	public int Logon() {
		return Logon("", false);
	}
	
	public int Logon(String token, boolean loginToken) {
		return Logon("", false);
	}
	
	private Cookie getCookie(HttpServletRequest request, String string) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals(string))
	            return cookie;
	        }
	    }
	    return null;
	}
	
	public Cookie getUsuarioCookie(HttpServletRequest request) {
		System.out.println("Quantidade de cookies: " + request.getCookies().length + " -- SegurancaServices");
		Cookie cookie = getCookie(request, "userId");
	    if (cookie != null)
	        System.out.println("Achou o cookie -- SegurancaServices");
    	return cookie;
	}
}
