package code.bytech.com.emergency.model.hospital;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samjunior on 8/6/15.
 */
public class HospitalManagerBackup {

    String hospitalArray[]={"Muhimbili","IMTU","Temeke","Mwananyamala","Hindu Mandal","Aga Khan"};
    String numbers[]={"0714095262","0714095262","0714095262","0714095262","0714095262","0714095262"};
    String descriptions[]={"Hospitali Ya Taifa Ya Rufaa Ya Muhimbili","Internationa Medical And Technological University ","Hospitali ya Temeke","Hospitali ya Mwananyamala","Hospitali ya Hindu Mandal"," Hospitali ya Aga Khan"};


    public static HospitalManagerBackup instance;

    private List<NavItem> navItems;

    public static HospitalManagerBackup getInstance(){
        if (instance==null){
            instance= new HospitalManagerBackup();
        }

        return instance;
    }

    public List<NavItem> getNavItems(){
        if (navItems ==null){
            navItems =new ArrayList<NavItem>();

            for (int i=0;i<hospitalArray.length;i++){
                NavItem navItem =new NavItem();
                navItem.title=hospitalArray[i];
                navItem.image="navItem";
               //navItem.image=hospitalArray[i].replaceAll("\\s+","").toLowerCase();
                navItem.tel=numbers[i];
                navItem.description=descriptions[i];
                Log.d("Geeks",""+hospitalArray[i].replaceAll("\\s+","").toLowerCase());
                navItems.add(navItem);
            }


        }
        return navItems;
    }


}
