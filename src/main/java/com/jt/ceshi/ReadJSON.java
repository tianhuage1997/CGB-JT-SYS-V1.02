package com.jt.ceshi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadJSON {

    public static void main(String[] args) {

        String laststr="";

         URL l1 =
                 Thread.currentThread().
                         getContextClassLoader().
                         getResource("ceshi.json");

        String path=String.valueOf(l1);
        //去除file:/的前缀
        path = path.replace("file:/","");
        File file=new File(path);
        BufferedReader reader=null;
        try{
            FileInputStream in = new FileInputStream(file);
            reader=new BufferedReader(new InputStreamReader(in,"UTF-8"));// 读取文件
            String tempString = null ;
            while((tempString=reader.readLine())!=null){
                laststr=laststr+tempString;
            }

            System.out.println("laststr:"+laststr);

            ObjectMapper mapper = new ObjectMapper();
            //json字符串映射为map对象
            Map<String , Object>  map = new HashMap<String, Object>(16);

            //将字符串映射到对应的map
            map = mapper.readValue(laststr,map.getClass());
            //获取id
            String id = "employees";
            System.out.println("获取employees："+map.get(id));
            //嵌套的对象获取
            /*  这个办法会报错的。因为是arraylist类型
           Map base = (Map)map.get(id);
           String firstName = (String)base.get("firstName");
           String twoName=(String)base.get("twoName");
           String lastName=(String)base.get("lastName");
            System.out.println(firstName+"--------"+twoName+"---------"+lastName);

*/
            ArrayList<String> list = (ArrayList<String>)map.get(id);
            System.out.println(list.toString());
           // String firstName = list.size()
//            String twoName=String.valueOf(list.get(1));
//            String lastName=String.valueOf(list.get(3));
            System.out.println();
            Object[]  listMap=list.toArray();
            System.out.println("*******************************************");
            System.out.println(listMap.toString());
            System.out.println(listMap[0].toString());
//            System.out.println(listMap[1]);
//            System.out.println(listMap[2]);
//            System.out.println(listMap[3]);
            //Map base = (Map)map.get(id);
            System.out.println(1);
            System.out.println("=============================================");
            JsonNode jsonNode = mapper.readTree(laststr);
            JsonNode  firstName = jsonNode.get("employees").get("firstName").get(0);
            System.out.println("树形解析："+firstName.asText());
            System.out.println("**************************************");

            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(reader!=null){
                try{
                    reader.close();
                }catch(IOException el){
                }
            }
        }

    }

/*
{
    "employees": [
        {
            "firstName": "Bill",
            "twoName" : "zhuang",
            "threeName" : "jinye",
            "lastName": "Gates"
        }
    ]
}
 */

}
