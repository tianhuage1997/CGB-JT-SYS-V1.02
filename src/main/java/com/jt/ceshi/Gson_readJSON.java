package com.jt.ceshi;

import java.net.URL;

public class Gson_readJSON {

    public static void main(String[] args) {
        String laststr="";

        URL l1 =
                Thread.currentThread().
                        getContextClassLoader()
                        .getResource("ceshi.json");
        String path = String.valueOf(l1);
         //去除file:/的前缀
        path =path.replace("file:/","");
        


    }

}
