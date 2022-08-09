package canada.montreal.pierre.android2_labo3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadImgThread extends  Thread{

    String imgUrl = null;
    Bitmap bitmap = null;

    private int Load_IMG = 0;
    private Handler handler;
//    ImageView iv = null;
    public LoadImgThread(String imgUrl,Handler handler){
           this.imgUrl = imgUrl;
           this.handler = handler;

    }

    public Bitmap getImg(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream inputStream = null;
                    URL url = new URL(imgUrl);                    //服务器地址
                    if (url != null) {
                        //打开连接
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setConnectTimeout(3000);//设置网络连接超时的时间为3秒
                        httpURLConnection.setRequestMethod("GET");        //设置请求方法为GET
                        httpURLConnection.setDoInput(true);                //打开输入流
                        int responseCode = httpURLConnection.getResponseCode();    // 获取服务器响应值
                        if (responseCode == HttpURLConnection.HTTP_OK) {        //正常连接
                            inputStream = httpURLConnection.getInputStream();

                            bitmap = BitmapFactory.decodeStream(inputStream);

                            Message message = new Message();
                            //
                            message.what = Load_IMG;
                            handler.sendMessage(message);

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }).start();

        return bitmap;
    }

//    @Override
//    public void run() {
//        super.run();
//        try {
//            InputStream inputStream = null;
//            URL url = new URL(imgUrl);                    //服务器地址
//            if (url != null) {
//                //打开连接
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                httpURLConnection.setConnectTimeout(3000);//设置网络连接超时的时间为3秒
//                httpURLConnection.setRequestMethod("GET");        //设置请求方法为GET
//                httpURLConnection.setDoInput(true);                //打开输入流
//                int responseCode = httpURLConnection.getResponseCode();    // 获取服务器响应值
//                if (responseCode == HttpURLConnection.HTTP_OK) {        //正常连接
//                    inputStream = httpURLConnection.getInputStream();
//
//                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                    iv.setImageBitmap(bitmap);//获取输入流
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
