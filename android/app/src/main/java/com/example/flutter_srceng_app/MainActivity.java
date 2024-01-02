package com.example.flutter_srceng_app;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.libsdl.app.SDLActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class MainActivity extends FlutterActivity {
    public static String srcengDir = null;
    public static List<String> gameModList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
    }

    public void setPath(View view) {
        srcengDir = "";
    }

    public void runGame(View view) {
        if (!isSelectGameRootDir()) {
            return;
        }

        Intent intent = new Intent(this, SDLActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private List<String> searchGameMods() {
        List<String> sl = new ArrayList<>();

        if (srcengDir != null) {
            File tDir = new File(srcengDir);
            if (!tDir.isDirectory()) {
                return null;
            }

            String[] tDirs = tDir.list();
            for (String tStr : tDirs) {
                File tDir2 = new File(tDir + "/" + tStr);
                if (!tDir2.isDirectory()) {
                    continue;
                }

                // Find gameinfo.txt
                File tGameinfotxt = new File(tDir2 + "/" + "gameinfo.txt");
                if (tGameinfotxt.exists()) {
                    sl.add(tDir2.getName());
                }
            }
            if (sl.isEmpty()) {
                return null;
            }
        }

        return sl;
    }

    private boolean isSelectGameRootDir() {

        if (com.example.flutter_srceng_app.MainActivity.srcengDir == null) {
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("请选择游戏根目录里的一个文件比如 hl2.exe！")
                    .setPositiveButton("OK", (dialog, which) -> {

                    })
                    .show();
            return false;
        }

        gameModList = searchGameMods();
        if (gameModList == null) {
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("请选择正确的游戏目录")
                    .setPositiveButton("OK", (dialog, which) -> {

                    })
                    .show();
            return false;
        }

        return true;
    }
}
