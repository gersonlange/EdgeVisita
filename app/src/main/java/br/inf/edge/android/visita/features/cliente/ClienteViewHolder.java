package br.inf.edge.android.visita.features.cliente;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.inf.edge.android.visita.R;

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
    public void onClick(View view)
    {
    }
}
