package br.inf.edge.android.visita.features.visita;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;

import br.inf.edge.android.visita.R;
import br.inf.edge.android.visita.data.Session;
import br.inf.edge.android.visita.features.cliente.ClienteAdapter;
import br.inf.edge.android.visita.model.Cliente;
import br.inf.edge.android.visita.model.Clientes;
import br.inf.edge.android.visita.model.Dados;

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