package com.darlanbonfim.appgeoinova;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelaFormulario extends AppCompatActivity {

    Button btnSalvar, btnVoltar;
    EditText txtNome, txtNascimento, txtEmail, txtSexo;

    Animation animSobe, animZoom, animItem, animCai;
    ImageView imgBanner;
    LinearLayout layForms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_formulario);

        // Ligamento dos objetos a tela;
        txtNome = findViewById(R.id.txtNome);
        txtNascimento = findViewById(R.id.txtNascimento);
        txtEmail = findViewById(R.id.txtEmail);
        txtSexo = findViewById(R.id.txtSexo);
        imgBanner = findViewById(R.id.imgBanner);
        layForms = findViewById(R.id.layForms);

        // Comandos para gerar animação na tela;
        animSobe = AnimationUtils.loadAnimation(this, R.anim.anim_subir);
        animCai = AnimationUtils.loadAnimation(this, R.anim.anim_cair);
        animZoom = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        animItem = AnimationUtils.loadAnimation(this, R.anim.anim_itens);

        layForms.setAnimation(animItem);
        imgBanner.setAnimation(animZoom);

        // Configuração do botão Salvar;
        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Comando para verificar se todos os campos foram preenchidos;

                // Variavel que recebe um valor logico para ser testada nas condições;
                boolean confereDados = true;

                // Se algum campo estiver vazio, gera uma mensagem de erro no campo especifico;
                if(txtNome.getText().toString().trim().isEmpty()){
                    confereDados = false;
                    txtNome.setError(getString(R.string.txt_opss));
                    Toast.makeText(getApplicationContext(), "É necessário preencher todos os campos!", Toast.LENGTH_LONG).show();

                } else if(txtNascimento.getText().toString().trim().isEmpty()){
                    confereDados = false;
                    txtNascimento.setError(getString(R.string.txt_opss));
                    Toast.makeText(getApplicationContext(), "É necessário preencher todos os campos!", Toast.LENGTH_LONG).show();

                } else if(txtEmail.getText().toString().trim().isEmpty()){
                    confereDados = false;
                    txtEmail.setError(getString(R.string.txt_opss));
                    Toast.makeText(getApplicationContext(), "É necessário preencher todos os campos!", Toast.LENGTH_LONG).show();

                } else if(txtSexo.getText().toString().trim().isEmpty()){
                    confereDados = false;
                    txtSexo.setError(getString(R.string.txt_opss));
                    Toast.makeText(getApplicationContext(), "É necessário preencher todos os campos!", Toast.LENGTH_LONG).show();

                } else if(! validaEmail(txtEmail.getText().toString())){ // Esse comando verifica se o e-mail digitado é válido;
                    confereDados = false;
                    txtEmail.setError("Email inválido!");
                    Toast.makeText(getApplicationContext(), "Opss! Dados inválidos!", Toast.LENGTH_LONG).show();

                } else if(! validaDados(txtSexo.getText().toString().toUpperCase(), "[MF]+")){ // Esse campo verifica se foi digitado apenas M ou F;
                    confereDados = false;
                    txtSexo.setError("Erro! Digite apenas [M ou F]");
                    Toast.makeText(getApplicationContext(), "Opss! Dados inválidos!", Toast.LENGTH_LONG).show();

                } else if(confereDados){ // Se todos os dados estiverem Ok...;

                    // Instanciamento da classe Pessoas para salvar os dados;
                    Pessoas p = new Pessoas();

                    p.setNome(txtNome.getText().toString());
                    p.setNascimento(txtNascimento.getText().toString());
                    p.setEmail(txtEmail.getText().toString());
                    p.setSexo(txtSexo.getText().charAt(0));

                    // Aqui seria execultado os endpoints;
                    Intent endpoint1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://geoinova.com.br/teste/repositorio.php"));
                    //startActivity(endpoint1);
                    Toast.makeText(getApplicationContext(), "As informações foram salvas no repositório!", Toast.LENGTH_SHORT).show();

                    // Utilização de um recurso para gerar um tempo retardante antes de mostrar as mensagens;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent endpoint2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://geoinova.com.br/teste/cache.php"));
                            //startActivity(endpoint2);
                            Toast.makeText(getApplicationContext(), "As informações foram salvas no cache!", Toast.LENGTH_SHORT).show();

                            // Outra chamada de tempo!
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent endpoint3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://geoinova.com.br/teste/fila.php"));
                                    //startActivity(endpoint3);
                                    Toast.makeText(getApplicationContext(), "As informações foram salvas na fila!", Toast.LENGTH_SHORT).show();

                                    // Outra chamada de tempo! Aqui os campos de textos são limpos e é gerado a msg de salvamento;
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            limpaTela();
                                            Toast.makeText(getApplicationContext(), getString(R.string.txt_salvo), Toast.LENGTH_LONG).show();
                                        }
                                    }, 3000);
                                }
                            },2000);

                        }
                    },2000);
                }
            }
        });

        // Configuração do botão Voltar;
        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void limpaTela(){
        txtNome.setText("");
        txtNascimento.setText("");
        txtEmail.setText("");
        txtSexo.setText("");
    }


    public static boolean validaEmail(String email) {
        boolean verificaEmail = false;
        if (email != null && email.length() > 0) {
            String texto = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(texto, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                verificaEmail = true;
            }
        }
        return verificaEmail;
    }

    public boolean validaDados(String dados, String txt){
        if(dados.matches(txt)){
            return true;
        } else {
            return false;
        }
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