package karpushinkirill.gr303.fruts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    int amount;
    double result, price;
    CheckBox[] checkBoxes = new CheckBox[4];
    EditText[] amounts = new EditText[4];
    EditText[] prices = new EditText[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxes[0] = findViewById(R.id.cbApple);
        checkBoxes[1] = findViewById(R.id.cbStrawberry);
        checkBoxes[2] = findViewById(R.id.cbBlueberry);
        checkBoxes[3] = findViewById(R.id.cbPotatoes);

        amounts[0] = findViewById(R.id.etApple);
        amounts[1] = findViewById(R.id.etStrawberry);
        amounts[2] = findViewById(R.id.etBlueberry);
        amounts[3] = findViewById(R.id.etPotatoes);

        prices[0] = findViewById(R.id.etApplePrice);
        prices[1] = findViewById(R.id.etStrawberryPrice);
        prices[2] = findViewById(R.id.etBlueberryPrice);
        prices[3] = findViewById(R.id.etPotatoesPrice);

        prices[0].setText("10.25");
        prices[1].setText("20.10");
        prices[2].setText("5.99");
        prices[3].setText("17.50");
    }

    public void onCalc(View view){
        String output = "";
        double sum=0;

        for (int i = 0; i < checkBoxes.length; i++) {
            if(checkBoxes[i].isChecked()){
                String amountTxt = amounts[i].getText().toString();
                String priceTxt = prices[i].getText().toString();

                amount = Integer.parseInt(amountTxt);
                price = Double.parseDouble(priceTxt);
                result = amount*price;

                if (amountTxt.isEmpty()) {
                    Toast.makeText(this, "Пустое поле", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (priceTxt.isEmpty()) {
                    Toast.makeText(this, "Пустое поле", Toast.LENGTH_SHORT).show();
                    return;
                }

                sum += result;
                output += String.format("%d: %d x %s = %d x %.2f = %.2f\n",
                        i+1, amount, checkBoxes[i].getText().toString(), amount, price, result);
            }
        }
        output += String.format("\ntotal - %.2f", sum);

        if (((RadioButton)findViewById(R.id.rbToast)).isChecked()) {
            Toast.makeText(this, output, Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.create();
        dialog.setIcon(R.drawable.icon);
        dialog.setTitle("Results");
        dialog.setMessage(output);
        dialog.show();
    }
}