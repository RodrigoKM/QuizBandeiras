package com.android.app.quizbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {

    private TextView acertos, nome;
    private Button recomecar, sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);

        acertos = (TextView) findViewById(R.id.qnt_acerto_text_view);
        nome = (TextView) findViewById(R.id.nome_text_view);

        recomecar = (Button) findViewById(R.id.recomecar_button);
        sair = (Button) findViewById(R.id.sair_button);

        nome.setText(GerenciarVariavel.nome);
        acertos.setText(String.valueOf(GerenciarVariavel.qntAcertos) + " / " + GerenciarVariavel.totalBandeiras);

        recomecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(ResultadoActivity.this, HomeActivity.class));
            }
        });

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
