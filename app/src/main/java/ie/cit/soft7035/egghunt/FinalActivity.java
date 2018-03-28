package ie.cit.soft7035.egghunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        Button doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishHunt();

            }
        });
    }

    private void finishHunt() {
        Toast.makeText(this, "Thanks for playing", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, getIntent());
        finish();

    }
}
