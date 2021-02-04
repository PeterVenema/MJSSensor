package nl.meetjestad.sensor;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.HttpURLConnection;


public class ReadSensor {

    private String baseUrl = "https://meetjestad.net/data/?type=sensors&ids={ID}&format=json&limit=1";
    private URL url = null;

    public ReadSensor(String sensorID) {
        String requestUrl = baseUrl.replace("{ID}",sensorID);
        try {
            this.url = new URL(requestUrl);
        } catch (Exception e) {

        }
    }

    /*
     * Should be a separate class in the future. This is for testing.
     */
    public String getSensorReading() {
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            int bt = in.read();
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            //con.setRequestProperty("Content-Type", "application/json");
//
//                //if (responseCode == HttpURLConnection.HTTP_OK) { // success
//            con.setRequestProperty("User-Agent", "Mozilla/5.0");
//            int responseCode = con.getResponseCode();
//            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
            in.close();
            return "respond: " + bt;

        } catch(Exception e){
                return "";
        } finally {
            urlConnection.disconnect();
        }

    }

}
