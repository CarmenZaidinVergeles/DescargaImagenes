package com.example.chrno.principal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class Imagen extends Activity {
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);
        iv = (ImageView)findViewById(R.id.imageView);
        Intent i=getIntent();
        Bundle b=i.getExtras();
        Bitmap bitmap = b.getParcelable("bitmap");
        iv.setImageBitmap(bitmap);
    }

}
