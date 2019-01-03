package com.example.user.dreamlist2018;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class Dialog1 extends AppCompatDialogFragment {

    private int IMAGE_GALLERY_REQUEST = 100;

    EditText inputDream;
    //EditText inputDetails;
    EditText inputDeadline;
    RadioGroup radioGroup;
    Button btAddDream;
    Button pickImage;
    ImageView image;
    byte[] imageByte;

    ConstraintLayout layout;

    String mode = "insert";
    int dreamId;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.f_reg_1, null);

        layout = view.findViewById(R.id.layout);

        Bundle bundle = getArguments();

        try{
            dreamId = bundle.getInt("dreamId", 0);
        }catch (NullPointerException e){
            dreamId = 0;
        }

        if(dreamId > 0){
            mode = "edit";
        }

        view = setTouchListenerToView(view);

         //setView
        inputDream = view.findViewById(R.id.input_dream);
        //inputDetails = view.findViewById(R.id.input_description);
        inputDeadline = view.findViewById(R.id.input_deadline);
        radioGroup = view.findViewById(R.id.radio_group);
        btAddDream = view.findViewById(R.id.bt_add);
        pickImage = view.findViewById(R.id.pick_image);
        image = view.findViewById(R.id.image);
        setRadioListener();
        setAddButtonListener();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setTitle("title")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });

        if(mode == "edit"){
            DreamManager dreamManager = new DreamManager(getActivity());
            Dream dream = dreamManager.getDream(dreamId);
            inputDream.setText(dream.getTitle());
            inputDeadline.setText(dream.getDeadline());
        }

        AlertDialog dialog = builder.create();

        return dialog;

    }

    private void setRadioListener(){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                Date date;
                switch (i){

                    case R.id.radio_1:
                        Common.log("radio_1");
                        date = Common.addDateFromToday("YEAR", 1);
                        inputDeadline.setText(Common.formatDate(date, Common.DATE_FORMAT_SAMPLE_1));
                        break;
                    case R.id.radio_2:
                        Common.log("radio_2");
                        date = Common.addDateFromToday("YEAR", 2);
                        inputDeadline.setText(Common.formatDate(date, Common.DATE_FORMAT_SAMPLE_1));
                        break;
                    case R.id.radio_3:
                        Common.log("radio_3");
                        date = Common.addDateFromToday("YEAR", 3);
                        inputDeadline.setText(Common.formatDate(date, Common.DATE_FORMAT_SAMPLE_1));
                        break;
                    default:
                        Common.log("default");
                }
            }
        });
    }

    private void setAddButtonListener(){

        if(mode == "insert"){

            btAddDream.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //add infomation
                    Dream dream = new Dream();
                    dream.setTitle(inputDream.getText().toString());
                    //dream.setDescription(inputDetails.getText().toString());
                    dream.setDeadline(inputDeadline.getText().toString());

                    DreamManager manager = new DreamManager(getContext());
                    long insertId = manager.addDream(dream);

                    if(insertId > 0){
                        Common.toast(getContext(), "Add Dream " + dream.getTitle());
                    }
                    Intent result = new Intent();
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK,result );

                }
            });


        }else{

            btAddDream.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //add infomation
                    DreamManager manager = new DreamManager(getContext());
                    Dream dream = manager.getDream(dreamId);
                    dream.setTitle(inputDream.getText().toString());
                    dream.setDeadline(inputDeadline.getText().toString());
                    dream.setImage(imageByte);

                    long insertId = manager.updateDream(dream);

                    if(insertId > 0){
                        Common.toast(getContext(), "Update Dream " + dream.getTitle());
                    }
                    Intent result = new Intent();
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK,result );

                }
            });

        }



        pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pickImage
                //ファイルから画像取得する
                Intent photoPickIntent = new Intent(Intent.ACTION_PICK);

                File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String pictureDirectoryPath = pictureDirectory.getPath();
                Uri data = Uri.parse(pictureDirectoryPath);
                photoPickIntent.setDataAndType(data, "image/*");

                startActivityForResult(photoPickIntent,IMAGE_GALLERY_REQUEST );
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == getActivity().RESULT_OK){

            if(requestCode == IMAGE_GALLERY_REQUEST){
                Uri imageUri = data.getData();

                InputStream inputStream;
                BitmapFactory.Options imageOptions;

                try {
                    inputStream = getActivity().getContentResolver().openInputStream(imageUri);

                    //画像サイズ情報
                    imageOptions = new BitmapFactory.Options();
                    imageOptions.inJustDecodeBounds = true;
                    BitmapFactory.decodeStream(inputStream,null,imageOptions);
                    inputStream.close();

                    //再度読み込み
                    inputStream = getActivity().getContentResolver().openInputStream(imageUri);
                    int width = imageOptions.outWidth;

                    int p = 1;
                    while(width > 400){
                        //縮小率を決める
                        p *= 2;
                        width /= p;
                    }

                    Bitmap imageBitmap;
                    if(p > 1){
                        //縮小
                        imageOptions = new BitmapFactory.Options();
                        imageOptions.inSampleSize = p;
                        imageBitmap = BitmapFactory.decodeStream(inputStream, null,imageOptions);

                    }else{
                        //等倍
                        imageBitmap = BitmapFactory.decodeStream(inputStream, null,null);
                    }

                    inputStream.close();

                    //bitmapをblob保存用に変換
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    imageByte = stream.toByteArray();
                    stream.close();

                    //確認用の画像
                    Bitmap img = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                    image.setImageBitmap(img);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }


    }

    private View setTouchListenerToView(View view){

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Common.ActionManager actionManager = new Common.ActionManager();
                actionManager.setKeyBoardHide(getActivity(), layout);
                return false;
            }
        });

        return view;
    }


}

