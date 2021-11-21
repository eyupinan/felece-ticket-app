package org.felecechallenge.ticket.service.impl;

import org.felecechallenge.ticket.service.UrlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class UrlServiceImpl implements UrlService {
    @Value("${server.port:-1}")
    private  int port;
    @Value("${server.host:localhost}")
    private  String host;
    @Value("${server.ssl.enabled:false}")
    private  boolean isSSL;

    public  String getUrl(String path){
        URL url;
        String protocol="https";
        /*if (isSSL==true){
            protocol="https";
        }*/
        try{
            if (port!=-1){
                url = new URL(protocol,host,path);
            }
            else{
                url = new URL(protocol,host,path);
            }
            System.out.println(url.toString());
            return url.toString();
        }
        catch(Exception e){
            return "";
        }
    }
    public  String getUrl(){
        URL url;
        String protocol="http";
        if (isSSL==true){
            protocol="https";
        }
        try{
            url = new URL(protocol,host,port,"");
            return url.toString();
        }
        catch(Exception e){
            return "";
        }
    }
}
