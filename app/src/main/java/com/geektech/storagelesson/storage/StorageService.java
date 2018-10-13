package com.geektech.storagelesson.storage;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

// Created by askar on 10/12/18.
public class StorageService {

    public static void saveFile(
            Context context,
            byte[] data,
            String filename) {
        FileOutputStream outStream;
        try {
            outStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outStream.write(data);
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveToExternalPublic (
            byte[] data,
            String filename
    ){
        String dirName = "Lesson";
        File sourceDir = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
                dirName
        );

        //Создаем директорию если ее нет
        if (!sourceDir.mkdirs()) {
            Log.e("ololo", "Public directory not created");
        }

        File file = new File(sourceDir, filename);
        try {
            Log.d("ololo", "Public path is " + file.getPath());
            if (file.exists()){
                file.delete();
            } else {
                file.createNewFile();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data);
            fileOutputStream.close();
        } catch (Exception e) {
            Log.e("ololo", "Can't write file", e);
        }
    }

    public static void saveToExternalPrivate (
            Context context,
            byte[] data,
            String filename
    ){
        String dirName = "Lesson";
        File sourceDir = new File(
                context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
                dirName
        );

        //Создаем директорию если ее нет
        if (!sourceDir.mkdirs()) {
            Log.e("ololo", "Private directory not created");
        }

        File file = new File(sourceDir, filename);
        try {
            Log.d("ololo", "Private path is " + file.getPath());
            if (file.exists()){
                file.delete();
            } else {
                file.createNewFile();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data);
            fileOutputStream.close();
        } catch (Exception e) {
            Log.e("ololo", "Can't write file", e);
        }
    }


    public static boolean deleteFile(
            Context context,
            String filename
    ){
        File file = new File(context.getFilesDir(), filename);
        if (file.exists()){
            return file.delete();
        }
        return false;
    }

    private static Boolean canWrite(
            Context context,
            byte[] data
    ) {
        return context.getFilesDir().getUsableSpace() > data.length;
    }
}
