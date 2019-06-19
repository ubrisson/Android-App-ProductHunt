package com.example.ebm.comments;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebm.R;
import com.example.ebm.comments.models.Comment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ItemViewHolder> {
    /* La liste à afficher */
    private ArrayList<Comment> lesComments;

    public CommentsAdapter(ArrayList<Comment> lesComments) {
        this.lesComments = lesComments;
    }

    @NonNull
    @Override
    public CommentsAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentsAdapter.ItemViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.comment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.ItemViewHolder holder, int position) {
        holder.bind(lesComments.get(position));
    }

    @Override
    public int getItemCount() {
        return lesComments.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout commentLayout;
        TextView name_tag;
        TextView headline;
        ImageView userPic;
        TextView body;
        TextView commentDate;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name_tag = itemView.findViewById(R.id.username_twitter_tag);
            headline = itemView.findViewById(R.id.headline);
            userPic = itemView.findViewById(R.id.user_image);
            body = itemView.findViewById(R.id.body);
            commentDate = itemView.findViewById(R.id.com_date);
            commentLayout = itemView.findViewById(R.id.commentLayout);
        }

        void bind(Comment comment) {
            name_tag.setText(comment.getUserNameTag());
            headline.setText(comment.getUserHeadline());
            body.setText(comment.getBody());
            commentDate.setText(comment.getCreated_at());
            commentLayout.setPadding(32*comment.getDepth(),0,0,0);
            String url = comment.getUserPic();
            Picasso.get().load(url).transform(new RoundedTransformation(100, 0)).fit().into(userPic);
        }

        public class RoundedTransformation implements com.squareup.picasso.Transformation {
            private final int radius;
            private final int margin;

            public RoundedTransformation(final int radius, final int margin) {
                this.radius = radius;
                this.margin = margin;
            }

            @Override
            public Bitmap transform(final Bitmap source) {
                final Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP,
                        Shader.TileMode.CLAMP));

                Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(),
                        Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(output);
                canvas.drawRoundRect(new RectF(margin, margin, source.getWidth() - margin,
                        source.getHeight() - margin), radius, radius, paint);

                if (source != output) {
                    source.recycle();
                }
                return output;
            }

            @Override
            public String key() {
                return "rounded(r=" + radius + ", m=" + margin + ")";
            }
        }
    }
}
