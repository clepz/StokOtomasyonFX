package http;

import fxml.Login;
import model.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;

import java.io.*;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class HttpRequests {

    private HttpURLConnection createRequest(String link, String httpMetodu){
        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL("http://localhost:8081" + link);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(httpMetodu);
            con.setRequestProperty("Authorization", "Basic "+ Login.user.getEncoded());
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Accept", "application/json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return con;
    }

    public User girisYap(String username, String password){
        Login.user = new User(username);
        Login.user.setEncoded(Base64.getEncoder().encodeToString((username+":"+password).getBytes()));
        HttpURLConnection con = createRequest("/rol"+"?username="+username,"GET");

        try {
            if(con.getResponseCode()== 401){
                Login.user.setRol("-1");
                return Login.user;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String[] a = new String[2];
            a = reader.readLine().split(",");
            Login.user.setRol(a[0]);
            Login.user.setKullaniciId(Integer.parseInt(a[1]));
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
        Urun urun = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String a = reader.readLine();
            JSONObject urunJson = new JSONObject(a);
                urun = new Urun(urunJson.getString("barkod"),urunJson.getString("marka"),urunJson.getString("model"),urunJson.getString("aciklama"),
                    urunJson.get("seri_no").toString(), BigDecimal.valueOf(urunJson.getDouble("fiyat")).floatValue(),urunJson.getInt("bundleVarMi"),urunJson.getString("bolum_no"),urunJson.getInt("adet"));
        } catch (IOException | NullPointerException | JSONException e) {
            e.printStackTrace();
            return null;
        }
        return urun;
    }

    public boolean kullaniciEkle(int kullanici_id, String username, String password, String rol){

        HttpURLConnection con = createRequest("/admin/ekle", "POST");

        String a = null;
        con.setDoOutput(true);

        try {
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            JSONObject user = new JSONObject();
            user.put("username",username);
            user.put("password",password);
            if(rol.equals("Admin"))
                user.put("rol","ROLE_ADMIN");
            else if(rol.equals("Satıcı"))
                user.put("rol","ROLE_USER");
            else if(rol.equals("Kasiyer"))
                user.put("rol","ROLE_KASIYER");
            user.put("kullaniciId",kullanici_id);
            wr.write(user.toString().getBytes());
            wr.flush();
            if(con.getResponseCode() != HttpURLConnection.HTTP_OK)
                throw new RuntimeException("Http response code: "+con.getResponseCode());
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            a = in.readLine();
            wr.close();
            con.disconnect();
            return Integer.valueOf(a) == 1;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return a;
    }

    public boolean kullaniGuncelle(Integer id, String password, String yetki) {
        HttpURLConnection con = createRequest("/admin/guncelle", "POST");
        con.setDoOutput(true);
        try {
            DataOutputStream outputStream = new DataOutputStream(con.getOutputStream());
            JSONObject user = new JSONObject();
            user.put("kullaniciId",id);
            user.put("password",password);
            user.put("rol",yetkiDuzenle(yetki));
            outputStream.write(user.toString().getBytes());
            outputStream.flush();
            if(con.getResponseCode() != HttpURLConnection.HTTP_OK)
                throw new RuntimeException("Http response code: "+con.getResponseCode());
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String a = in.readLine();
            con.disconnect();
            if(a.equals("hatalı id"))
                return false;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean kullaniciSil(Integer id) {

        HttpURLConnection connection = createRequest("/admin/sil"+"?id="+id,"GET");

        try {
            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK)
                return false;
             BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
             String a = in.readLine();
             connection.disconnect();;
             if(a.equals("başarısız"))
                 return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    private String yetkiDuzenle(String yetki){
        if ((yetki.equals("Admin")))
            return "ROLE_ADMIN";
        else if(yetki.equals("Satıcı"))
            return "ROLE_USER";
        return "ROLE_KASIYER";
    }


    public List<Urun> tumUrunleriAl() {
        HttpURLConnection connection = createRequest("/user/tumurunal", "GET");
        List<Urun> urunler = new ArrayList<>();
        String line;
        try {
            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK)
                return urunler;
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            if((line = in.readLine()) == null)
                return urunler;
            JSONArray urunJsonArray = new JSONArray(line);
            for(int i = 0 ; !urunJsonArray.isNull(i); i++) {
                JSONObject urunJson = urunJsonArray.getJSONObject(i);
                Urun urun = new Urun(urunJson.getString("barkod"), urunJson.getString("marka"), urunJson.getString("model"), urunJson.getString("aciklama"),
                        urunJson.get("seri_no").toString(), BigDecimal.valueOf(urunJson.getDouble("fiyat")).floatValue(), urunJson.getInt("bundleVarMi"), urunJson.getString("bolum_no"),urunJson.getInt("adet"));
                urunler.add(urun);
            }

            connection.disconnect();
            return urunler;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<? extends String> bolumNoAl() {
        HttpURLConnection con = createRequest("/user/tumbolumnoal","GET");
        Collection<String> bolumler = new ArrayList<>();
        String line;
        try {
            if(con.getResponseCode() != HttpURLConnection.HTTP_OK)
                return bolumler;
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            if((line = in.readLine()) == null)
                return bolumler;
            JSONArray urunJsonArray = new JSONArray(line);
            for(int i = 0 ; !urunJsonArray.isNull(i); i++) {
                String bolum = urunJsonArray.getString(i);
                bolumler.add(bolum);
            }
            con.disconnect();
            return bolumler;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bolumler;
    }

    public void urunSat(String barkod, int miktar, Musteri musteri) {
        HttpURLConnection con = createRequest("/user/urunsat", "POST");
        JSONObject object = new JSONObject();
        con.setDoOutput(true);
        try {
            object.put("barkod",barkod);
            object.put("satilanAdet",miktar);
            object.put("kullaniciId",Login.user.getKullaniciId());
            JSONObject object2 = new JSONObject();
            object2.put("ad", musteri.getAd());
            object2.put("soyad", musteri.getSoyad());
            object2.put("tc", musteri.getTc());
            object2.put("adres", musteri.getAdres());
            object2.put("cinsiyet", musteri.getCinsiyet());
            object2.put("telefon", musteri.getTelefon());
            object2.put("ev_telefon", musteri.getEvTelefon());
            object2.put("musteri_notu", musteri.getMusteriNotu());
            object.put("musteri", object2);

            DataOutputStream stream = new DataOutputStream(con.getOutputStream());
            stream.write(object.toString().getBytes());
            stream.flush();
            System.out.println(object.toString());
            if(con.getResponseCode() != HttpURLConnection.HTTP_OK)
                return;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int urunEkle(Urun urun) {
        HttpURLConnection con = createRequest("/admin/urunekle","POST");
        con.setDoOutput(true);
        JSONObject object = new JSONObject();
        try {
            object.put("barkod", urun.getBarkod());
            object.put("marka", urun.getMarka());
            object.put("model", urun.getModel());
            object.put("aciklama", urun.getAciklama());
            object.put("seri_no", urun.getSeri_no());
            object.put("fiyat", urun.getFiyat());
            object.put("bundleVarMi", urun.getBundleVarMi());
            object.put("bolum_no", urun.getBolum_no());
            object.put("adet", urun.getAdet());
            DataOutputStream stream = new DataOutputStream(con.getOutputStream());
            stream.write(object.toString().getBytes());
            stream.flush();
            if(con.getResponseCode() != HttpURLConnection.HTTP_OK){
                return -1;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 1;
    }

    // TODO: 17.12.2018 DÜZELT
    public Collection<Musteri> kasiyerMusteriAl() {

        HttpURLConnection con = createRequest("/kasiyer/musterileriAl","GET");
        List<Musteri> liste = new ArrayList<>();
        try {
            if(con.getResponseCode() != HttpURLConnection.HTTP_OK)
                return null;
            BufferedReader read = new BufferedReader(new InputStreamReader(con.getInputStream()));
            JSONArray array = new JSONArray(read.readLine());
            for(int i = 0 ; !array.isNull(i) ; i++){
                Musteri musteri = new Musteri();
                musteri.setMusteriNotu(array.getJSONObject(i).getString("musteri_notu"));
                musteri.setAdres(array.getJSONObject(i).getString("adres"));
                musteri.setAd(array.getJSONObject(i).getString("ad"));
                musteri.setSoyad(array.getJSONObject(i).getString("soyad"));
                musteri.setCinsiyet(array.getJSONObject(i).getString("cinsiyet"));
                if(array.getJSONObject(i).get("ev_telefonu").getClass() == String.class)
                    musteri.setEvTelefon(array.getJSONObject(i).getString("ev_telefonu"));
                musteri.setTc(array.getJSONObject(i).getString("tc"));
                musteri.setTelefon(array.getJSONObject(i).getString("telefon"));
                liste.add(musteri);
            }
            return liste;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("json olarak alamıyoruz");
        }catch (NullPointerException e){
            return null;
        }
        return null;
    }

    public KasiyerEkranıBilgiler kasiyerEkranSatisBilgiAl(String tc) {
        KasiyerEkranıBilgiler bilgiler = new KasiyerEkranıBilgiler();
        HttpURLConnection con = createRequest("/kasiyer/urunsatistamamla?tc=" + tc, "GET");
        List<KasiyerTablo> kasiyerTabloList = new ArrayList<>();
        List<Fatura> fatura = new ArrayList<>();
        try {
            if(con.getResponseCode() != HttpURLConnection.HTTP_OK){
                return null;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String a = reader.readLine();
            JSONObject object = new JSONObject(a);
            System.out.println(object.toString());
            JSONArray arrayKasiyer = object.getJSONArray("kasiyerList");
            for(int i = 0 ; !arrayKasiyer.isNull(i); i++) {
                KasiyerTablo tablo = new KasiyerTablo(arrayKasiyer.getJSONObject(i).getString("urun"),arrayKasiyer.getJSONObject(i).getInt("miktar"),
                        BigDecimal.valueOf(arrayKasiyer.getJSONObject(i).getDouble("fiyat")).floatValue(),BigDecimal.valueOf(arrayKasiyer.getJSONObject(i).getDouble("tutar")).floatValue(),arrayKasiyer.getJSONObject(i).getString("seriNO"));
                kasiyerTabloList.add(tablo);
            }
            JSONArray arrayFatura = object.getJSONArray("faturaList");
            for(int i = 0 ; !arrayFatura.isNull(i); i++) {
                Fatura fat = new Fatura(arrayFatura.getJSONObject(i).getInt("faturaId"),arrayFatura.getJSONObject(i).getString("musteriTc"),
                        BigDecimal.valueOf(arrayFatura.getJSONObject(i).getDouble("faturaTutari")).floatValue(),arrayFatura.getJSONObject(i).getString("faturaBilgisi"),
                        arrayFatura.getJSONObject(i).getInt("kullaniciId"));
                fatura.add(fat);
            }
            JSONObject objmusteri= object.getJSONObject("musteri");
            Musteri musteri = new Musteri();
            musteri.setMusteriNotu(objmusteri.getString("musteri_notu"));
            musteri.setAdres(objmusteri.getString("adres"));
            musteri.setAd(objmusteri.getString("ad"));
            musteri.setSoyad(objmusteri.getString("soyad"));
            musteri.setCinsiyet(objmusteri.getString("cinsiyet"));
            if(objmusteri.get("ev_telefonu").getClass() == String.class)
                musteri.setEvTelefon(objmusteri.getString("ev_telefonu"));
            musteri.setTc(objmusteri.getString("tc"));
            musteri.setTelefon(objmusteri.getString("telefon"));
            bilgiler.setMusteri(musteri);
            bilgiler.setKasiyerTablo(kasiyerTabloList);
            bilgiler.setFatura(fatura);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bilgiler;
    }
}