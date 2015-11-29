package com.example.chrno.principal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Descargar extends AppCompatActivity {

    private Bitmap bmp;
    private android.widget.ListView lv;
    private Adaptador ad;
    private ProgressBar pb;
    private String s;
    private ArrayList<String>imgs = new ArrayList<>();
    private ArrayList<Bitmap>bmps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descargar);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        inicio();
    }

    public void inicio(){
        lv = (ListView)findViewById(R.id.lvLista);
        Intent i = getIntent();
        Bundle b = getIntent().getExtras();
        s = b.getString("url");
        Tarea t = new Tarea();
        t.execute(s);
    }

    public class Tarea extends AsyncTask<String, Integer, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(String... params) {
            try {
                URL url;
                try{
                   url = new URL("http://" + params[0]);
                }catch(Exception e){
                    url = new URL ("https://" + params[0]);
                }

                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

                String linea, out = "", img;
                while ((linea = in.readLine()) != null) {

                    int pos = 1, pos1, pos2;
                    if (linea.startsWith("<img") || linea.contains("<img")) {

                        pos = linea.indexOf("<img", pos);
                        pos1 = linea.indexOf("src=\"", pos);
                        pos2 = linea.indexOf("\"", pos1 + 5);
                        img = linea.substring(pos1 + 5, pos2);

                        if (!img.contains(".gif")) {
                            imgs.add(img);
                            URL urlimg = new URL(img);
                            bmps.add(BitmapFactory.decodeStream(urlimg.openConnection().getInputStream()));
                        }
                    }
                    publishProgress();
                }
            } catch (Exception e){

            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<String> imgs2) {
            pb.setVisibility(View.GONE);
            ad = new Adaptador(Descargar.this, imgs);

            lv.setAdapter(ad);
            lv.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent(Descargar.this, Imagen.class);
                            i.putExtra("bitmap", bmps.get(position));
                            startActivity(i);
                        }
                    }
            );
        }
    }
}
