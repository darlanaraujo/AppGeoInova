package com.darlanbonfim.appgeoinova;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaDesafio extends AppCompatActivity {

    Button btnVerificar, btnLimpar, btnVoltar;
    EditText txtTexto;

    String texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_desafio);

        // Configuração do botão que Verifica o Texto;
        btnVerificar = findViewById(R.id.btnVerificar);
        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtTexto = findViewById(R.id.txtTexto);
                if(txtTexto.getText().toString().trim().isEmpty()){

                    txtTexto.setError(getString(R.string.txt_opss));
                    Toast.makeText(TelaDesafio.this, "É necessário preencher o campo do Texto!", Toast.LENGTH_LONG).show();

                } else {
                    texto = txtTexto.getText().toString().toUpperCase();

                    if(verificaTexto(texto)){
                        Toast.makeText(TelaDesafio.this, "Parabéns! O seu texto tem todas as letras do alfabeto!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(TelaDesafio.this, "O texto digitado NÃO contêm todas as letras do alfabeto!", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        // Configuração do botão que Limpa o texto;
        btnLimpar = findViewById(R.id.btnLimpar);
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpaTela();
            }
        });

        // Configuração do botão que Volta a tela principal;
        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public boolean verificaTexto(String texto){
        String palavra;
        boolean status = false;

        palavra = texto.replaceAll("[^A-Z]", ""); // Elimina qualquer numero ou acentuação;
        palavra = palavra.replaceAll("(.)(?=.*\\1)", ""); // Elimina letras duplicadas;

        if(palavra.length() < 26){
            status = false;
        } else if(palavra.length() == 26){
            status = true;
        }

        return status;
    }

    public void limpaTela(){
        txtTexto.setText("");
    }

    /** Método que oculta a barra de navegação no aparelho;
     * Esse comando é chamada no método onResume() por ser resposável por execultar o comando no
     * momento é que a classe é chamada.
     * A barra de navegação é oculta, mas pode ser acessada passando o dedo na tela de baixo para
     * cima. Ela fica visivel por alguns segundos e volta a ocultar-se.
     */
    @Override
    protected void onResume() {
        super.onResume();

        // Comandoqueocultaabarradenavegação;
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // Ocultaabarrainferior
                // | View.SYSTEM_UI_FLAG_FULLSCREEN // Ocultaabarrasuperior;
                //|View.SYSTEM_UI_FLAG_IMMERSIVE // Faz a barra inferior aparecer permanete se passar o dedo debaixo para cima na tela;
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY; // Faz a barra inferior aparecer por algum tempo se passar o dedo debaixo para cima na tela;

        decorView.setSystemUiVisibility(uiOptions);
    }

}