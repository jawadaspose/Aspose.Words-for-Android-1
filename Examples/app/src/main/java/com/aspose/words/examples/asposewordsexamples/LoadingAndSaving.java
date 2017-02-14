package com.aspose.words.examples.asposewordsexamples;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aspose.words.examples.asposewordsexamples.loading_saving.ExportFontsAsBase64;
import com.aspose.words.examples.asposewordsexamples.loading_saving.ExportResourcesUsingHtmlSaveOptions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class LoadingAndSaving extends AppCompatActivity {

    private ListView listView;
    TextView txtView;
    private AdapterView.OnItemClickListener sectionsListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if (ContextCompat.checkSelfPermission(LoadingAndSaving.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(LoadingAndSaving.this, getString(R.string.write_to_external_storage_permission), Toast.LENGTH_SHORT).show();
                return;
            }

            switch (position) {
                case 0:
                    runExportFontsAsBase64();
                    break;
                case 1:
                    runExportResourcesUsingHtmlSaveOptions();
                    break;
                case 3:
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_and_saving);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = (ListView)findViewById(R.id.loading_saving_lv);
        ArrayList<String> sectionsNames = new ArrayList<String>();
        sectionsNames.add("Export Fonts to HTML in Base64 Encoding");
        sectionsNames.add("Working with HtmlSaveOptions Properties");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sectionsNames);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(sectionsListener);
        txtView = (TextView) findViewById(R.id.txtView);

    }

    public void runExportResourcesUsingHtmlSaveOptions()
    {
        String FilePath = Environment.getExternalStorageDirectory().getPath() + File.separator + "AsposeWords" + "/Document.doc";
        ExportResourcesUsingHtmlSaveOptions exportResourcesUsingHtmlSaveOptions= new ExportResourcesUsingHtmlSaveOptions();
        exportResourcesUsingHtmlSaveOptions.Run(FilePath);
    }
    public void runExportFontsAsBase64()
    {
        String FilePath = Environment.getExternalStorageDirectory().getPath() + File.separator + "AsposeWords" + "/Document.doc";
        ExportFontsAsBase64 exportFontsAsBase64= new ExportFontsAsBase64();
        exportFontsAsBase64.Run(FilePath);

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
