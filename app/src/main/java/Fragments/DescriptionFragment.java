package Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Info.CommonInfo;
import Info.CulturalInfo;
import Info.SportsInfo;
import Info.TechnicalInfo;
import ac.nita.advaitam4.Events;
import ac.nita.advaitam4.MainActivity;
import ac.nita.advaitam4.R;
import ac.nita.advaitam4.TabbedActivity;

/**
 * Created by HP on 06-08-2017.
 */
public class DescriptionFragment extends Fragment {

    private String desc;
    private String name;
    private String date;
    private String time;

    String idName;

    public DescriptionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	/* *
	* 1. Inflate the xml
	* 2. Setup the UI
	* 3. Return the rootView
	* */

        idName=getArguments().getString("KEY");
        Log.d("mylog","Idname "+idName);
        View view=inflater.inflate(R.layout.fragment_description, container, false);

        char EventType=idName.charAt(0);
        int EventId=Character.getNumericValue(idName.charAt(idName.length()-1));

        /*
        if(EventType=='s'){
            desc= SportsInfo.sInfo[EventId-1].getDesc();
            name= SportsInfo.sInfo[EventId-1].getName();
            date= SportsInfo.sInfo[EventId-1].getDate();
            time= SportsInfo.sInfo[EventId-1].getTime();
        }
        else if(EventType=='c'){
            desc= CulturalInfo.cInfo[EventId-1].getDesc();
            name= CulturalInfo.cInfo[EventId-1].getName();
            date= CulturalInfo.cInfo[EventId-1].getDate();
            time= CulturalInfo.cInfo[EventId-1].getTime();
        }
        else if(EventType=='t'){
            desc= TechnicalInfo.tInfo[EventId-1].getDesc();
            name= TechnicalInfo.tInfo[EventId-1].getName();
            date= TechnicalInfo.tInfo[EventId-1].getDate();
            time= TechnicalInfo.tInfo[EventId-1].getTime();
        }

*/



        TextView tv=(TextView)view.findViewById(R.id.name);
        tv.setText("EVENT NAME: " + name);
        tv=(TextView)view.findViewById(R.id.desc);
        tv.setText(desc);
        tv=(TextView)view.findViewById(R.id.time);
        tv.setText("TIME: " + time);
        tv=(TextView)view.findViewById(R.id.date);
        tv.setText("DATE: " + date);

        ImageView iv=view.findViewById(R.id.img);
        int resID = getResources().getIdentifier(idName , "drawable", Events.PACKAGE_NAME);
        iv.setImageResource(resID);

        //return inflater.inflate(R.layout.fragment_description, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        final View view = v;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference("data/events/"+idName);



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {



                    CommonInfo commonInfo = dataSnapshot.getValue(CommonInfo.class);
                    desc= commonInfo.getDesc();
                    name= commonInfo.getName();
                    date= commonInfo.getDate();
                    time= commonInfo.getTime();

                    Log.d("mylog","Strings "+desc+name+date+time);

                    TextView tv=(TextView)view.findViewById(R.id.name);
                    tv.setText("EVENT NAME: \n" + name);
                    tv=(TextView)view.findViewById(R.id.desc);
                    tv.setText(desc);
                    tv=(TextView)view.findViewById(R.id.time);
                    tv.setText("TIME: " + time);
                    tv=(TextView)view.findViewById(R.id.date);
                    tv.setText("DATE: " + date);



                } catch (Exception e){
                    e=e;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }



}
