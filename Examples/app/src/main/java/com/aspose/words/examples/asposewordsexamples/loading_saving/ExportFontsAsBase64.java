package com.aspose.words.examples.asposewordsexamples.loading_saving;

import android.os.Environment;
import android.util.Log;

import com.aspose.words.Document;
import com.aspose.words.HtmlSaveOptions;

import java.io.File;

public class ExportFontsAsBase64 {
    public void Run(String filePath) {

        try {
            String myDir = Environment.getExternalStorageDirectory().getPath() + File.separator + "AsposeWords";
            // ExStart:ExportFontsAsBase64
            Document doc = new Document(filePath);
            HtmlSaveOptions saveOptions = new HtmlSaveOptions();
            saveOptions.setExportFontResources(true);
            saveOptions.setExportFontsAsBase64(true);
            doc.save(myDir + "/ExportFontsAsBase64_out.html", saveOptions);
            // ExEnd:ExportFontsAsBase64
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}