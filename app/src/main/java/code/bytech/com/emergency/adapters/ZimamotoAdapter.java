package code.bytech.com.emergency.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import code.bytech.com.emergency.R;
import code.bytech.com.emergency.model.hospital.NavItem;

import java.util.List;

/**
 * Created by samjunior on 8/22/15.
 */
public class ZimamotoAdapter extends RecyclerView.Adapter<ZimamotoAdapter.ViewHolder> {

    private List<NavItem> navItems;
    private int rowItem;
    private Context context;
    public ZimamotoAdapter(List<NavItem> navItems, int rowItem, Context context) {
        this.navItems = navItems;
        this.rowItem = rowItem;
        this.context = context;
    }

    public ZimamotoAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(rowItem,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        NavItem navItem = navItems.get(i);
        viewHolder.title.setText(navItem.title);
        viewHolder.description.setText(navItem.description);

        //   viewHolder.imageView.setImageDrawable(context.getResources().getDrawable(navItem.getImageResourceId(context)));
        viewHolder.imageView.setImageDrawable(ContextCompat.getDrawable(context, navItem.getImageResourceId(context)));
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
            title=(TextView)itemView.findViewById(R.id.hospitalName);
            imageView=(ImageView)itemView.findViewById(R.id.hospitalImage);
            description=(TextView)itemView.findViewById(R.id.description);
            imageView.setOnClickListener(this);
            itemView.setOnClickListener(this);
            title.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            Intent callIntent= new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + navItems.get(getAdapterPosition()).tel));
            context.startActivity(callIntent);
        }

    }
}
