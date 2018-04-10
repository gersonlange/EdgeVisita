package br.inf.edge.suporte.visita.features.visita;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.inf.edge.suporte.visita.R;
import br.inf.edge.suporte.visita.model.Cliente;

public class VisitaActivity extends AppCompatActivity {

    TextView edtCliente, edtObservacao;
    Button btnInicia, btnTermina;

    Cliente dadosCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visita);

        setTitle(getString(R.string.titulo_visita));

        edtCliente = findViewById(R.id.nome_cliente);

        btnInicia = findViewById(R.id.btn_inicia);
        btnInicia.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dadosCliente.setDataInicio(new Date());

                inicia(true);
            }
        });

        edtObservacao = findViewById(R.id.text_observacao);

        btnTermina = findViewById(R.id.btn_termina);
        btnTermina.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        inicia(false);
    }

    private void inicia(boolean b) {
        btnInicia.setEnabled(! b);
        edtObservacao.setEnabled(b);
        btnTermina.setEnabled(b);
    }
}