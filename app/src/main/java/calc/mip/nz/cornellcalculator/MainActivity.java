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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

    }

    public void doAction(View view){
        if(view instanceof Button){
            Button button = (Button)view;
            Intent intent = null;
            Log.i("INFO Choosen : ", button.getText().toString() + " ");

            switch (button.getText().toString()){

                case "Calculator":
                    intent = new Intent(this,CalculatorActivity.class);
                    startActivity(intent);
                    break;

                case "Kids Learn Maths":
                    intent = new Intent(this, MathForKidsActivity.class);
                    startActivity(intent);
                    break;

                case "Layouts":
                    intent = new Intent(this,  LayoutsActivity.class);
                    startActivity(intent);
                    break;

            }
        }
    }
}
