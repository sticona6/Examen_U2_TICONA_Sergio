package com.upt.sergio;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity {

    String url = "http://www.upt.edu.pe/upt/web/home/";
    TextView descrtxt,titletxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titletxt = (TextView) findViewById(R.id.titletxt);
        descrtxt = (TextView)findViewById(R.id.descrtxt);
        Button titlebutton = (Button) findViewById(R.id.titlebutton);
        ImageView fotoimage = (ImageView)findViewById(R.id.fotoimage);

        titlebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                new Todo().execute();
            }
        });
    }
    public class Todo extends AsyncTask<Void,Void,Void>{

        Elements descripcion;
        String titulo;
        String subtitulo;

        @Override
        protected Void doInBackground(Void... voids) {
        try{
            Document document = Jsoup.connect("http://www.upt.edu.pe/upt/web/home/not_detalle/100000000").get();

                descripcion = document.getElementsByClass("div");
                subtitulo = document.body().getElementsByTag("div").text();
                titulo = document.title();

        }catch (Exception e){
            e.printStackTrace();
        }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            titletxt.setText(titulo+"\n"+subtitulo);
            descrtxt.setText(descripcion.text());
        }
    }
}
