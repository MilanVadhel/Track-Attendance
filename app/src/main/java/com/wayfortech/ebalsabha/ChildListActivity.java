
package com.wayfortech.ebalsabha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.wayfortech.ebalsabha.Adapters.ChildAdapter;
import com.wayfortech.ebalsabha.Database.DBManger;
import com.wayfortech.ebalsabha.Model.Child;

import java.util.List;

public class ChildListActivity extends AppCompatActivity {

    private RecyclerView childViewList;
    private DBManger dbManger;
    private ChildAdapter childAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewchild);
        initUI();
        getSupportActionBar().setTitle("Child List");
        dbManger=new DBManger(this);
        dbManger.open();
        setRecyclerView();

    }

    protected void onResume() {
        super.onResume();
        setRecyclerView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManger.close();
    }

    private void initUI() {
        childViewList=findViewById(R.id.childListRecyclerView);
    }

    private void setRecyclerView() {
        List<Child> childArrayList=dbManger.getAllChild();
        childViewList.setLayoutManager(new LinearLayoutManager(this));
        childAdapter=new ChildAdapter(this,childArrayList);
        childViewList.setAdapter(childAdapter);
       // childViewList.addItemDecoration(new DividerItemDecoration(childViewList.getContext(),LinearLayoutManager.VERTICAL));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem menuItem=menu.findItem(R.id.searchBar);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                childAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
