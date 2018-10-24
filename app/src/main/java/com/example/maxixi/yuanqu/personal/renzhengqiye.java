package com.example.maxixi.yuanqu.personal;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import okhttp3.OkHttpClient;


public class renzhengqiye extends AppCompatActivity {

    private static final int TAKE_PHOTO = 1;
    private ImageView picture;
    private Uri imageUri;
    private AlertDialog dialog;
    private static final int CHOSSE_PHOTO=2;
    private TextView qiyemingcheng;
    private TextView xingming;
    private TextView lianxifangshi;
    private TextView xinyongdaima;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dctivity_renzhengqiye);

        //view
        qiyemingcheng = (TextView)findViewById(R.id.personal_renzheng_qiyemingcheng_text);
        xingming = (TextView)findViewById(R.id.personal_renzheng_xingming_text);
        lianxifangshi = (TextView)findViewById(R.id.personal_renzheng_lianxifangshi_text);
        xinyongdaima = (TextView)findViewById(R.id.personal_renzheng_xinyongdaima_text);

        Toolbar toolbar = (Toolbar) findViewById(R.id.personal_renzhengqiye_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        picture = (ImageView) findViewById(R.id.personal_renzheng_upimgview);
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog();

            }
        });

        Button tijiao=(Button)findViewById(R.id.personal_renzheng_tijiao_button);
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOkhttp();
            }
        });
    }

    private void sendOkhttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient=new OkHttpClient();
                
            }
        }).start();
    }


    private void Dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        dialog = builder.create();
        final View view = View.inflate(this, R.layout.fctivity_photo_dialog, null);
        dialog.setView(view, 0, 0, 0, 0);

        final Button camera = (Button) view.findViewById(R.id.photo_camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            camera_take_photo();
            }
        });

        Button album = (Button) view.findViewById(R.id.photo_album);
        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(renzhengqiye.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(renzhengqiye.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    openAlbum();
                }

            }
        });

        Button cancel = (Button) view.findViewById(R.id.photo_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();//显示对话框
    }



    private void camera_take_photo(){
        //创建File，用于储存拍照后的图片
        File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(renzhengqiye.this, "com.example.cameraalbumtest.fileprovider", outputImage);
        } else {
            imageUri = Uri.fromFile(outputImage);
        }
        if (ContextCompat.checkSelfPermission(renzhengqiye.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) renzhengqiye.this, new String[]{Manifest.permission.CAMERA}, TAKE_PHOTO);
            Toast.makeText(this,"请打开相机权限",Toast.LENGTH_SHORT).show();
        } else {
            //启动相机程序
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //指定调用相机拍照后照片的储存路径
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, TAKE_PHOTO);
            dialog.dismiss();
        }
    }


    private void openAlbum(){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,CHOSSE_PHOTO);//打开相册
        dialog.dismiss();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permiddions,int[] grantResults){
        switch (requestCode){
            case 1:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else {
                    Toast.makeText(this,"您拒绝了相册访问",Toast.LENGTH_SHORT).show();
                }
                break;default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        //拍摄照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            case CHOSSE_PHOTO:
                if (resultCode==RESULT_OK){
                    //判断手机版本号
                    if (Build.VERSION.SDK_INT>=19){
                        //4.4及以上系统使用这个方法处理图片
                        handleImageOnKit(data);
                    }else {
                        //4.4以下系统使用这个方式处理图片；
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }


    @TargetApi(19)
    private void handleImageOnKit(Intent data){
        String imagePath=null;
        Uri uri=data.getData();
        if (DocumentsContract.isDocumentUri(this,uri)){
            //如果是document类型的Uri，则通过doCument id处理
            String docId= DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id=docId.split(":")[1];//解析出数字格式的id
                String selection=MediaStore.Images.Media._ID+"="+id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri= ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath=getImagePath(contentUri,null);
            }
        }else if ("content".equalsIgnoreCase(uri.getScheme())){
            //如果是content 类型的Uri，则使用普通方式处理
            imagePath=getImagePath(uri,null);
        }else if ("file".equalsIgnoreCase(uri.getScheme())){
            //如果是file类型的Uri，直接获取图片路径即可
            imagePath=uri.getPath();
        }
        displayImage(imagePath);//根据图片路径显示图片

    }

    private void handleImageBeforeKitKat(Intent data){
        Uri uri=data.getData();
        String imagePath=getImagePath(uri,null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri,String selection){
        String path=null;
        //通过Uri和selection来获取真是的图片路径
        Cursor cursor=getContentResolver().query(uri,null,selection,null,null);
        if (cursor!=null){
            if (cursor.moveToFirst()){
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath){
        if (imagePath!=null){
            Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        }else{
            Toast.makeText(this,"获取图片失败",Toast.LENGTH_SHORT).show();
        }
    }



}
