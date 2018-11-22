package th.ac.udru.pookka.udrufriend;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceFragment extends Fragment {


    public ServiceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create Recyclerview
        createRecyclerview();

    }

    private void createRecyclerview() {
        RecyclerView recyclerView = getView().findViewById(R.id.recycleViewFriend);
        final String tag = "22noV1";

        final int[] countInts = new int[]{0};

        final ArrayList<String> displayNameArrayList = new ArrayList<>();
        final ArrayList<String> urlAvatarArrayList = new ArrayList<>();

//        get value from firebase

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("User");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                int i = (int) dataSnapshot.getChildrenCount();
                Log.d(tag, "number of user =====>" + i);

                ArrayList<DatabaseModel> databaseModelArrayList = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    DatabaseModel databaseModel = dataSnapshot1.getValue(DatabaseModel.class);
                    databaseModelArrayList.add(databaseModel);

                    DatabaseModel databaseModel1 = databaseModelArrayList.get(countInts[0]);
                    countInts[0] += 1;
                    displayNameArrayList.add(databaseModel1.getNameString());
                    urlAvatarArrayList.add(databaseModel1.getPathUrlString());
                }

                Log.d(tag, "displaynamearraylist ===>" + displayNameArrayList.toString());
                Log.d(tag, "urlAvatarArraylist ====>" + urlAvatarArrayList.toString());

            } // on data change

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }   // create recyclerview method


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_, container, false);
    }

}
