package com.android.app.quizbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private EditText nomeEditText;
    private Button comecarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        nomeEditText = (EditText) findViewById(R.id.nome_edit_text);
        comecarButton = (Button) findViewById(R.id.comecar_button);

        comecarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comecarQuiz();
            }
        });

        if (GerenciarVariavel.nome != null) {
            nomeEditText.setText(GerenciarVariavel.nome);
        }
    }

    private void comecarQuiz() {
        GerenciarVariavel.nome = nomeEditText.getText().toString();
        if (GerenciarVariavel.nome.isEmpty()) {
            Toast.makeText(this, "Digite seu Nome!", Toast.LENGTH_SHORT).show();
        } else {
            GerenciarVariavel.qntAcertos = 0;
            finish();
            startActivity(new Intent(this, QuizActivity.class));
        }
    }

}
