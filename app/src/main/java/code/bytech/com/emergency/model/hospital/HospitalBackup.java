package code.bytech.com.emergency.model.hospital;

import android.content.Context;

/**
 * Created by samjunior on 8/6/15.
 */
public class HospitalBackup {
    public String image;
    public String title;
    public String description;
    public String tel;

    public int getImageResourceId(Context context){

        try {
            return context.getResources().getIdentifier(this.image,"drawable",context.getPackageName());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
