package danishalsayed.fibonacciseries;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

class NumberHandler {
    int length;
    int[] number = new int[50];
    EditText number_field;
    TextView result_display;
    Button button1;

    void setNumber(String input) {
        length = input.length();
        int x = 0;
        for (int i = length; i > 0; i--) {
            String temp = input.substring(i - 1, i);
            number[x] = Integer.parseInt(temp);
            x++;
        }
    }

    String getNumber() {
        String output = "";
        for (int i = length - 1; i >= 0; i--)
            output += Integer.toString(number[i]);
        return output;
    }

    NumberHandler add(NumberHandler n1, NumberHandler n2) {
        NumberHandler result = new NumberHandler();
        int excess = 0;
        int x;
        for (x = 0; x < n1.length || x < n2.length; x++) {
            result.number[x] = ((x < n1.length ? n1.number[x] : 0) + (x < n2.length ? n2.number[x] : 0) + excess) % 10;
            excess = ((x < n1.length ? n1.number[x] : 0) + (x < n2.length ? n2.number[x] : 0) + excess) / 10;
        }
        if (excess > 0) {
            result.number[x] = excess;
            x++;
        }
        result.length = x;
        return result;
    }

    ArrayList<NumberHandler> Fibonacci(int n) {

        int check = 0;
        NumberHandler temp = new NumberHandler();
        ArrayList<NumberHandler> series = new ArrayList<NumberHandler>();
        temp.setNumber("1");
        series.add(temp);
        series.add(temp);
        while (check != n - 2) {
            temp = temp.add(series.get(series.size() - 1), series.get(series.size() - 2));
            series.add(temp);
            check++;
        }
        return series;

    }
}
    public class Fibonacci_series extends ActionBarActivity {

        EditText number_field;
        TextView result_display;
        Button button1;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fibonacci_series);
            number_field = (EditText) findViewById(R.id.number_field);
            result_display = (TextView) findViewById(R.id.result_display);
            //notification=(TextView)findViewById(R.id.notification);
            button1 = (Button) findViewById(R.id.button1);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<NumberHandler> result = new ArrayList<NumberHandler>();
                    String input = number_field.getText().toString();
                    int n = Integer.parseInt(input);
                    NumberHandler temp=new NumberHandler();
                    if (n > 0 && n < 481)
                        result = temp.Fibonacci(n);
                    String display = "";
                    for (int i = 0; i < n; i++) {
                        display = display + " " + result.get(i).getNumber();
                    }
                    result_display.setText(display.subSequence(0, display.length()));
                    result_display.setTextColor(-1);
                    // notification.setVisibility(View.VISIBLE);

                }
            });

        }
        @Override

        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_fibonacci_series, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }
