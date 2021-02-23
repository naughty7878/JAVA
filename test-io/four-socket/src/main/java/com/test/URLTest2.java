package com.test;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

/**
 * 案例：下载
 */
public class URLTest2 {

    @Test
    public void download() {

        HttpURLConnection urlConnection = null;
        InputStream is = null;
        FileOutputStream fos = null;

        try {
            URL url = new URL("http://127.0.0.1:8080/examples/beauty.jpg");

            // 获取连接
            urlConnection = (HttpURLConnection) url.openConnection();
            // 正式连接
            urlConnection.connect();

            is = urlConnection.getInputStream();
            fos = new FileOutputStream("beauty4.jpg");

            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }

            System.out.println("下载完成");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (urlConnection != null) {
                try {
                    urlConnection.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
