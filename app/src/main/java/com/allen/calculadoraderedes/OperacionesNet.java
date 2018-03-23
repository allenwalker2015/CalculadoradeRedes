package com.allen.calculadoraderedes;

import android.util.Log;
import java.lang.Math;

/**
 * Created by elect on 22/03/2018.
 */

public class OperacionesNet {

    public String[] generateIPs(String ip,String subnet){
        String[] res = new String[5];
        int  n_subnet = Integer.parseInt(subnet);
        String net_id="",broadcast="",n_mask="";
        String s_mask =new String(new char[n_subnet]).replace("\0", "1")
                + new String(new char[32-n_subnet]).replace("\0", "0");
        String s_mask2 =new String(new char[n_subnet]).replace("\0", "0")
                + new String(new char[32-n_subnet]).replace("\0", "1");
        String[] ip_octet = ip.split("\\.");
//        Log.d("s mask", "generateIPs: "+s_mask);
        for(int i = 0;i<4 ; i++ ){
            n_mask = n_mask
                    +  Integer.parseInt(s_mask.substring(i * 8,i*8+8),2) + ".";
            net_id = net_id
                    + new Integer(Integer.parseInt(ip_octet[i]) & Integer.parseInt(s_mask.substring(i * 8,i*8+8),2))
                    +  ".";
            broadcast = broadcast
                    +new Integer(Integer.parseInt(ip_octet[i]) | Integer.parseInt(s_mask2.substring(i * 8,i*8+8),2))
                    + ".";
            Log.d("OCTETOS", Integer.parseInt(ip_octet[i]) + "=" + Integer.parseInt(s_mask.substring(i * 8,i*8+8),2) +" generateIPs: "+net_id);
        }
        res[0] = net_id.substring(0,net_id.length()-1);
        res[1] = broadcast.substring(0,broadcast.length()-1);
        res[2] = String.format("%.0f",Math.pow(2,32-n_subnet));
        res[3] = String.format("%.0f",Math.pow(2,32-n_subnet)-2);
        res[4] = n_mask.substring(0,n_mask.length()-1);
//        Log.d("NETID", "generateIPs: "+netid);
//        Log.d("BROADCAST", "generateIPs: "+broadcast);
//        Log.d("TOTALIPS", "generateIPs: "+totalips);
//        Log.d("CLIENTIPS", "generateIPs: "+clientips);







//        Log.d("DEBUG_GENERATEIP", "generateIPs: "+s_mask);
//        Long b_subnet = Long.parseLong(s_mask,2);
//        Log.d("DEBUG_GENERATEIP", "generateIPs: "+b_subnet);
//        String[] ip_octet = ip.split("\\.");
//        String temp,s_bin = "";
//        for (String octet:ip_octet) {
//            temp = Integer.toBinaryString(Integer.parseInt(octet));
//            s_bin += new String(new char[8-temp.length()]).replace("\0", "0") + temp;
//        }
//        Long broadcast = Long.parseLong(s_mask,2)&  Long.parseLong(s_bin,2);
//        Log.d("S_BIN", "generateIPs: " + broadcast);

        return res;
    }
}
