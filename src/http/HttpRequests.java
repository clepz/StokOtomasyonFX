package http;

import fxml.Login;
import model.Urun;
import model.User;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class HttpRequests {

    private HttpURLConnection createRequest(String link, String httpMetodu){
        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL("http://localhost:8081" + link);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(httpMetodu);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return con;
    }

    public User girisYap(String username, String password){
        HttpURLConnection con = createRequest("/rol"+"?username="+username,"GET");
        Login.user = new User(username);
        Login.user.setEncoded(Base64.getEncoder().encodeToString((username+":"+password).getBytes()));
        con.setRequestProperty("Authorization","Basic " + Login.user.getEncoded());
        try {
            if(con.getResponseCode()== 401){
                Login.user.setRol("-1");
                return Login.user;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            Login.user.setRol(reader.readLine());
            con.disconnect();
        } catch (ConnectException e){
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Login.user;
    }


    public Urun urunAl(String barkod) {
        String params = "?barkod="+barkod;
        HttpURLConnection con = createRequest("/user/urun"+params,"GET");
        con.setRequestProperty("Authorization", "Basic "+Login.user.getEncoded());
        Urun urun = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String a = reader.readLine();
            JSONObject urunJson = new JSONObject(a);
                urun = new Urun(urunJson.getString("barkod"),urunJson.getString("marka"),urunJson.getString("model"),urunJson.getString("aciklama"),
                    urunJson.getString("seri_no"), BigDecimal.valueOf(urunJson.getDouble("fiyat")).floatValue(),urunJson.getInt("bundleVarMi"),urunJson.getString("bolum_no"));
        } catch (IOException | NullPointerException | JSONException e) {
            e.printStackTrace();
            return null;
        }
        return urun;
    }



    public String al(){
        HttpURLConnection con = createRequest("/deneme","GET");
        String encoding = Base64.getEncoder().encodeToString(("deneme:deneme").getBytes());
        con.setRequestProperty("Authorization","Basic " + encoding);
        BufferedReader in = null;
        String inputLine;
        StringBuffer content = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        con.disconnect();
        return content.toString();
    }

    public String postYap(){
        HttpURLConnection con = createRequest("/ekle","POST");
        String encoding = Base64.getEncoder().encodeToString(("deneme:deneme").getBytes());
        con.setRequestProperty("Authorization","Basic " + encoding);
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        String a = null;
        try {
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            JSONObject user = new JSONObject();
            user.put("username","tarikk");
            user.put("password", "12345");
            user.put("rol","ROLE_ADMIN");
            wr.write(user.toString().getBytes());
            wr.flush();
            if(con.getResponseCode() != HttpURLConnection.HTTP_OK)
                throw new RuntimeException("Http response code: "+con.getResponseCode());
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            a = in.readLine();
            wr.close();
            con.disconnect();
            JSONObject b = new JSONObject(a);
            System.out.println(b.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return a;
    }
}