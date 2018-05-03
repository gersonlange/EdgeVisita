package br.inf.edge.suporte.visita.features.visita;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.inf.edge.suporte.visita.R;
import br.inf.edge.suporte.visita.dao.DadosDAO;
import br.inf.edge.suporte.visita.dao.VisitaDAO;
import br.inf.edge.suporte.visita.model.Cliente;

public class VisitaActivity extends AppCompatActivity {

    TextView edtCliente, edtObservacao;
    Button btnInicia, btnTermina;

    Cliente cliente;
    VisitaDAO dao;

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
                inicia();
            }
        });

        edtObservacao = findViewById(R.id.text_observacao);

        btnTermina = findViewById(R.id.btn_termina);
        btnTermina.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View view) {
                finaliza();
            }
        });

        dao = new VisitaDAO();

        habilita(false);

        setCliente( EventBus.getDefault().getStickyEvent(Integer.class) );
    }

    private void habilita(boolean b) {
        btnInicia.setEnabled(! b);
        edtObservacao.setEnabled(b);
        btnTermina.setEnabled(b);
    }

    private void inicia() {
        habilita(true);

        Date date = new Date();

        String data = new SimpleDateFormat("dd/MM/yyyy").format(date);
        String hora = new SimpleDateFormat("HH:mm").format(date);

        dao.insert(cliente.getCodigo(), cliente.getCodigoRegiao(), data, hora);
     }

    private void finaliza() {
        String hora = new SimpleDateFormat("HH:mm").format(new Date());

        dao.fecha(cliente.getCodigo(), "", "",
                edtObservacao.getText().toString(),
                hora);

        finish();
    }

    public void setCliente(Integer codigoCliente) {

        cliente = new DadosDAO().getCliente( codigoCliente );

        edtCliente.setText( cliente.getNome() + "\n" + cliente.getEndereco());
    }
}