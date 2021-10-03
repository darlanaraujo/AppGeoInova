package com.darlanbonfim.appgeoinova;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TelaMockups extends AppCompatActivity {

    TextView txtSugestoes;
    Button btnCardapio, btnVoltar, btnPedido;

    Animation animSobe, animZoom, animItem, animCai;
    LinearLayout layTudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_mockups);

        // Ligamento dos objetos a tela;
        layTudo = findViewById(R.id.layTudo);

        // Comandos para gerar animação na tela;
        animSobe = AnimationUtils.loadAnimation(this, R.anim.anim_subir);
        animCai = AnimationUtils.loadAnimation(this, R.anim.anim_cair);
        animZoom = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        animItem = AnimationUtils.loadAnimation(this, R.anim.anim_itens);

        layTudo.setAnimation(animItem);


        // Comando para mostrar um Toast caso o usuário clique no texto sugestões;
        txtSugestoes = findViewById(R.id.txtSugestoes);
        txtSugestoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Passe o dedo sobre a imagem arrastando para os lados!", Toast.LENGTH_LONG).show();
            }
        });

        // Comando para mostrar o PopUp Cardápio;
        btnCardapio = findViewById(R.id.btnCardapio);
        btnCardapio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Configurações para mostrar uma página dentro do Alerta.
                LayoutInflater popCardapio = LayoutInflater.from(TelaMockups.this);
                final View tela = popCardapio.inflate(R.layout.pop_cardapio, null);
                AlertDialog.Builder pop = new AlertDialog.Builder(TelaMockups.this);

                // Comando do botão dentro da tela de pop_cardapio;
                btnPedido = tela.findViewById(R.id.btnPedido);
                btnPedido.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(TelaMockups.this, "Uma nova Tela com as opções do pedido seria Aberta!", Toast.LENGTH_LONG).show();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(TelaMockups.this, "Clique fora da tela para fechar!", Toast.LENGTH_LONG).show();
                            }
                        }, 2000);

                    }
                });

                // Comando que mostra o PopUp Cardápio;
                pop.setView(tela);
                pop.show();
            }
        });

        // Configuração do botão voltar;
        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Configuração do botão Pedido;
        btnPedido = findViewById(R.id.btnPedido);
        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TelaMockups.this, "Uma nova Tela com as opções do pedido seria Aberta!", Toast.LENGTH_LONG).show();
            }
        });

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