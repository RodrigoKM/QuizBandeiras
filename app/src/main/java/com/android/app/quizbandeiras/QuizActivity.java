package com.android.app.quizbandeiras;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private ImageView imagemBandeira;
    private RadioGroup radioGroup;
    private Button respostaButton, proximaButton;
    private RadioButton[] opcoesRadioButton = new RadioButton[4];
    private Map<String, Integer> bandeirasMap = new HashMap<>();
    private Iterator bandeirasIterator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        imagemBandeira = (ImageView) findViewById(R.id.bandeira_image_view);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        opcoesRadioButton[0] = (RadioButton) findViewById(R.id.opcao1);
        opcoesRadioButton[1] = (RadioButton) findViewById(R.id.opcao2);
        opcoesRadioButton[2] = (RadioButton) findViewById(R.id.opcao3);
        opcoesRadioButton[3] = (RadioButton) findViewById(R.id.opcao4);
        respostaButton = (Button) findViewById(R.id.resposta_button);
        proximaButton = (Button) findViewById(R.id.proxima_button);

        proximaButton.setEnabled(false);

        adicionarBandeirasHashMap();
        bandeirasIterator = bandeirasMap.keySet().iterator();
        iterarBandeiras();

        respostaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarResposta();
            }
        });

        proximaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iterarBandeiras();
            }
        });

        GerenciarVariavel.totalBandeiras = bandeirasMap.size();
    }

    private void mostrarResposta() {
        if (proximaButton.isEnabled())
            return;

        boolean escolheuOpcao = false;
        for (RadioButton opcao : opcoesRadioButton) {
            if (opcao.isChecked()) {
                escolheuOpcao = true;
            }
        }
        if (!escolheuOpcao) {
            Toast.makeText(this, "Escolha uma Alternativa!", Toast.LENGTH_SHORT).show();
            return;
        }

        habilitarProximaButton(true);
        habilitarRadio(false);

        mudarCorRadioText();

        for (RadioButton opcao : opcoesRadioButton) {
            if (opcao.isChecked() && opcao.getCurrentTextColor() == Color.GREEN) {
                GerenciarVariavel.qntAcertos++;
            }
        }
    }

    private void corRadioTextPadrao() {
        for (RadioButton opcao : opcoesRadioButton) {
            opcao.setTextColor(Color.BLACK);
        }
    }

    private void mudarCorRadioText() {
        for (RadioButton opcao : opcoesRadioButton) {
            boolean respostaCerta = bandeirasMap.get(opcao.getText().toString()) == imagemBandeira.getTag();
            if (respostaCerta) {
                opcao.setTextColor(Color.GREEN);
            } else {
                opcao.setTextColor(Color.RED);
            }
        }
    }

    private void iterarBandeiras() {
        habilitarProximaButton(false);

        if (bandeirasIterator.hasNext()) {

            habilitarRadio(true);
            corRadioTextPadrao();

            String key = bandeirasIterator.next().toString();
            imagemBandeira.setImageResource(bandeirasMap.get(key));
            imagemBandeira.setTag(bandeirasMap.get(key));
            adicionarOpcoesRadioGroup(key);

            if (!bandeirasIterator.hasNext()){
                proximaButton.setText("Resultado");
            }
        } else {
            finish();
            startActivity(new Intent(this, ResultadoActivity.class));
        }
    }

    private void habilitarProximaButton(boolean enabled) {
        proximaButton.setEnabled(enabled);
        respostaButton.setEnabled(!enabled);
    }

    private void habilitarRadio(boolean isHabilitar) {
        for (RadioButton radio : opcoesRadioButton) {
            radio.setEnabled(isHabilitar);
        }
        if (isHabilitar)
            radioGroup.clearCheck();
    }

    private void adicionarOpcoesRadioGroup(String resposta) {
        ArrayList<String> opcoes = new ArrayList();
        Random random = new Random();
        List<String> keys = new ArrayList<String>(bandeirasMap.keySet());

        opcoes.add(resposta);

        for (int i = 1; i < opcoesRadioButton.length; i++) {
            String randomKey = keys.get(random.nextInt(keys.size()));
            if (!opcoes.contains(randomKey)) {
                opcoes.add(randomKey);
            } else {
                i--;
            }
        }

        Collections.shuffle(opcoes);

        for (int i = 0; i < opcoes.size(); i++) {
            this.opcoesRadioButton[i].setText(opcoes.get(i));
        }
    }

    private void adicionarBandeirasHashMap() {
        bandeirasMap.put("Colombia", R.drawable.colombia);
        bandeirasMap.put("India", R.drawable.india);
        bandeirasMap.put("Eslovaquia", R.drawable.eslovaquia);
        bandeirasMap.put("Australia", R.drawable.australia);
        bandeirasMap.put("Madagascar", R.drawable.madagascar);
        bandeirasMap.put("Costa do Marfim", R.drawable.costa_do_marfim);
        bandeirasMap.put("Ucrania", R.drawable.ucrania);
        bandeirasMap.put("Canada", R.drawable.canada);
        bandeirasMap.put("Republica Tcheca", R.drawable.republica_tcheca);
        bandeirasMap.put("Mexico", R.drawable.mexico);
        bandeirasMap.put("Uruguay", R.drawable.uruguay);
    }

}
