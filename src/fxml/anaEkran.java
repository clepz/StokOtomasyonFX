package fxml;

import com.jfoenix.controls.*;
import http.HttpRequests;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Musteri;
import model.SatisTablo;
import model.Urun;

public class anaEkran {

    ObservableList<SatisTablo> list;

    @FXML
    private JFXTextField barkod;

    @FXML
    private JFXTextField marka;

    @FXML
    private JFXTextField model;

    @FXML
    private JFXTextField urunAciklamasi;

    @FXML
    private JFXTextField seriNo;

    @FXML
    private JFXTextField fiyat;

    @FXML
    private JFXTextField bolumNo;

    @FXML
    private JFXTextField bundle;

    @FXML
    private JFXTextField tc;

    @FXML
    private JFXTextField ad;

    @FXML
    private JFXTextField soyad;

    @FXML
    private JFXTextField adres;

    @FXML
    private JFXTextField cepTelefonu;

    @FXML
    private JFXTextField evTelefonu;

    @FXML
    private JFXTextField musteriNotu;

    @FXML
    private TableView<SatisTablo> tablo;

    @FXML
    private TableColumn<?, ?> barkodColumn;

    @FXML
    private TableColumn<?, ?> urunAciklamaColumn;

    @FXML
    private TableColumn<SatisTablo, Spinner> miktarColumn;

    @FXML
    private TableColumn<?, ?> bolumColumn;

    @FXML
    private TableColumn<?, ?> fiyatColumn;

    @FXML
    private TableColumn<?, ?> seriNoColumn;

    @FXML
    private TableColumn<SatisTablo, String> islemColumn;


    @FXML
    JFXComboBox<Character> cinsiyetComboBox;

    @FXML
    private Button btnSatisYap;

    @FXML
    private Button ekleBtn;

    Urun urun;

    @FXML
    void initialize(){
        list = FXCollections.observableArrayList();
        ekleBtn.setDisable(false);
        cinsiyetComboBox.getItems().addAll('E','K');
        barkodColumn.setCellValueFactory(new PropertyValueFactory<>("barkod"));
        urunAciklamaColumn.setCellValueFactory(new PropertyValueFactory<>("urunAciklamasi"));
        bolumColumn.setCellValueFactory(new PropertyValueFactory<>("bolumNo"));
        seriNoColumn.setCellValueFactory(new PropertyValueFactory<>("seriNo"));
        fiyatColumn.setCellValueFactory(new PropertyValueFactory<>("fiyattablo"));
        miktarColumn.setCellValueFactory(new PropertyValueFactory<SatisTablo, Spinner>("miktarSpinner"));
        islemColumn.setCellValueFactory(new PropertyValueFactory<>("dummy"));

        Callback<TableColumn<SatisTablo, String>, TableCell<SatisTablo, String>> cellFactory
                = //
                new Callback<TableColumn<SatisTablo, String>, TableCell<SatisTablo, String>>() {
                    @Override
                    public TableCell call(final TableColumn<SatisTablo, String> param) {
                        final TableCell<SatisTablo, String> cell = new TableCell<SatisTablo, String>() {

                            final Button btn = new Button("sil");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        list.remove(getIndex());
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        islemColumn.setCellFactory(cellFactory);
        //miktarColumn.setCellFactory(cellFactory2);
        tablo.setItems(list);
    }

    @FXML
    void barkodKeyReleased(){
        if(barkod.getText().length() == 16){
            HttpRequests requests = new HttpRequests();
            urun = requests.urunAl(barkod.getText());
            if(urun != null && urun.getAdet() != 0){
                marka.setText(urun.getMarka());
                model.setText(urun.getModel());
                urunAciklamasi.setText(urun.getAciklama());
                fiyat.setText(String.valueOf(urun.getFiyat()));
                bolumNo.setText(urun.getBolum_no());
                seriNo.setText(urun.getSeri_no());
                bundle.setText(String.valueOf(urun.getBundleVarMi()));
                ekleBtn.setDisable(false);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Ürün Stoklarda Bulunmamaktadır.", ButtonType.CLOSE);
                alert.show();
                ekleBtn.setDisable(true);
            }
        }else if(barkod.getText().length()>16){
           urunTemizle();
        }
    }

    @FXML
    void ekleBtnClicked(){
        list.add(new SatisTablo(urun.getBarkod(),urun.getSeri_no(),urun.getBolum_no(),urun.getFiyat(), urun.getAciklama(),urun.getAdet()));
        urunTemizle();
    }

    private void urunTemizle() {
        marka.setText("");
        model.setText("");
        urunAciklamasi.setText("");
        fiyat.setText("");
        bolumNo.setText("");
        bundle.setText("");
        seriNo.setText("");
        ekleBtn.setDisable(true);
    }

    @FXML
    void satisYapClicked(){
        list.forEach(u -> System.out.println(u.getBarkod() + " , miktar :" + u.getMiktar() + " , fiyat : "+u.getFiyattablo()));
        Musteri musteri = new Musteri();
        musteri.setAd(ad.getText());
        musteri.setSoyad(soyad.getText());
        musteri.setTc(tc.getText());
        musteri.setCinsiyet(String.valueOf(cinsiyetComboBox.getValue()));
        musteri.setTelefon(cepTelefonu.getText());
        musteri.setEvTelefon(evTelefonu.getText());
        musteri.setAdres(adres.getText());
        musteri.setMusteriNotu(musteriNotu.getText());

        HttpRequests requests = new HttpRequests();
        while(!list.isEmpty()) {
            requests.urunSat(list.get(0).getBarkod(), list.remove(0).getMiktar(),musteri);
        }
    }

    @FXML
    void topBarPressed(){

    }

    @FXML
    void topBarDragged(){

    }
}
