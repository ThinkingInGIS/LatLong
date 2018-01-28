package org.nercita.latlong.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Service
public class LatLongService {
	
	public String getAddress(String longitude,String latitude) { 
        HttpURLConnection connection = null;  
        BufferedReader reader = null;  
        try {  
            URL getUrl = new URL("http://api.map.baidu.com/geocoder?output=json&location="+latitude+",%20"+longitude+"&key=xqKbMKkFxrhiICGeIurKMwxfT4QpPX3c");  
            connection= (HttpURLConnection) getUrl.openConnection();  
            connection.setConnectTimeout(5000);  
            connection.setReadTimeout(5000);  
            connection.connect();  
            reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));  
            StringBuilder builder = new StringBuilder();  
            String line;  
            while ((line =reader.readLine()) != null) {  
                builder.append(line);  
            }  
            
//            if (log.isDebugEnabled())  
//                log.debug(builder.toString());  
            //net.sf.json.JSONObject jsonObj =net.sf.json.JSONObject.fromObject(builder.toString());  
            //JSONArray newArray = JSONArray.fromObject("["+builder.toString()+"]");  
            //JSONObject obj = (net.sf.json.JSONObject)newArray.get(0);  
           //System.out.println("newArray:"+newArray);  
            //System.out.println("obj:"+obj);  
            //net.sf.json.JSONObjectresult = (net.sf.json.JSONObject)obj.get("result");  
            //net.sf.json.JSONObject addressComponent =(net.sf.json.JSONObject)result.get("addressComponent");  
            //String city =(String)addressComponent.get("city");  
            return builder.toString();  
             
        }catch(Exception e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                reader.close();  
            }catch(IOException e) {  
                e.printStackTrace();  
            }finally{  
                connection.disconnect();  
            }  
        }  
        return null;  
	}
	
    /**
     * 根据地址获取城市下的经纬度  
     * @param address
     * @param city
     * @return
     */
    public String getLatLong(String address,String city) {  
        HttpURLConnection connection= null;  
        BufferedReader reader = null;  
        try {  
            URL getUrl = new URL("http://api.map.baidu.com/geocoder?address="+address+"&output=json&key=xqKbMKkFxrhiICGeIurKMwxfT4QpPX3c&city="+city);  
            connection =(HttpURLConnection) getUrl.openConnection();  
            connection.setConnectTimeout(5000);  
            connection.setReadTimeout(5000);  
            connection.connect();  
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));  
            StringBuilder builder = new StringBuilder();  
            String line;  
            while ((line = reader.readLine()) != null) {  
                builder.append(line);  
            } 
            return builder.toString();
            
//            if (log.isDebugEnabled())  
//                log.debug(builder.toString());  
            //net.sf.json.JSONObjectjsonObj = net.sf.json.JSONObject.fromObject(builder.toString());  
//            net.sf.json.JSONArray  newArray =net.sf.json.JSONArray.fromObject("["+builder.toString()+"]");  
//            net.sf.json.JSONObjectobj = (net.sf.json.JSONObject)newArray.get(0);  
//            System.out.println("newArray:"+newArray);  
//            System.out.println("obj:"+obj);  
//            net.sf.json.JSONObjectresult = (net.sf.json.JSONObject)obj.get("result");  
//            net.sf.json.JSONObjectlocation = (net.sf.json.JSONObject)result.get("location");  
//            Double Lon =(Double)location.get("lng");  
//            Double Lat =(Double)location.get("lat");  
//            System.out.println("result:"+result);  
//            System.out.println("location:"+location);  
//            System.out.println("Lon:"+ Lon);  
//            System.out.println("Lat:"+Lat);  
//            return Lon + "," + Lat;  
             
        }catch(Exception e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                reader.close();  
            }catch(IOException e) {  
                e.printStackTrace();  
            }finally{  
                connection.disconnect();  
            }  
        }  
        return null;  
    } 

}
