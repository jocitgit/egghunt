package ie.cit.soft7035.egghunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int level;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        level=1;

        Button button = findViewById(R.id.beginButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startHunt();
            }
        });

    }

    private void startHunt(){
        Intent launchCode = new Intent(MainActivity.this, CodeActivity.class);
        launchCode.putExtra("level", level);
        startActivity(launchCode);
    }



}
