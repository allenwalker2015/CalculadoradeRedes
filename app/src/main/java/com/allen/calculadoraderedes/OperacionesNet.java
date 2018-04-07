package com.allen.calculadoraderedes;

import android.util.Log;
import java.lang.Math;

/**
 * Created by elect on 22/03/2018.
 */

public class OperacionesNet {

    //Devuelve un arreglo de tipo string de 5 posiciones con netid,broadcast,totalips, cantidad de hosts y la mascara en forma 255.255... etc
    public String[] generateIPs(String ip,String subnet){
        String[] res = new String[7];
        int  n_subnet = Integer.parseInt(subnet); // cantidad de bits de la mascara
        String net_id="",broadcast="",n_mask=""; // en estas string se iran almacenando los numeros generados y concatenandolos
        String s_mask =new String(new char[n_subnet]).replace("\0", "1")
                + new String(new char[32-n_subnet]).replace("\0", "0"); // Se genera la mascara en binario porque puede que no sea multiplo de 8
        String s_mask2 =new String(new char[n_subnet]).replace("\0", "0") // Se genera el inverso de la mascara para encontrar el broadcast
                + new String(new char[32-n_subnet]).replace("\0", "1");
        String[] ip_octet = ip.split("\\."); // Se genera un arreglo de strings de tamaño 4, y se separan por el punto entre los numeros
        //Se realiza el calculo de los 4 octetos para el netid y el broadcast y de paso se genera la mascara
        for(int i = 0;i<4 ; i++ ){

            n_mask = n_mask
                    +  Integer.parseInt(s_mask.substring(i * 8,i*8+8),2) + "."; // En cada iteracion se convierte a decimal cada octeto de la mascara

            net_id = net_id
                    + new Integer(Integer.parseInt(ip_octet[i]) & Integer.parseInt(s_mask.substring(i * 8,i*8+8),2))
                    +  "."; //Se genera cada octeto del netid

            broadcast = broadcast
                    +new Integer(Integer.parseInt(ip_octet[i]) | Integer.parseInt(s_mask2.substring(i * 8,i*8+8),2))
                    + "."; //Se genera cada octeto del broadcast

           // Log.d("OCTETOS", Integer.parseInt(ip_octet[i]) + "=" + Integer.parseInt(s_mask.substring(i * 8,i*8+8),2) +" generateIPs: "+net_id);
        }
        res[0] = net_id.substring(0,net_id.length()-1); // Se elimina un punto  adicional que queda en las cadenas
        res[1] = broadcast.substring(0,broadcast.length()-1);
        res[2] = String.format("%.0f",Math.pow(2,32-n_subnet)); // Se calcula la cantidad de ips
        res[3] = String.format("%.0f",Math.pow(2,32-n_subnet)-2);// Se calcula la cantidad de host
        res[4] = n_mask.substring(0,n_mask.length()-1);
        res[5] = ""+ (32 - n_subnet);
        res[6] = ""+ n_subnet;
        return res;
    }
}
