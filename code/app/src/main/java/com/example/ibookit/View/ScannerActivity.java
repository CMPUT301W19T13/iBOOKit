package com.example.ibookit.View;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.ibookit.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class ScannerActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 2;
    private Uri mImageUri;
    private BarcodeDetector detector;
    private TextView txtView;
    private ImageView myImageView;
//    private final int myShelfOwnerReturnCode = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        myImageView = (ImageView) findViewById(R.id.Scanner_imageView);
        txtView = findViewById(R.id.scan_decrypted_info);


        detector = new BarcodeDetector.Builder(getApplicationContext())
                        .setBarcodeFormats(Barcode.ALL_FORMATS)
                        .build();
        if(!detector.isOperational()){
            txtView.setText("Could not set up the detector!");
            return;
        }




        Button btn = (Button) findViewById(R.id.scan_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            fileChooser();
            }
        });

    }
    /**
     * pick a image for the book in system
     *
     * reference: https://codinginflow.com/tutorials/android/firebase-storage-upload-and-retrieve-images/part-2-image-chooser
     */
    private void fileChooser () {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    /**
     * select a image to scan, https://stackoverflow.com/questions/29163444/bitmapfactory-unable-to-decode-stream-null
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {

            mImageUri = data.getData();
            try{
                Bitmap mBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(mImageUri));
                processData(mBitmap);
            } catch (IOException ie){
                txtView.setText("error");
            }

        }

    }
    // inspired from: https://www.youtube.com/watch?v=7apJbSpcJwU
    private void processData(Bitmap myBitmap){
        Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
        SparseArray<Barcode> barcodes = detector.detect(frame);

        Barcode thisCode = barcodes.valueAt(0);
        txtView.setText(thisCode.rawValue);
        myImageView.setImageBitmap(myBitmap);
        returnData(thisCode);
    }

    private void returnData(Barcode myBarcode){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("scanned_ISBN", myBarcode.rawValue);
        setResult(RESULT_OK, returnIntent);
        finish();


    }


}
