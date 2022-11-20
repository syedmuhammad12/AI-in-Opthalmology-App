package com.hivefive.aiopthomology;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.drjacky.imagepicker.ImagePicker;
import com.github.drjacky.imagepicker.constant.ImageProvider;
import com.hivefive.aiopthomology.ml.ModelFinal;

import org.jetbrains.annotations.NotNull;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.model.Model;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public class ImageOptionsMenu extends AppCompatActivity {

    CardView select_image, camera_image;
    ImageView img;
    int imageSize = 224;
    float result_conf;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_options_menu);
        getSupportActionBar().setTitle("Image Selection");

        /* ------------------------------- Hooks ---------------------------------------*/

        select_image = findViewById(R.id.card_photo_select);
        camera_image = findViewById(R.id.card_photo_camera);
        img = findViewById(R.id.img_scr);


        /* ------------------------------- Hooks ---------------------------------------*/

        ActivityResultLauncher<Intent> launcher=
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(ActivityResult result)->{
                    if(result.getResultCode()==RESULT_OK){
                        Uri uri=result.getData().getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                            classifyImage(bitmap);

                            Intent intent = new Intent(ImageOptionsMenu.this, PatientInfo.class);
                            intent.putExtra("result", result_conf);
                            startActivity(intent);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
//                        img.setImageURI(uri);
                        // Use the uri to load the image






                    }else if(result.getResultCode()== ImagePicker.RESULT_ERROR){
                        // Use ImagePicker.Companion.getError(result.getData()) to show an error
//                                ImagePicker.Companion.getError(result.getData());
                        Log.d("TAG",ImagePicker.Companion.getError(result.getData()).toString() );
                    }
                });

        /* ------------------------------- Opening Camera ---------------------------------------*/

        camera_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Log.d("Button", "onClick: ");
                launcher.launch(
                    ImagePicker.with(ImageOptionsMenu.this)
                            .crop()
                            .cropFreeStyle()
                            .setMultipleAllowed(false)
                            .setOutputFormat(Bitmap.CompressFormat.PNG)
                            .maxResultSize(224,224,true)
                            .provider(ImageProvider.BOTH) //Or bothCameraGallery()
                            .cameraOnly() // or galleryOnly()
                            .createIntent()

                );





            }
        });


        /* ------------------------------- Select Image ---------------------------------------*/

        select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                Log.d("Button", "onClick: ");
                launcher.launch(
                        ImagePicker.with(ImageOptionsMenu.this)
                                .crop()
                                .cropFreeStyle()
                                .setMultipleAllowed(false)
                                .setOutputFormat(Bitmap.CompressFormat.PNG)
                                .maxResultSize(224,224,true)
                                .provider(ImageProvider.BOTH) //Or bothCameraGallery()
                                .galleryOnly() // or galleryOnly()
                                .createIntent()

                );





            }
        });



    }


    private void classifyImage(Bitmap bitmap_image){

        try {
            ModelFinal model = ModelFinal.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);

            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4*imageSize*imageSize*3);
            byteBuffer.order(ByteOrder.nativeOrder());
            int[] intValues = new int[imageSize*imageSize];
            Log.d("button1", "classifyImage: ");
            bitmap_image.getPixels(intValues, 0, bitmap_image.getWidth(), 0, 0, bitmap_image.getWidth(), bitmap_image.getHeight());
            int pixel = 0;
            Log.d("button2", "classifyImage: ");
            for(int i =0; i < imageSize; i++){
                for(int j =0; j < imageSize; j++){
                    int val = intValues[pixel++];
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 255));

                }

            }

            inputFeature0.loadBuffer(byteBuffer);
            Log.d("button3", "classifyImage: ");
            // Runs model inference and gets result.
            ModelFinal.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
            Log.d("button4", "classifyImage: ");
            float[] confidence = outputFeature0.getFloatArray();
            for(int i = 0; i<confidence.length; i++){
                result_conf = confidence[i];
            }
            Log.d("button5", "classifyImage: ");
            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }


    }


}