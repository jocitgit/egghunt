package ie.cit.soft7035.egghunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CodeActivity extends AppCompatActivity {

    private int level;
    private EditText codeEditText;
    private String codeText;

    private final int SUCCESS_CODE = 1;
    private final int FINAL_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        EditText codeEditText = findViewById(R.id.codeEditText);

        if (savedInstanceState != null) {
            level = savedInstanceState.getInt("level");
            codeEditText.setText(savedInstanceState.getString("codeText"));

        } else {
            level = getIntent().getIntExtra("level",1);
        }

        showClue();

        Button goButton = findViewById(R.id.goButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCode();
            }
        });

        codeEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((i == KeyEvent.KEYCODE_ENTER) && (keyEvent.getAction() == KeyEvent.ACTION_UP)) {
                    checkCode();
                    return true;
                }
                return false;
            }
        });
    }

    private void showClue() {
        TextView clueNumber = findViewById(R.id.clueNumber);
        TextView clue1 = findViewById(R.id.clueLine1);
        TextView clue2 = findViewById(R.id.clueLine2);

        clueNumber.setText("Clue " + level);
        clue1.setText(Code.clues[level-1][0]);
        clue2.setText(Code.clues[level-1][1]);

    }

    private void checkCode() {
        codeText = codeEditText.getText().toString();
        System.out.println("cet:" + codeEditText.toString());
        if (!TextUtils.isEmpty(codeText)) {
            System.out.println("Code.codes.length");
            System.out.println(level);
            System.out.println(Code.codes[level-1]);
            if (codeText.equalsIgnoreCase(Code.codes[level-1])) {
                codeEditText.setText("");
                success();
            } else {
                Toast.makeText(this, "Oops! That's not the right code", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Enter a code first!", Toast.LENGTH_SHORT).show();
        }
    }

    private void success() {
        if (level == Code.codes.length) {
            Intent launchFinal = new Intent(CodeActivity.this, FinalActivity.class);
            startActivityForResult(launchFinal, FINAL_CODE);
        } else {
            Intent launchSuccess = new Intent(CodeActivity.this, SuccessActivity.class);
            startActivityForResult(launchSuccess, SUCCESS_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SUCCESS_CODE && resultCode == RESULT_OK) {
            level++;
            showClue();
        } else if (requestCode == FINAL_CODE && resultCode == RESULT_OK) {
            finish();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        codeText = codeEditText.getText().toString();
        outState.putInt("level", level);
        outState.putString("codeText", codeText);
    }
}
