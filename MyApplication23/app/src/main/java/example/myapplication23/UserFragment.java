package example.myapplication23;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment
{
    View contentView;
    int REQ_OPEN_FILE = 2;

    public UserFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Permissions.check(getActivity());//检查权限
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        contentView = inflater.inflate(R.layout.fragment_user, container, false);
        Button button = (Button)contentView.findViewById(R.id.id_openfile);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openFile(v);
            }
        });
        // Inflate the layout for this fragment
        return contentView;
    }
    public void openFile(View v)
    {
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        startActivityForResult(intent, REQ_OPEN_FILE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == REQ_OPEN_FILE)
        {
            if(resultCode == RESULT_OK)
            {
                Uri mediaUri = data.getData();
                String filePath = filePathFromUri(getContext(), mediaUri);
                //加载图片
                Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                ImageView imageView = (ImageView)contentView.findViewById(R.id.id_imageview);
                imageView.setImageBitmap(bitmap);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static String filePathFromUri(Context context, Uri mediaUri)
    {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(mediaUri, projection, null, null, null);
        cursor.moveToFirst();
        int column = cursor.getColumnIndex(projection[0]);
        String imagePath = cursor.getString(column);
        return imagePath;
    }

}
