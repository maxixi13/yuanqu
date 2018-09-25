package com.example.maxixi.yuanqu.personal;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.maxixi.yuanqu.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class renzhengqiye extends AppCompatActivity {

    private static final int TAKE_PHOTO = 1;
    private ImageView picture;
    private Uri imageUri;
    private static final int CHOSSE_PHOTO=2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dctivity_renzhengqiye);

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
    }


    private void Dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        final View view = View.inflate(this, R.layout.fctivity_photo_dialog, null);
        dialog.setView(view, 0, 0, 0, 0);

        Button camera = (Button) view.findViewById(R.id.photo_camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                //启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
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
        dialog.show();//显示对话框
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
                    }
                }
                break;
            default:
                break;
        }
    }

    private void openAlbum(){
        Intent intent=new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOSSE_PHOTO);
    }

    public void onRequestPermiddionsResult(int requestCode,String[] permiddions,int[] grantResults){
        switch (requestCode){
            case 1:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else {
                    Toast.makeText(this,"You denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;default:
        }
    }


    @TargetApi(19)
    private void handleImageOnKit(Intent data){
        String imagePath=null;
        Uri uri=data.getData();
        if (DocumentsContract.isDocumentUri(this,uri)){
            //如果是document类型的Uri，则通过doCument id处理
            String docId=DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id=docId.split(":")[1];//解析出数字格式的id
                String selection=MediaStore.Images.Media._ID+"="+id;
                imagePath = getImagePath(MediaStore.Image.Media.EXTERNAL_CONTENT_URI,selection);
            }
        }

    }



}
