package code.bytech.com.emergency.model.hospital;

import android.util.Log;
import code.bytech.com.emergency.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samjunior on 8/6/15.
 */
public class NavItemManager {

    String hospitalArray[]={"Muhimbili","IMTU","Temeke","Mwananyamala","Hindu Mandal","Aga Khan"};
    String hospitalNumbers[]={"0714095262","0714095262","0714095262","0714095262","0714095262","0714095262"};
    String HospitalDescriptions[]={"Hospitali Ya Taifa Ya Rufaa Ya Muhimbili","Internationa Medical And Technological University ","Hospitali ya Temeke","Hospitali ya Mwananyamala","Hospitali ya Hindu Mandal"," Hospitali ya Aga Khan"};

    String[] zimamotoArray={"Fire-Bakresa","Magomeni","Namanga Station","Mbezi"};
    String[] zimamotoNumbers={"0714095262","0714095262","0714095262","0714095262"};
    String[] zimamotoDescription={"Fire-Bakresa","Magomeni Station","Namanga Station","Mbezi Station"};

    String[] polisiArray={"Fire-Bakresa","Magomeni","Namanga Station","Mbezi"};
    String[] polisiNumbers={"0714095262","0714095262","0714095262","0714095262"};
    String[] polisiDescription={"Fire-Bakresa","Magomeni Station","Namanga Station","Mbezi Station"};

    String[] umemeArray={"Ubungo","Kisarawe","Kibaha","Mbezi"};
    String[] umemeNumbers={"0714095262","0714095262","0714095262","0714095262"};
    String[] umemeDescription={"Ubungo","Kisarawe","Kibaha","Mbezi"};


    String[] aidArray={"presha","Ajali","Kuungua","Kifafa"};
    int[] images={R.drawable.presha,R.drawable.ajali,R.drawable.kuungua,R.drawable.kifafa};
    String[] aidDescription={"Huduma ya kwanza kwa mtu aliyepatwa na presha ya kupanda au ya kushuka ",
            "Huduma ya kwanza kwa ajili ya mgonjwa aliyepata ajali na mwenye majeraha ",
            "Huduma ya kwanza kwa ajili ya mgonjwa aliyepata majeraha yatokanayo na kuungua",
            "Huduma ya kwanza kwa mgonjwa aliyeanguka kifafa"};


    public static NavItemManager instance;

    private List<NavItem> hospitalItems;
    private List<NavItem> zimamotoItems;
    private List<NavItem> polisiItems;
    private List<NavItem> umemeItems;
    private List<NavItem> aidItems;

    public static NavItemManager getInstance(){
        if (instance==null){
            instance= new NavItemManager();
        }

        return instance;
    }

    public List<NavItem> getHospitals(){
        if (hospitalItems ==null){
            hospitalItems =new ArrayList<NavItem>();

            for (int i=0;i<hospitalArray.length;i++){
                NavItem navItem =new NavItem();
                navItem.title=hospitalArray[i];
                navItem.image="hospitali";
               //navItem.image=hospitalArray[i].replaceAll("\\s+","").toLowerCase();
                navItem.tel=hospitalNumbers[i];
                navItem.description=HospitalDescriptions[i];
                Log.d("Geeks",""+hospitalArray[i].replaceAll("\\s+","").toLowerCase());
                hospitalItems.add(navItem);
            }


        }
        return hospitalItems;
    }
    public List<NavItem> getZimamoto(){
        if (zimamotoItems ==null){
            zimamotoItems =new ArrayList<NavItem>();

            for (int i=0;i<zimamotoArray.length;i++){
                NavItem navItem =new NavItem();
                navItem.title=zimamotoArray[i];
                navItem.image="firefighter";
               //navItem.image=hospitalArray[i].replaceAll("\\s+","").toLowerCase();
                navItem.tel=zimamotoNumbers[i];
                navItem.description=zimamotoDescription[i];
               // Log.d("Geeks",""+hospitalArray[i].replaceAll("\\s+","").toLowerCase());
                zimamotoItems.add(navItem);
            }


        }
        return zimamotoItems;
    }

    public List<NavItem> getPolisi(){
        if (polisiItems ==null){
            polisiItems =new ArrayList<NavItem>();

            for (int i=0;i<polisiArray.length;i++){
                NavItem navItem =new NavItem();
                navItem.title=polisiArray[i];
                navItem.image="polisi";
               //navItem.image=hospitalArray[i].replaceAll("\\s+","").toLowerCase();
                navItem.tel=polisiNumbers[i];
                navItem.description=polisiDescription[i];
               // Log.d("Geeks",""+hospitalArray[i].replaceAll("\\s+","").toLowerCase());
                polisiItems.add(navItem);
            }


        }
        return polisiItems;
    }

 public List<NavItem> getAid(){
        if (aidItems ==null){
            aidItems =new ArrayList<NavItem>();

            for (int i=0;i<aidArray.length;i++){
                NavItem navItem =new NavItem();
                navItem.title=aidArray[i];
                //navItem.image="umeme";
               navItem.image=aidArray[i].replaceAll("\\s+","").toLowerCase();
                Log.d("Geeks",""+aidArray[i].replaceAll("\\s+","").toLowerCase());
                navItem.description=aidDescription[i];
               // Log.d("Geeks",""+hospitalArray[i].replaceAll("\\s+","").toLowerCase());
                aidItems.add(navItem);
            }


        }
        return aidItems;
    }
    public List<NavItem> getUmeme(){
        if (umemeItems ==null){
            umemeItems =new ArrayList<NavItem>();

            for (int i=0;i<umemeArray.length;i++){
                NavItem navItem =new NavItem();
                navItem.title=umemeArray[i];
                navItem.image="umeme";
               //navItem.image=hospitalArray[i].replaceAll("\\s+","").toLowerCase();
                navItem.tel=umemeNumbers[i];
                navItem.description=umemeDescription[i];
               // Log.d("Geeks",""+hospitalArray[i].replaceAll("\\s+","").toLowerCase());
                umemeItems.add(navItem);
            }


        }
        return umemeItems;
    }


}
