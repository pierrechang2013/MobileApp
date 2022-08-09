package canada.montreal.pierre.judocanada;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

public class FramReplaceActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fram_replace);

        frameLayout = findViewById(R.id.fragLayout);

        setFragment(new LoginFragment());
    }

    public  void setFragment(Fragment fragment){

        FragmentManager fm =  getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(frameLayout.getId(),fragment);
        ft.commit();
    }
}