package th.ac.udru.pookka.udrufriend;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendAdaptor extends RecyclerView.Adapter<FriendAdaptor.FriendViewHolder>{

    private Context context;
    private ArrayList<String> displayNamestringArrayList, pathURLStringArrayList;
    private LayoutInflater layoutInflater;

    public FriendAdaptor(Context context,
                         ArrayList<String> displayNamestringArrayList,
                         ArrayList<String> pathURLStringArrayList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.displayNamestringArrayList = displayNamestringArrayList;
        this.pathURLStringArrayList = pathURLStringArrayList;
    } //constructor

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = layoutInflater.inflate(R.layout.recycler_friend, viewGroup, false);
        FriendViewHolder friendViewHolder = new FriendViewHolder(view);

        return friendViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder friendViewHolder, int i) {

        String urlPathString = pathURLStringArrayList.get(i);
        String displayNameString = displayNamestringArrayList.get(i);

        friendViewHolder.textView.setText(displayNameString);
        Picasso.get().load(urlPathString).into(friendViewHolder.circleImageView);
    }

    @Override
    public int getItemCount() {
        return displayNamestringArrayList.size();
    }

    public class FriendViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView circleImageView;
        private TextView textView;


        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.circleImageFriend);
            textView = itemView.findViewById(R.id.displayName);


        }
    }   // friend view holder class


}
