package com.example.user.Unit_Converter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class Temperature extends AppCompatActivity {
    String[] data = {"Celsius", "Fahrenheit", "Kelvin", "Rankine", "Reaumur"};
    Map<String, String> types = new TreeMap<>();
    static String from = "";
    static String to = "";
    static String input= "";
    EditText in;
    TextView answer;
    static int i=0;
    static int j=0;
    static int z=0;
    static int k=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.my_spinner, data);
        type_init();
        answer = (TextView)findViewById(R.id.textView2_t);
        answer.setText("result");
        final EditText in = (EditText)findViewById(R.id.editText_t);
        spinner_from_init(adapter);
        spinner_to_init(adapter);
        Spinner spinner = (Spinner) findViewById(R.id.from_type_t);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner_t);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spinner);
            android.widget.ListPopupWindow popupWindow1 = (android.widget.ListPopupWindow) popup.get(spinner1);
            popupWindow.setHeight(getResources().getDimensionPixelSize(R.dimen._105sdp));
            popupWindow1.setHeight(getResources().getDimensionPixelSize(R.dimen._105sdp));
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
        }
        in.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                input = (in.getText()).toString();
                Check1 a = new Check1();
                BigDecimal ininput = new BigDecimal("-1");

                if(a.check(input) && input.length()>=1){

                    if((input.equals(".") && input.length()<2)||(input.equals(".-") && input.length()<3)||(input.equals("-.") && input.length()<3)||(input.substring(input.length()-1).equals("-") && input.length()>2)){
                        if(z>input.length()){}
                        else{Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();
                        z=input.length();
                        }
                    }
                    else if(input.equals("-") && input.length()<2){}
                    else{
                        ininput = new BigDecimal(input);
                        if ((Objects.equals(from, "Celsius") && ininput.compareTo(BigDecimal.valueOf(-273.15))<0)||
                                (Objects.equals(from, "Fahrenheit") && ininput.compareTo(BigDecimal.valueOf(-459.67))<0)||
                                (Objects.equals(from, "Kelvin") && ininput.compareTo(BigDecimal.valueOf(0))<0)||
                                (Objects.equals(from, "Rankine") && ininput.compareTo(BigDecimal.valueOf(0))<0)||
                                (Objects.equals(from, "Reaumur") && ininput.compareTo(BigDecimal.valueOf(-218.52))<0)){Toast.makeText(getApplicationContext(), "non-existent temperature", Toast.LENGTH_SHORT).show();}
                        else{
                        Temp_convetrer(types.get(from), types.get(to), input);}}}
                else{
                    if(!(a.check(input)) && input.length()>=1){
                        if(z>input.length()){}
                        else{Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}}
                    else{answer.setText("result");}
                    z=input.length();}
            }
        });}
protected void  aftersmthhappened(){
    Check1 a = new Check1();
    BigDecimal ininput = new BigDecimal("-1");

        if(a.check(input) && input.length()>=1){
            if(!Objects.equals(input, "")){ininput = new BigDecimal(input);}
            if((input.equals(".") && input.length()<2)||(input.equals(".-") && input.length()<3)||(input.equals("-.") && input.length()<3)||(input.equals("-") && !(input.length()<2))){
                Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();
            }
            else if(input.equals("-") && input.length()<2){}
            else{
                ininput = new BigDecimal(input);
                if ((Objects.equals(from, "Celsius") && ininput.compareTo(BigDecimal.valueOf(-273.15))<0)||
                        (Objects.equals(from, "Fahrenheit") && ininput.compareTo(BigDecimal.valueOf(-459.67))<0)||
                        (Objects.equals(from, "Kelvin") && ininput.compareTo(BigDecimal.valueOf(0))<0)||
                        (Objects.equals(from, "Rankine") && ininput.compareTo(BigDecimal.valueOf(0))<0)||
                        (Objects.equals(from, "Reaumur") && ininput.compareTo(BigDecimal.valueOf(-218.52))<0)){Toast.makeText(getApplicationContext(), "non-existent temperature", Toast.LENGTH_SHORT).show();}
                else{
                    Temp_convetrer(types.get(from), types.get(to), input);}}}
        else{
            if(a.check(input)==false && input.length()>=1){Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
            else{answer.setText("result");}
    }
}
    @Override
    protected void onStart() {
        if(k==1){
            aftersmthhappened();}
        if(k==0){
            input= "";
            i=0;
            j=0;}
        super.onStart();
    }
    @Override
    protected void onDestroy() {
        k=0;
        super.onDestroy();
    }

    void type_init(){
        types.put("Celsius", "c");
        types.put("Fahrenheit", "f");
        types.put("Kelvin", "k");
        types.put("Rankine", "r");
        types.put("Reaumur", "re");
    }
    void spinner_from_init(ArrayAdapter a){
        Spinner from_spinner = (Spinner) findViewById(R.id.from_type_t);
        from_spinner.setAdapter(a);
        from_spinner.setPrompt("Select type");
        from_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextSize(20);
                from = data[position];
                Check1 a = new Check1();
                BigDecimal ininput = new BigDecimal("-1");
                if(i<1){i+=1;}
                else{
                if(a.check(input) && input.length()>=1){
                    if(!Objects.equals(input, "")){ininput = new BigDecimal(input);}
                    if((input.equals(".") && input.length()<2)||(input.equals(".-") && input.length()<3)||(input.equals("-.") && input.length()<3)||(input.equals("-") && !(input.length()<2))){
                        Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();
                    }
                    else if(input.equals("-") && input.length()<2){}
                    else{
                        ininput = new BigDecimal(input);
                        if ((Objects.equals(from, "Celsius") && ininput.compareTo(BigDecimal.valueOf(-273.15))<0)||
                                (Objects.equals(from, "Fahrenheit") && ininput.compareTo(BigDecimal.valueOf(-459.67))<0)||
                                (Objects.equals(from, "Kelvin") && ininput.compareTo(BigDecimal.valueOf(0))<0)||
                                (Objects.equals(from, "Rankine") && ininput.compareTo(BigDecimal.valueOf(0))<0)||
                                (Objects.equals(from, "Reaumur") && ininput.compareTo(BigDecimal.valueOf(-218.52))<0)){Toast.makeText(getApplicationContext(), "non-existent temperature", Toast.LENGTH_SHORT).show();}
                        else{
                            Temp_convetrer(types.get(from), types.get(to), input);}}}
                else{
                    if(a.check(input)==false && input.length()>=1){Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
                    else{answer.setText("result");}}

            }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });}
    void spinner_to_init(ArrayAdapter a){
        Spinner to_spinner = (Spinner)findViewById(R.id.spinner_t);
        to_spinner.setAdapter(a);
        to_spinner.setPrompt("Select type");
        to_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextSize(20);
                to = data[position];
                Check1 a = new Check1();
                BigDecimal ininput = new BigDecimal("-1");
                if(j<1){j+=1;}
                else{
                if(a.check(input) && input.length()>=1){
                    if(!Objects.equals(input, "")){ininput = new BigDecimal(input);}
                    if((input.equals(".") && input.length()<2)||(input.equals(".-") && input.length()<3)||(input.equals("-.") && input.length()<3||(input.equals("-") && !(input.length()<2)))){
                        Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();
                    }
                    else if(input.equals("-") && input.length()<2){}
                    else if((Objects.equals(from, "Celsius") && ininput.compareTo(BigDecimal.valueOf(-273.15))<0)||
                            (Objects.equals(from, "Fahrenheit") && ininput.compareTo(BigDecimal.valueOf(-459.67))<0)||
                           (Objects.equals(from, "Kelvin") && ininput.compareTo(BigDecimal.valueOf(0))<0)||
                            (Objects.equals(from, "Rankine") && ininput.compareTo(BigDecimal.valueOf(0))<0)||
                            (Objects.equals(from, "Reaumur") && ininput.compareTo(BigDecimal.valueOf(-218.52))<0))
                    {Toast.makeText(getApplicationContext(), "non-existent temperature", Toast.LENGTH_SHORT).show();
                        answer.setText("result");}
                    else{Temp_convetrer(types.get(from), types.get(to), input);}}
                else{
                    if(a.check(input)==false && input.length()>=1){Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
                    else{answer.setText("result");}}
            }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    BigDecimal converter(String to, String from, BigDecimal a){
        BigDecimal onepointeight = new BigDecimal("1.8");
        BigDecimal pointeight = new BigDecimal("0.8");
        BigDecimal thirtytwo = new BigDecimal("32");
        BigDecimal longone = new BigDecimal("273.15");
        BigDecimal longtwo = new BigDecimal("459.67");
        BigDecimal longthree = new BigDecimal("491.67");
        BigDecimal twotwofive = new BigDecimal("2.25");
        BigDecimal onetwofive = new BigDecimal("1.25");
        switch (from){
            case "c":
                switch(to){
                    case "c": return a;
                    case "f": return (a.multiply(onepointeight)).add(thirtytwo);
                    case "k": return a.add(longone);
                    case "r": return (a.add(longone)).multiply(onepointeight);
                    case "re": return a.multiply(pointeight);
                }
            case "f":
                switch(to){
                    case "c": return (a.subtract(thirtytwo)).divide(onepointeight,1000,ROUND_HALF_UP);
                    case "f": return a;
                    case "k": return (a.add(longtwo)).divide(onepointeight,1000,ROUND_HALF_UP);
                    case "r": return a.add(longtwo);
                    case "re": return (a.subtract(thirtytwo)).divide(twotwofive,1000,ROUND_HALF_UP);
                }
            case "k":
                switch(to){
                    case "c": return a.subtract(longone);
                    case "f": return (a.multiply(onepointeight)).subtract(longtwo);
                    case "k": return a;
                    case "r": return a.multiply(onepointeight);
                    case "re": return (a.subtract(longone)).multiply(pointeight);
                }
            case "r":
                switch(to){
                    case "c": return (a.subtract(longthree)).divide(onepointeight,1000, ROUND_HALF_UP);
                    case "f": return a.subtract(longtwo);
                    case "k": return a.divide(onepointeight,1000, ROUND_HALF_UP);
                    case "r": return a;
                    case "re": return (a.subtract(longthree)).divide(twotwofive,1000, ROUND_HALF_UP);

                }
            case "re":
                switch(to){
                    case "c": return a.multiply(onetwofive);
                    case "f": return (a.multiply(twotwofive)).add(thirtytwo);
                    case "k": return (a.multiply(onetwofive)).add(longone);
                    case "r": return (a.multiply(twotwofive)).add(longthree);
                    case "re": return a;
                }
            default: return longthree;
        }

    }
    void Temp_convetrer(String from, String to, String amount){
        int scale = 10;
        Settings sss = new Settings();
        scale = sss.getDefaults("scale",Temperature.this);
        int count = 0;
        BigDecimal from_amount = new BigDecimal(amount);
        BigDecimal ten = new BigDecimal("10");
        BigDecimal one = new BigDecimal("1");
           BigDecimal to_amount = converter(to, from, from_amount);
        if(to_amount.compareTo(BigDecimal.ZERO)==0){answer.setText("0");}
        else{while (to_amount.compareTo(ten) >= 0) {
            to_amount = to_amount.divide(ten, 1000, ROUND_HALF_UP);
            ++count;
        }
            while (to_amount.compareTo(one) < 0 && to_amount.compareTo(BigDecimal.ZERO) > 0) {
                to_amount = to_amount.multiply(ten);
                --count;
            }
            if (count <= 7 && count >= -7) {
                if (scale<= -count && count<0){
                    BigDecimal to_amountf = to_amount.setScale(scale, RoundingMode.HALF_UP).stripTrailingZeros();
                    String output = to_amountf.toPlainString() + "E" + Integer.toString(count);
                    answer.setText(output);}
                else{
                    BigDecimal BigTen = new BigDecimal(degree(count));
                    to_amount = to_amount.multiply(BigTen);
                    BigDecimal to_amountf = to_amount.setScale(scale, RoundingMode.HALF_UP).stripTrailingZeros();
                    String output = to_amountf.toPlainString();
                    answer.setText(output);
                }
            } else if (count > 7) {
                count -= 7;
                BigDecimal BigTen = new BigDecimal("10000000");
                to_amount = to_amount.multiply(BigTen);
                BigDecimal to_amountf = to_amount.setScale(scale, RoundingMode.HALF_UP).stripTrailingZeros();
                String output = to_amountf.toPlainString() + "E" + Integer.toString(count);
                answer.setText(output);
            }
            else if (count < -7) {
                if (scale>= -count){
                    count += 7;
                    BigDecimal BigTen = new BigDecimal("10000000");
                    to_amount = to_amount.divide(BigTen);
                    BigDecimal to_amountf = to_amount.setScale(scale, RoundingMode.HALF_UP).stripTrailingZeros();
                    String output = to_amountf.toPlainString() + "E" + Integer.toString(count);
                    answer.setText(output);}
                else {
                    BigDecimal to_amountf = to_amount.setScale(scale, RoundingMode.HALF_UP).stripTrailingZeros();
                    String output = to_amountf.toPlainString() + "E" + Integer.toString(count);
                    answer.setText(output);
                }}}

    }

    String degree(int count)
    {
        String BigTen = "1";
        if(count > 0){
            for(int x = 0; x < count; x++)
            {
                BigTen = BigTen + "0";
            }}
        else if(count<0){
            for(int x = 0; x < -(count+1); x++)
            {
                BigTen = "0" + BigTen;
            }
            BigTen = "0."+BigTen;
        }
        return BigTen;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_upgraded, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_1:
                finish();
                return true;
            case R.id.action_2:
                Intent intent1 = new Intent(this, Authors.class);
                k=1;
                startActivity(intent1);
                return true;
            case R.id.action_3:
                Intent intent2 = new Intent(this, Help.class);
                k=1;
                startActivity(intent2);
                return true;
            case R.id.action_4:
                Intent intent3 = new Intent(this, Settings.class);
                k=1;
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }}

