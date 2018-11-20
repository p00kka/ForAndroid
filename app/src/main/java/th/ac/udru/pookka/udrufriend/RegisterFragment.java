package th.ac.udru.pookka.udrufriend;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

//    Explicit

    private Uri uri;
    private ImageView imageView;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        create toolbar
        createToolbar();

//        avatar controller
        avatarController();

    }   // main Method

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        resultcode is either true or false
        if (resultCode == getActivity().RESULT_OK  ) {

            uri = data.getData();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getActivity()
                                .getContentResolver()
                                .openInputStream(uri)); //draw image, large, used up lots of memories
                Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 800,600,false);
                //scale image into another image
                imageView.setImageBitmap(bitmap1);

            } catch (Exception e) {
                e.printStackTrace(); //
            }

        } else {
            Toast.makeText(getActivity(), "Please choose image", Toast.LENGTH_SHORT).show();

        }


    }   // Result

    private void avatarController() {
        imageView = getView().findViewById(R.id.imageViewAvartar);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Please select 5 pics"),5);
            }
        });
    } // open app to choose pic

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.register);
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(R.string.title_have_space);
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

}
