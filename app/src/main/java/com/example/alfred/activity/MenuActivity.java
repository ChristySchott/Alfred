package com.example.alfred.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.alfred.InfosApp;
import com.example.alfred.R;
import com.example.alfred.RecyclerItemClickListener;
import com.example.alfred.adapter.AdapterRestaurants;
import com.example.alfred.model.Restaurant;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    // private AppBarConfiguration mAppBarConfiguration;
    private RecyclerView recyclerViewOpenedRestaurants;
    private List<Restaurant> restaurantList =  new ArrayList<>();
    private InfosApp infosApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /* DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController); */

        recyclerViewOpenedRestaurants = findViewById(R.id.rvOpened);

        infosApp = (InfosApp) getApplicationContext();

        // Listagem de Restaurantes
        this.createRestaurants();

        // Configurar adapter para o RecyclerView
        AdapterRestaurants adapterRestaurants = new AdapterRestaurants(restaurantList);

        // Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewOpenedRestaurants.setLayoutManager(layoutManager);
        recyclerViewOpenedRestaurants.setHasFixedSize(true);
        recyclerViewOpenedRestaurants.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerViewOpenedRestaurants.setAdapter(adapterRestaurants);

        // Evento de Clique em um item da Recycler View
        recyclerViewOpenedRestaurants.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerViewOpenedRestaurants,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Restaurant myRestaurant = infosApp.getRestaurantList().get(position);

                                Intent it = new Intent(MenuActivity.this, RestaurantMenuActivity.class);
                                it.putExtra("restaurant", myRestaurant);
                                startActivity(it);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );
    }


    // TODO - BUSCAR DO BANCO
    public void createRestaurants() {
        Restaurant restaurant = new Restaurant("name", 5, "pizza", 5.5);
        restaurantList.add(restaurant);
        Restaurant restaurant2 = new Restaurant("name", 5, "pizza", 5.5);
        restaurantList.add(restaurant2);
        Restaurant restaurant3 = new Restaurant("name", 5, "pizza", 5.5);
        restaurantList.add(restaurant3);
        Restaurant restaurant4 = new Restaurant("name", 5, "pizza", 5.5);
        restaurantList.add(restaurant4);
    }


    /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

     */
}
