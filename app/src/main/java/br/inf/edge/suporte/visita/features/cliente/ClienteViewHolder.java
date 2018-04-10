package br.inf.edge.suporte.visita.features.cliente;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.inf.edge.suporte.visita.R;
import br.inf.edge.suporte.visita.features.visita.VisitaActivity;

class ClienteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView labelCliente;
    TextView labelObservacao;

    public ClienteViewHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);

        labelCliente = itemView.findViewById(R.id.label_cliente);
        labelObservacao = itemView.findViewById(R.id.label_observacao);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), VisitaActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        view.getContext().startActivity(intent);
    }
}
