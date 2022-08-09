package canada.montreal.pierre.android1_excercice11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Hamdane Bouzar
 * Liang CHANG
 *
 * */

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rv)
    RecyclerView rv;

    ListAdapter rva;

    ViewModel vm;
    List<Form> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建nav graph
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.fragmentContainerView);
        //创建controller
        NavController navController = navHostFragment.getNavController();

        //设置
        NavigationUI.setupActionBarWithNavController(this,navController);


    }

    @Override
    public boolean onSupportNavigateUp() {

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.fragmentContainerView);
        //创建controller
        NavController navController = navHostFragment.getNavController();
        return navController.navigateUp();
//        return super.onSupportNavigateUp();
    }



    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return true;
    }




}