package com.example.ebm.collections_feature;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebm.R;
import com.example.ebm.modele.CollecPH;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CollectionsAdapter extends RecyclerView.Adapter<CollectionsAdapter.ItemViewHolder> {

    /* La liste à afficher */
    private List<CollecPH> lesCollections;
    /* Écouteur d'évènement */
    private onClickCollecListener listener;

    public CollectionsAdapter(List<CollecPH> lesCollections, onClickCollecListener clickCollecListener) {
        this.lesCollections = lesCollections;
        this.listener = clickCollecListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.collection,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(lesCollections.get(position));
    }

    @Override
    public int getItemCount() {
        return lesCollections.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView subtitle;
        ImageView backgroundImage;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
            backgroundImage = itemView.findViewById(R.id.bg_image);
            backgroundImage.setOnClickListener(this);
        }

        void bind(CollecPH collecPH) {
            title.setText(collecPH.getName());
            subtitle.setText(collecPH.getTitle());
            String url = collecPH.getBackground_image_url();
            Picasso.get().load(url).fit().into(backgroundImage);
        }

        @Override
        public void onClick(View v) {
            listener.clickCollec(getAdapterPosition());
        }
    }


    /**
     * Interface permettant de gérer le clic sur un élément
     */
    public interface onClickCollecListener{
        void clickCollec(int position);
    }
}
