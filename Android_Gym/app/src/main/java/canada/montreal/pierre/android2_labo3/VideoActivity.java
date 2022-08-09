package canada.montreal.pierre.android2_labo3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.sql.Connection;
import java.util.Objects;

import canada.montreal.pierre.android2_labo3.mysql.DbUtil;

public class VideoActivity extends AppCompatActivity {//implements View.OnClickListener{

    final String TAG = this.getClass().getSimpleName();
    private VideoView vvVideo;
    //private TextView advice;
    private TextView questionAnswer;


    private long gymExId;
    private String gymExTitle;
//    private List<ExcerciceWithVideo> list;

    private final int GET_LIST = 0;
    private Video video;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Toolbar toolbar = findViewById(R.id.videoTb);
        Intent intent = getIntent();
//gymExTitle
        gymExTitle = intent.getStringExtra("gymExTitle");
        toolbar.setTitle(gymExTitle);

        //toolbar.setTitle(R.string.video);
        setSupportActionBar(toolbar);
        //必须这样写
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


//        vvVideo = findViewById(R.id.vv_video);
        //advice = findViewById(R.id.textView);
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "S0Q4gqBUs7c";
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
        questionAnswer = findViewById(R.id.textView2);

        gymExId = intent.getLongExtra("gymExId", 0);
        Log.d(TAG + " gymExId", String.valueOf(gymExId));
        this.listerExVideo(gymExId);
        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                if (msg.what == GET_LIST) {
                    if (ContextCompat.checkSelfPermission(VideoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(VideoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        Toast.makeText(VideoActivity.this, "权限不够llllllll", Toast.LENGTH_LONG);
                    } else {

                        //String adviceStr = "";
                        String qaStr = "";

                        //adviceStr = video.getAdvice();
                        qaStr = video.getQuestionAnswer();
                        //String url = video.getVideoAdress();

                        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
                        getLifecycle().addObserver(youTubePlayerView);

                        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                            @Override
                            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                                String videoID = video.getVideoAdress();

                                //Log.i(TAG + " videoID",videoID);
                                if(videoID == null||videoID.length()<=0){
                                    videoID =  "sD-tzrVZvrY";;

                                }
                                youTubePlayer.loadVideo(videoID, 0);
                            }
                        });

                        //注意这个context,不能乱写，要是当前调取VideoView的activity,之前用了getApplicationContext就错了
                        //MediaController localMediaController = new MediaController(VideoActivity.this);
                        //Log.i(TAG + " url", url);

//                        vvVideo.setVideoURI(Uri.parse(url));
//
//                        vvVideo.setMediaController(localMediaController);
//
//                        localMediaController.setMediaPlayer(vvVideo);
//                        vvVideo.start();

                        //advice.setText(adviceStr);
                        questionAnswer.setText(qaStr);
                    }
                }

            }
        };

    }


        /*在线播放测试地址
        * http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4
        * http://vfx.mtime.cn/Video/2019/03/18/mp4/190318214226685784.mp4

http://vfx.mtime.cn/Video/2019/03/19/mp4/190319104618910544.mp4

http://vfx.mtime.cn/Video/2019/03/19/mp4/190319125415785691.mp4

http://vfx.mtime.cn/Video/2019/03/17/mp4/190317150237409904.mp4

http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4

http://vfx.mtime.cn/Video/2019/03/14/mp4/190314102306987969.mp4

http://vfx.mtime.cn/Video/2019/03/13/mp4/190313094901111138.mp4

http://vfx.mtime.cn/Video/2019/03/12/mp4/190312143927981075.mp4

http://vfx.mtime.cn/Video/2019/03/12/mp4/190312083533415853.mp4

http://vfx.mtime.cn/Video/2019/03/09/mp4/190309153658147087.mp4
        * */


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //initVideoView();
                    this.listerExVideo(gymExId);
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    Toast.makeText(this, "没有足够的权限", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home: {
                //vvVideo.stopPlayback();


                this.finish();//结束当前
            }

            default: {
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }



    void listerExVideo(long geID) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection con = DbUtil.getConn();
                video = DbUtil.getVideo(con, geID);
                Message message = new Message();
                message.what = GET_LIST;
                handler.sendMessage(message);

            }
        }).start();

    }
}