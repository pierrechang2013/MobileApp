package canada.montreal.pierre.android1_excercice11;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment {

    android.view.MenuItem add;


    @BindView(R.id.rv)
    RecyclerView rv;

    ListAdapter rva;

    ViewModel vm;
    List<Form> list;
    NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("onCreateView", " lllllllllllllllll");
        View view = inflater.inflate( R.layout.fragment_list, container,false);
        ButterKnife.bind(this, view);
//        rv = view.findViewById(R.id.rv);
        navController = NavHostFragment.findNavController(this);
        setHasOptionsMenu(true);

        vm = new ViewModelProvider(requireActivity()).get(ViewModel.class);
        list = vm.list;

        if(list==null){
            Log.d("ListFragment", "list nulllllllllllllllll");

            list = Functions.getList();
            vm.list = list;
        }else{

            Log.d("ListFragment list size", String.valueOf(list.size()));
        }

         rv.setLayoutManager(new LinearLayoutManager(getContext()));

//        Log.d("ListFragment", "list 111111111111");
        rva = new ListAdapter(vm.list);
//        Log.d("ListFragment", "list 222222222222222");
        rv.setAdapter(rva);
//        Log.d("ListFragment", "list 33333333333333333333");

        rva.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("现在点了",String.valueOf(position));
                Form form = vm.list.get(position);
                form.setCurrentPosition(position);
                vm.select(form);

//                vm.setCurrentForm(vm.list.get(position));

//                Log.d("多少钱",vm.currentForm.getAmount());

                navController.navigate(R.id.action_listFragment2_to_detailFragment2);

            }
        });


//        vm.getList().observe(getViewLifecycleOwner(), new Observer<List<Form>>() {
//            @Override
//            public void onChanged(List<Form> list) {
//
//                Log.d("List ", "bianlilllllllllllllllll");
//                   rva.list = list;
//                Log.d("list", String.valueOf(list.size()));
//                Log.d("rva.list", String.valueOf(rva.list.size()));
//                   rva.notifyDataSetChanged();
//
//
//            }
//        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);


    }

    //操作menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("!!!!!!!!!!!!!!!!点击item",item.toString());
        navController.navigate(R.id.action_listFragment2_to_detailFragment2);
       // return item.onNav(navController) || super.onOptionsItemSelected(item)
        return true;


    }

    //创建menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.menu, menu);

        add = (android.view.MenuItem) menu.findItem(R.id.add);
        Log.d("!!!!!!!!!!!!!!!!创建add",add.toString());

    }
}