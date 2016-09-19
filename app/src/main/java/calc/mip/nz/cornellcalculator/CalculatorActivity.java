package calc.mip.nz.cornellcalculator;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CalculatorActivity extends AppCompatActivity {
    private double x, y, result;
    private int defTxtLength = 7;
    private float offsetFontSize = 16;
    private boolean isFirst = true;
    private double textSize;
    private boolean isXSet = false;
    private boolean isYset = false;
    private DecimalFormat df = new DecimalFormat("0.####", DecimalFormatSymbols.getInstance());

    private String choosenOperation = "";
    private TextView txtView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        getSupportActionBar().hide();
        Locale locale = new Locale("en");

        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        txtView = (TextView) findViewById(R.id.txtViewResult);


    }

    public void doAdd(View view){


        if(view instanceof Button){
            float textSize = txtView.getTextSize();
            float pixelSd = getResources().getDisplayMetrics().density;
            Button button = (Button)view;

            String num = button.getText().toString();

            Log.i("INFO", "YES IT IS BUTTON");
            Log.i("INFO: Button was pressed :", String.valueOf(num));

            // CHECK IF IT FIRST NUM DELETE 0
            if(isFirst) {
                txtView.setText("");
                isFirst = false;
            }

            // if x already set (mean an user pressed operation button)
            if (isXSet && !isYset){
                txtView.setText("");
                isYset = true;
            }

            switch (num){
                case "1": txtView.setText(txtView.getText().toString() + 1); break;
                case "2": txtView.setText(txtView.getText().toString() + 2); break;
                case "3": txtView.setText(txtView.getText().toString() + 3); break;
                case "4": txtView.setText(txtView.getText().toString() + 4); break;
                case "5": txtView.setText(txtView.getText().toString() + 5); break;
                case "6": txtView.setText(txtView.getText().toString() + 6); break;
                case "7": txtView.setText(txtView.getText().toString() + 7); break;
                case "8": txtView.setText(txtView.getText().toString() + 8); break;
                case "9": txtView.setText(txtView.getText().toString() + 9); break;
                case "0": txtView.setText(txtView.getText().toString() + 0); break;
                case ".":
                    // if there is no any number add 0.
                    if(txtView.getText().toString().equals("") && txtView.getText().toString().isEmpty()){
                        txtView.setText("0.");
                    }else{
                        if(txtView.getText().toString().charAt(txtView.getText().length()-1) == '.' ){
                            Toast.makeText(getApplicationContext(),"Delimeter alredy exist", Toast.LENGTH_SHORT).show();
                        }else{
                            txtView.setText(txtView.getText().toString() + ".");
                        }


                    }

                    break;


            }


            Log.i("INFO FONT SIZE", String.valueOf(textSize / getResources().getDisplayMetrics().scaledDensity));
            Log.i("INFO Display Metrics", String.valueOf(getResources().getDisplayMetrics().density));

            //Todo to adjust the fontsize
            if(txtView.getText().length() >  defTxtLength && (textSize / getResources().getDisplayMetrics().scaledDensity) > 40) {

                defTxtLength += 2;

                txtView.setTextSize( (textSize / pixelSd) - offsetFontSize);
                Log.i("INFO : TextSize = ", Float.toString(txtView.getTextSize()) );
                offsetFontSize -= 3;
            }

        }
        else{
            Log.i("INFO", "NOT A BUTTON");
        }
    }

    public void doAC(View view){
        Toast.makeText(getApplicationContext(),"Clear result", Toast.LENGTH_LONG).show();
        txtView.setTextSize(80);
        txtView.setText("0");

        defTxtLength = 7;
        offsetFontSize = 15;
        isFirst = true;

        isXSet = false;
        isYset = false;

    }

    void doAddOpperation(View view){
        TextView textView = (TextView) findViewById(R.id.txtViewResult);

        if(view instanceof  Button){

            // if an user selected the operation second time
            // when Y was defined throw the result and leave the method
            if(isYset){
                doResult(view);
                return;
            }

            Button button = (Button)view;
            String buttonName = button.getText().toString();

            Log.i("INFO Operation : ", buttonName);

            //Change isXSet to show that x already installed (the operation button was pressed)
            isXSet = true;

            switch (buttonName){
                case "/": choosenOperation = "/";
                    x =  Double.parseDouble(textView.getText().toString());
                    isXSet = true;
                    break;

                case "X": choosenOperation = "X";
                    x =  Double.parseDouble(textView.getText().toString());
                    isXSet = true;
                    break;

                case "-": choosenOperation = "-";
                    x =  Double.parseDouble(textView.getText().toString());
                    isXSet = true;
                    break;

                case "+": choosenOperation = "+";
                    x =  Double.parseDouble(textView.getText().toString());
                    isXSet = true;
                    break;


            }

        }

    }

    public void doResult(View view){
        Log.i("INFO : RESULT : " , " Equal button was pressed" );
        df.setRoundingMode(RoundingMode.CEILING);
        try{

            if(isXSet && isYset && !choosenOperation.isEmpty()){
                y = Double.parseDouble(txtView.getText().toString());
                Log.i("INFO : doResult : ", "Calculate the result");
                Log.i("INFO : x and y : ", x+" "+y);
                Log.i("INFO : operation : ", choosenOperation);



                switch (choosenOperation) {
                    case "/":

                        if (x % y == 0) {

                            txtView.setText(String.valueOf(Math.round(x / y)));
                        } else {
                            txtView.setText(df.format(x / y).replace(",","."));
                        }
                        break;

                    case "X":
                        result = x * y;
                        Log.i("INFO Result : ", df.format(result));
                        txtView.setText(df.format(result).replace(",","."));
                        break;

                    case "-":
                        result = x - y;
                        txtView.setText(df.format(result).replace(",","."));
                        break;

                    case "+":
                        result = x + y;
                        txtView.setText(df.format(result).replace(",","."));
                        break;
                }


                x = Double.parseDouble( txtView.getText().toString().replace(",","."));
                isYset = false;
                Log.i("INFO : Result ", txtView.getText().toString());

            }
        }catch(Exception e){

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            doAC(view);
        }
    }



    public void doPlusMinus(View view){
        double num = Double.parseDouble(txtView.getText().toString());

        if(num < 0){
            txtView.setText( df.format(Math.abs(num)) );
        }else{
            txtView.setText("-"+txtView.getText().toString());
        }
    }

    // Get the percent from the number ( / 100)
    public void doPercent(View view){

        txtView.setText(df.format(Double.parseDouble(txtView.getText().toString()) / 100).replace(",",".") );

    }
    public void goBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}
