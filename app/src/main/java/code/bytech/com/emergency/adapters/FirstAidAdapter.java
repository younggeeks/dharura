package code.bytech.com.emergency.adapters;

import android.content.Context;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import code.bytech.com.emergency.R;
import code.bytech.com.emergency.events.DrawerItemClicked;
import code.bytech.com.emergency.events.FirstAidItemClick;
import code.bytech.com.emergency.model.hospital.NavItem;
import code.bytech.com.emergency.utils.EventBus;

import java.util.List;

/**
 * Created by samjunior on 8/23/15.
 */
public class FirstAidAdapter extends RecyclerView.Adapter<FirstAidAdapter.ViewHolder> {

    private List<NavItem> navItems;
    private int rowLayout;
    private Context context;

    public FirstAidAdapter(List<NavItem> navItems, int rowLayout, Context context) {
        this.navItems = navItems;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NavItem item=navItems.get(position);

        holder.description.setText(item.description);
        holder.title.setText(item.title);
        holder.imageView.setImageDrawable(ContextCompat.getDrawable(context,item.getImageResourceId(context)));
    }

    @Override
    public int getItemCount() {
        return navItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private ImageView imageView;
        private TextView description;

        public ViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.title);
            imageView=(ImageView)itemView.findViewById(R.id.img_thumbnail);
            description=(TextView)itemView.findViewById(R.id.description);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            EventBus.getInstance().post(new FirstAidItemClick((String)title.getText()));
        }
    }
}
