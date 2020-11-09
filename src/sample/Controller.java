package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Controller implements Initializable
{
    @FXML
    public TextField txtConvNumber;
    @FXML
    public ComboBox cmbNumerOriginal;
    @FXML
    public TextField txtConverted;
    @FXML
    public ComboBox cmbConvertTo;


    public Time.Measure fromString(String typeAsString)
    {
        switch (typeAsString)
        {
            case "Часы":
                return Time.Measure.h;
            case "Дни":
                return Time.Measure.day;
            case "Недели":
                return Time.Measure.week;
            case "Месяцы":
                return Time.Measure.month;
        }
        return null;
    }

    @FXML
    private TextField txtResult;

    @FXML
    private TextField txtMoney1Input;

    public void textMoney1()
    {
        System.out.println(txtMoney1Input.getText());
    }

    @FXML
    private TextField txtMoney2Input;

    public void textMoney2()
    {
        System.out.println(txtMoney2Input.getText());
    }

    @FXML
    private ComboBox<String> comboBox1;

    @FXML
    private Button btnSolve;

    @FXML
    private ComboBox<String> comboBox2;

    @FXML
    private ComboBox<String> comboBoxResult;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {


        comboBox1.getItems().setAll(
                "Часы",
                "Дни",
                "Недели",
                "Месяцы"
        );

        comboBox2.getItems().setAll(
                "Часы",
                "Дни",
                "Недели",
                "Месяцы"
        );

        comboBoxResult.getItems().setAll(
                "Сложение",
                "Вычитание",
                "Умножение",
                "Деление"/*,
                "Сравнение"*/
        );

        cmbNumerOriginal.getItems().setAll(
                "Часы",
                "Дни",
                "Недели",
                "Месяцы"
        );
        cmbConvertTo.getItems().setAll(
                "Часы",
                "Дни",
                "Недели",
                "Месяцы"
        );

        /*comboBox1.getSelectionModel().select(0);
        comboBox2.getSelectionModel().select(0);

        comboBoxResult.getSelectionModel().select(0);
        cmbNumerOriginal.getSelectionModel().select(0);
        cmbConvertTo.getSelectionModel().select(0);*/

        txtMoney1Input.textProperty().addListener((observable, oldValue, newValue) ->
        {
            ChangeCalc();
        });
        txtMoney2Input.textProperty().addListener((observable, oldValue, newValue) ->
        {
            ChangeCalc();
        });

        txtConvNumber.textProperty().addListener((observable, oldValue, newValue) ->
        {
            ChangeConv();
        });

        try
        {
            FileInputStream fis = new FileInputStream("settings.xml");
            XMLDecoder decoder = new XMLDecoder(fis);
            Settings settings = (Settings) decoder.readObject();
            comboBox1.getSelectionModel().select(settings.comboBoxSelectedIndex1);
            comboBox2.getSelectionModel().select(settings.comboBoxSelectedIndex2);
            comboBoxResult.getSelectionModel().select(settings.comboBoxSelectedIndex3);
            txtMoney1Input.setText(settings.txtInputText1);
            txtMoney2Input.setText(settings.txtInputText2);
            txtResult.setText(settings.txtInputText2);

        } catch (Exception e) {
            e.printStackTrace();
        }

        ChangeCalc();
    }

    private void ChangeConv()
    {
        int a = Integer.parseInt(txtConvNumber.getText());
        Time.Measure typeFrom = (Time.Measure) cmbNumerOriginal.getValue();
        Time.Measure typeTo = (Time.Measure) cmbConvertTo.getValue();

        Time originalNumber = new Time(a, typeFrom);

        /*if (){
            */
            Time converted = originalNumber.to(typeTo);
        /*
        }
        else{

            Time converted = originalNumber.convertInDate();

        }*/
        txtConverted.setText(converted.toString());
    }

    private void ChangeCalc()
    {

        String selectedValue = comboBoxResult.getSelectionModel().getSelectedItem();
        int a = Integer.parseInt(txtMoney1Input.getText());
        Time.Measure type1 = fromString(comboBox1.getValue());
        Time time1 = new Time(a, type1);

        int b = Integer.parseInt(txtMoney2Input.getText());
        Time.Measure type2 = fromString(comboBox2.getValue());
        Time time2 = new Time(b, type2);
        Time time3;
        switch (selectedValue)
        {
            case "Сложение":
                time3 = time1.add(time2);
                break;
            case "Вычитание":
                time3 = time1.subtract(time2);
                break;
            case "Умножение":
                time3 = time1.multiply(time2);
                break;
            case "Деление":
                time3 = time1.divide(time2);
                break;
            /**
             * как выводить в выпадающем списке и инт и булевый тип
             */
            /*case "Сравнение": // как выводить в выпадающем списке и инт и булевый тип
                boolean time3;
                time3 = time1.compare(time2);
                break;*/
            default:
                return;

        }
        txtResult.setText(time3.toString());
    }

    public void Btn1_clicked()
    {
        int a = Integer.parseInt(txtMoney1Input.getText());
        Time.Measure type1 = fromString(comboBox1.getValue());
        Time time1 = new Time(a, type1);

    }


    public void Btn2_clicked(ActionEvent actionEvent)
    {
        int b = Integer.parseInt(txtMoney2Input.getText());
        Time.Measure type2 = fromString(comboBox2.getValue());
        Time time2 = new Time(b, type2);
    }


    public void changeMoneyType(ActionEvent actionEvent)
    {
    }

    public void changeMoneyType2(ActionEvent actionEvent)
    {
    }

    public void onComboBoxChanged(ActionEvent actionEvent)
    {
        ChangeCalc();
    }

    public void onConvert(ActionEvent actionEvent)
    {
        ChangeConv();
    }


    public void Add(ActionEvent actionEvent)
    {
        String selectedValue = comboBoxResult.getSelectionModel().getSelectedItem();
        int a = Integer.parseInt(txtMoney1Input.getText());
        Time.Measure type1 = fromString(comboBox1.getValue());
        Time time1 = new Time(a, type1);

        int b = Integer.parseInt(txtMoney2Input.getText());
        Time.Measure type2 = fromString(comboBox2.getValue());
        Time time2 = new Time(b, type2);
        Time time3;

        time3 = time1.add(time2);

        txtResult.setText(time3.toString());
    }

    public void Subtract(ActionEvent actionEvent)
    {
        String selectedValue = comboBoxResult.getSelectionModel().getSelectedItem();
        int a = Integer.parseInt(txtMoney1Input.getText());
        Time.Measure type1 = fromString(comboBox1.getValue());
        Time time1 = new Time(a, type1);

        int b = Integer.parseInt(txtMoney2Input.getText());
        Time.Measure type2 = fromString(comboBox2.getValue());
        Time time2 = new Time(b, type2);
        Time time3;

        time3 = time1.subtract(time2);

        txtResult.setText(time3.toString());
    }

    public void Multiply(ActionEvent actionEvent)
    {
        String selectedValue = comboBoxResult.getSelectionModel().getSelectedItem();
        int a = Integer.parseInt(txtMoney1Input.getText());
        Time.Measure type1 = fromString(comboBox1.getValue());
        Time time1 = new Time(a, type1);

        int b = Integer.parseInt(txtMoney2Input.getText());
        Time.Measure type2 = fromString(comboBox2.getValue());
        Time time2 = new Time(b, type2);
        Time time3;

        time3 = time1.multiply(time2);

        txtResult.setText(time3.toString());
    }

    public void Divide(ActionEvent actionEvent)
    {
        String selectedValue = comboBoxResult.getSelectionModel().getSelectedItem();
        int a = Integer.parseInt(txtMoney1Input.getText());
        Time.Measure type1 = fromString(comboBox1.getValue());
        Time time1 = new Time(a, type1);

        int b = Integer.parseInt(txtMoney2Input.getText());
        Time.Measure type2 = fromString(comboBox2.getValue());
        Time time2 = new Time(b, type2);
        Time time3;

        time3 = time1.divide(time2);

        txtResult.setText(time3.toString());
    }

    public void Compare(ActionEvent actionEvent)
    {
        String selectedValue = comboBoxResult.getSelectionModel().getSelectedItem();
        int a = Integer.parseInt(txtMoney1Input.getText());
        Time.Measure type1 = fromString(comboBox1.getValue());
        Time time1 = new Time(a, type1);

        int b = Integer.parseInt(txtMoney2Input.getText());
        Time.Measure type2 = fromString(comboBox2.getValue());
        Time time2 = new Time(b, type2);
        boolean time3;

        time3 = time1.compare(time2);

        txtResult.setText(String.valueOf(time3)/*.toString()*/);
    }


    /**
     * не могу понять как заставить это работать
     */
    public void ConvertInDate(ActionEvent actionEvent)
    {
        int a = Integer.parseInt(txtConvNumber.getText());
        //Time.Measure typeFrom = (Time.Measure) cmbNumerOriginal.getValue();

        //Time originalNumber = new Time(a, typeFrom);

        Time converted = originalNumber.convertInDate(typeTo);

        txtConverted.setText(converted);
    }




    public void onStageClose()
    {
        Settings settings = new Settings();

        settings.comboBoxSelectedIndex1 = comboBox1.getSelectionModel().getSelectedIndex();
        settings.comboBoxSelectedIndex2 = comboBox2.getSelectionModel().getSelectedIndex();
        settings.comboBoxSelectedIndex3 = comboBoxResult.getSelectionModel().getSelectedIndex();
        settings.txtInputText1 = txtMoney1Input.getText();
        settings.txtInputText2 = txtMoney2Input.getText();
        settings.txtInputText3 = txtResult.getText();

        try
        {
            FileOutputStream fos = new FileOutputStream("settings.xml");
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(settings);
            encoder.close();
            fos.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
