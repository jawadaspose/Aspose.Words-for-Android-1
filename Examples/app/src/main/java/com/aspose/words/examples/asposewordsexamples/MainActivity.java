package com.aspose.words.examples.asposewordsexamples;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private ListView listView;

    private AdapterView.OnItemClickListener sectionsListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, getString(R.string.write_to_external_storage_permission), Toast.LENGTH_SHORT).show();
                return;
            }
            switch (position) {
                case 0:
                    runLoadingSavingAndConverting();
                    break;
                default:
                    break;
            }
        }
    };

    private void runLoadingSavingAndConverting() {
        Intent LoadingAndSaving = new Intent(this, LoadingAndSaving.class);
        startActivity(LoadingAndSaving);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
        ArrayList<String> sectionsNames = new ArrayList<String>();
        sectionsNames.add("Loading, Saving and Converting");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sectionsNames);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(sectionsListener);

        // Request write to external storage permission.
        requestWriteToExternalStoragePermission();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveAssetsFilesToExternalStorage() {
        new SaveFilesToExternalStorageTask().execute();
    }

    private class SaveFilesToExternalStorageTask extends AsyncTask<Void, Integer, Void> {
        private ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage(getString(R.string.saving_resource_files_external_storage));
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setIndeterminate(false);
            progressDialog.setMax(35);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Save Resource Files To External Storage
            Utils.saveFileToExternalStorage(MainActivity.this, "Document.doc");
            publishProgress(Integer.valueOf(1));
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
        }
    }

    public void requestWriteToExternalStoragePermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, getString(R.string.write_to_external_storage_permission), Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        } else {
            saveAssetsFilesToExternalStorage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WRITE_EXTERNAL_STORAGE: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    saveAssetsFilesToExternalStorage();
                } else {
                    // permission denied
                    Toast.makeText(this, getString(R.string.permission_denied_external_storage), Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}