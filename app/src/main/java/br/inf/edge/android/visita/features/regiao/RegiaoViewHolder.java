package br.inf.edge.android.visita.features.regiao;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.inf.edge.android.visita.R;

class RegiaoViewHolder extends RecyclerView.ViewHolder{
    TextView labelRegiao;
    TextView labelObservacao;

    public RegiaoViewHolder(View itemView) {
        super(itemView);

        labelRegiao = itemView.findViewById(R.id.label_regiao);
        labelObservacao = itemView.findViewById(R.id.label_observacao);
    }
}
