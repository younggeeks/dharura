package code.bytech.com.emergency.model.firstaid;

import android.content.Context;

/**
 * Created by samjunior on 8/23/15.
 */
public class AidItem {
    public String name;
    public String description;
    public String image;

    public int getImageResourceId(Context context){
        try {
            return context.getResources().getIdentifier(this.image,"drawable",context.getPackageName());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
