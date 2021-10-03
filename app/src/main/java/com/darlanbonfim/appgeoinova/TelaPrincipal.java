package com.darlanbonfim.appgeoinova;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class TelaPrincipal extends AppCompatActivity {

    Intent intent;
    Button btnMockups, btnForms, btnListarCadastro, btnDesafio, btnVideo;

    Animation animSobe, animZoom, animItem, animCai;
    ImageView imgFundo, imgLogo, imgMapa, imgLocalizacao;
    LinearLayout layMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        setStatusBarColor(findViewById(R.id.statusBarBackground),getResources().getColor(R.color.white));

        // Comandos para ligar os objetos aos itens da tela;
        imgFundo = findViewById(R.id.imgFundo);
        imgLogo = findViewById(R.id.imgLogo);
        imgMapa = findViewById(R.id.imgMapa);
        imgLocalizacao = findViewById(R.id.imgLocalizacao);
        layMenu = findViewById(R.id.layMenu);

        // Comandos para gerar animação na tela;
        animSobe = AnimationUtils.loadAnimation(this, R.anim.anim_subir);
        animCai = AnimationUtils.loadAnimation(this, R.anim.anim_cair);
        animZoom = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        animItem = AnimationUtils.loadAnimation(this, R.anim.anim_itens);

        //imgFundo.setAnimation(animCai);
        imgLogo.setAnimation(animCai);
        imgMapa.setAnimation(animCai);
        imgLocalizacao.setAnimation(animZoom);
        layMenu.setAnimation(animItem);

        // Configuração do click na logo para chamar a tela de apresentação;
        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        // Configuração do botão que chama a tela Mockups;
        btnMockups = findViewById(R.id.btnMockups);
        btnMockups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), TelaMockups.class);
                startActivity(intent);
            }
        });

        // Configuração do botão que chama a tela Formulários;
        btnForms = findViewById(R.id.btnForms);
        btnForms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), TelaFormulario.class);
                startActivity(intent);
            }
        });

        // Configuração do botão que chama a tela de Lista de Cadastro;
        btnListarCadastro = findViewById(R.id.btnListarCadastro);
        btnListarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), TelaListarCadastro.class);
                startActivity(intent);
            }
        });

        // Configurando o botão que chama a tela de Desafio do Alfabeto;
        btnDesafio = findViewById(R.id.btnDesafio);
        btnDesafio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), TelaDesafio.class);
                startActivity(intent);
            }
        });

        // Configurando o botão que chama a tela de Video;
        btnVideo = findViewById(R.id.btnVideo);
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), TelaVideo.class);
                startActivity(intent);
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

    public void setStatusBarColor(View statusBar,int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //status bar height
            int actionBarHeight = getActionBarHeight();
            int statusBarHeight = getStatusBarHeight();
            //action bar height
            statusBar.getLayoutParams().height = actionBarHeight + statusBarHeight;
            statusBar.setBackgroundColor(color);
        }
    }

    public int getActionBarHeight() {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}