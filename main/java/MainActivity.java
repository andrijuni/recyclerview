package com.andri.juni.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView RV_Library;
    private ArrayList<Library> list ;
    private static String[] data_Name;
    private static String[] data_Location;
    private static TypedArray data_Photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RV_Library = findViewById(R.id.rv_library);
        RV_Library.setHasFixedSize(true);

        prepare();
        addItem();
        showRecycler();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void prepare() {
        data_Name = getResources().getStringArray(R.array.data_name);
        data_Location = getResources().getStringArray(R.array.data_location);
        data_Photo = getResources().obtainTypedArray(R.array.data_photo);
    }

    private ArrayList<Library> addItem() {
        list = new ArrayList<>();
        for (int i = 0; i < data_Name.length; i++) {
            Library library = new Library();
            library.setPhoto(data_Photo.getResourceId(i,-1));
            library.setName(data_Name[i]);
            library.setLocation(data_Location[i]);
            list.add(library);
        }
        return list;
    }

    private void showRecycler() {
        RV_Library.setLayoutManager(new LinearLayoutManager(this));
        LibraryAdapter libraryAdapter = new LibraryAdapter(list);
        RV_Library.setAdapter(libraryAdapter);

        LibraryAdapter.setOnItemClickCallback(new LibraryAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Library data_Name) {
                showSelectedLibrary(data_Name);
            }
        });
    }

    private void showSelectedLibrary (Library library) {
        Toast.makeText(this, "Anda Berkunjung ke "+ library.getName(),Toast.LENGTH_SHORT).show();
    }
}
