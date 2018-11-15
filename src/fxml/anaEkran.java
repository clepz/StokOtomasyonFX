package fxml;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import http.HttpRequests;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import model.SatisTablo;
import com.sun.prism.impl.Disposer.Record;
import model.Urun;

public class anaEkran {

    ObservableList<SatisTablo> list = FXCollections.observableArrayList();

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
    private JFXTreeTableView<SatisTablo> tablo;

    @FXML
    private TreeTableColumn<SatisTablo, String> barkodColumn;

    @FXML
    private TreeTableColumn<SatisTablo, String> urunAciklamaColumn;

    @FXML
    private TreeTableColumn<SatisTablo, Integer> miktarColumn;

    @FXML
    private TreeTableColumn<SatisTablo, String> bolumColumn;

    @FXML
    private TreeTableColumn<SatisTablo, String> fiyatColumn;

    @FXML
    private TreeTableColumn<SatisTablo, String> seriNoColumn;

    @FXML
    private TreeTableColumn<SatisTablo, String> islemColumn;


    @FXML
    JFXComboBox<Character> cinsiyetComboBox;

    @FXML
    JFXButton ekleBtn;

    @FXML
    void initialize(){
        ekleBtn.setDisable(false);
        cinsiyetComboBox.getItems().addAll('F','M');
        barkodColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SatisTablo, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<SatisTablo, String> param) {
                return param.getValue().getValue().barkodProperty();
            }
        });
        urunAciklamaColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SatisTablo, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<SatisTablo, String> param) {
                return param.getValue().getValue().urunAciklamasiProperty();
            }
        });bolumColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SatisTablo, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<SatisTablo, String> param) {
                return param.getValue().getValue().bolumNoProperty();
            }
        });seriNoColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SatisTablo, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<SatisTablo, String> param) {
                return param.getValue().getValue().seriNoProperty();
            }
        });fiyatColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SatisTablo, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<SatisTablo, String> param) {
                return param.getValue().getValue().fiyatProperty();
            }
        });

        Callback<TreeTableColumn<SatisTablo, String>, TreeTableCell<SatisTablo, String>> cellFactory
                = //
                new Callback<TreeTableColumn<SatisTablo, String>, TreeTableCell<SatisTablo, String>>() {
                    @Override
                    public TreeTableCell call(final TreeTableColumn<SatisTablo, String> param) {
                        final TreeTableCell<SatisTablo, String> cell = new TreeTableCell<SatisTablo, String>() {

                            final JFXButton btn = new JFXButton("Sil");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setButtonType(JFXButton.ButtonType.RAISED);
                                    btn.setOnAction(event -> {
                                        //Button Action here
                                        list.remove(this.getIndex());
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        Callback<TreeTableColumn<SatisTablo, Integer>, TreeTableCell<SatisTablo, Integer>> cellFactory2
                = //
                new Callback<TreeTableColumn<SatisTablo, Integer>, TreeTableCell<SatisTablo, Integer>>() {
                    @Override
                    public TreeTableCell call(final TreeTableColumn<SatisTablo, Integer> param) {
                        final TreeTableCell<SatisTablo, Integer> cell = new TreeTableCell<SatisTablo, Integer>() {

                            final Spinner<Integer> btn = new Spinner<>(1,10,1);

                            @Override
                            public void updateItem(Integer item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    setGraphic(btn);
                                    setText(null);
                                    btn.valueProperty().addListener((observable, oldValue, newValue) -> list.get(this.getIndex()).setMiktar(newValue));
                                }
                            }
                        };
                        return cell;
                    }
                };
        islemColumn.setCellFactory(cellFactory);

        miktarColumn.setCellFactory(cellFactory2);

        final TreeItem<SatisTablo> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        tablo.setRoot(root);
        tablo.setShowRoot(false);

    }

    @FXML
    void barkodKeyReleased(){
        if(barkod.getText().length() == 16){
            HttpRequests requests = new HttpRequests();
            Urun urun = requests.urunAl(barkod.getText());
            if(urun != null){
                marka.setText(urun.getMarka());
                model.setText(urun.getModel());
                urunAciklamasi.setText(urun.getAciklama());
                fiyat.setText(String.valueOf(urun.getFiyat()));
                bolumNo.setText(urun.getBolum_no());
                bundle.setText(String.valueOf(urun.getBundleVarMi()));
                ekleBtn.setDisable(false);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Ürün Bulunmamaktadır.", ButtonType.CLOSE);
                alert.show();
                ekleBtn.setDisable(true);
            }
        }else if(barkod.getText().length()>16){
            marka.setText("");
            model.setText("");
            urunAciklamasi.setText("");
            fiyat.setText("");
            bolumNo.setText("");
            bundle.setText("");
        }
    }

    @FXML
    void ekleBtnClicked(){

        list.add(new SatisTablo(barkod.getText(),seriNo.getText(),bolumNo.getText(),fiyat.getText(), urunAciklamasi.getText()));

    }

    @FXML
    void closeClicked(){
        Platform.exit();
    }

    @FXML
    void topBarPressed(){

    }

    @FXML
    void topBarDragged(){

    }
}
