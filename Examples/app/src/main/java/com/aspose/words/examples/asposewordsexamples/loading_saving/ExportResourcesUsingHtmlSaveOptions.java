package com.aspose.words.examples.asposewordsexamples.loading_saving;

import android.os.Environment;

import com.aspose.words.CssStyleSheetType;
import com.aspose.words.Document;
import com.aspose.words.HtmlSaveOptions;

import java.io.File;

public class ExportResourcesUsingHtmlSaveOptions {
    public void Run(String filepath) {

        // The export Font feature requires valid Aspose.Words for Android you can get a temporary License from http://www.aspose.com/corporate/purchase/temporary-license.aspx
        try {
            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "AsposeWords" + File.separator + "Resources");
            if (f.isDirectory()) {
                System.out.println("Directory is not created");
            } else {
                f.mkdir();
                System.out.println("Directory is  created");
            }

            String myDir = Environment.getExternalStorageDirectory().getPath() + File.separator + "AsposeWords";
            String resourceDir = myDir + File.separator + "Resources";
            // ExStart:ExportResourcesUsingHtmlSaveOptions
            Document doc = new Document(filepath);
            HtmlSaveOptions saveOptions = new HtmlSaveOptions();
            saveOptions.setCssStyleSheetType(CssStyleSheetType.EXTERNAL);
            saveOptions.setExportFontResources(true);
            saveOptions.setResourceFolderAlias("http://example.com/resources");
            saveOptions.setResourceFolder(resourceDir);
            doc.save(myDir + "/ExportResourcesUsingHtmlSaveOptions_out.html", saveOptions);
            // ExEnd:ExportResourcesUsingHtmlSaveOptions
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}