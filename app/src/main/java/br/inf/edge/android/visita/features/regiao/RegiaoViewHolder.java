package br.inf.edge.android.visita.features.regiao;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import br.inf.edge.android.visita.R;

class RegiaoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView labelRegiao;
    TextView labelObservacao;
    TextView labelCodigo;

    public RegiaoViewHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);

        labelRegiao = itemView.findViewById(R.id.label_regiao);
        labelObservacao = itemView.findViewById(R.id.label_observacao);
        labelCodigo = itemView.findViewById(R.id.label_codigo);
    }

    @Override
    public void onClick(View view)
    {
        Log.d("DEBUG", "ENTROU");
    }
}
