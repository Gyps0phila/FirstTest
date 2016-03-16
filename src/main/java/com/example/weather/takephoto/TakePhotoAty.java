package com.example.weather.takephoto;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.weather.dailytest.BaseAty;
import com.example.weather.dailytest.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Gypsophila on 2016/3/16.
 */
public class TakePhotoAty extends BaseAty {

    private Button takePhoto;
    private Button choose_from_album;
    private ImageView picture;
    private Uri imageUri;
    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        takePhoto = (Button) findViewById(R.id.take_photo);
        picture = (ImageView) findViewById(R.id.picture);
        choose_from_album = (Button) findViewById(R.id.choose_from_album);
        choose_from_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * 以这种方式试验成功，但图片大的就失败显示不出来
                 * 而《第一行代码》中示例并不行
                 */
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, 3);
//                File outputImage = new File(Environment.getExternalStorageDirectory(), "tempImage.jpg");
//
//                try {
//                    if (outputImage.exists()) {
//                        outputImage.delete();
//                    }
//                    outputImage.createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                imageUri = Uri.fromFile(outputImage);

//                Intent intent = new Intent(Intent.ACTION_PICK, imageUri);
//                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                "image/*");

//                Intent intent = new Intent("android.intent.action.GET_CONTENT");
//                intent.setType("image/*");
//                intent.putExtra("crop", true);
//                intent.putExtra("scale", true);
//                Log.i("crop_photo", "调用1");
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                startActivityForResult(intent, CROP_PHOTO);
            }
        });
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建file对象，用于储存拍照后的图片
                File outputImage = new File(Environment.getExternalStorageDirectory(), "tempImage.jpg");


                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(outputImage);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                //在拍照的活动中接受到这个参数，存储拍照的图片
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO: {
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CROP_PHOTO);
                }
                break;
            }
            case CROP_PHOTO: {
                if (resultCode == RESULT_OK) {
                    try {
                        Log.i("crop_photo", "调用2");
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            case 3: {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                picture.setImageBitmap(BitmapFactory.decodeFile(picturePath));

                break;
            }
            default:
                break;

        }
    }
}
