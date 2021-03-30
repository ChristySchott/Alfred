package com.example.alfred.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.example.alfred.InfosApp;
import com.example.alfred.R;
import com.example.alfred.RecyclerItemClickListener;
import com.example.alfred.adapter.AdapterDishes;
import com.example.alfred.model.Dish;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMenuActivity extends AppCompatActivity {

    private RecyclerView recyclerViewDishes;
    private List<Dish> dishList =  new ArrayList<>();
    private InfosApp infosApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);

        recyclerViewDishes = findViewById(R.id.rvDishes);

        infosApp = (InfosApp) getApplicationContext();

        // Listagem de Restaurantes
        this.createDishes();

        // Configurar adapter para o RecyclerView
        AdapterDishes adapterRestaurants = new AdapterDishes(dishList);

        // Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewDishes.setLayoutManager(layoutManager);
        recyclerViewDishes.setHasFixedSize(true);
        recyclerViewDishes.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerViewDishes.setAdapter(adapterRestaurants);

        // Evento de Clique em um item da Recycler View
        recyclerViewDishes.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerViewDishes,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                // TODO - ADICIONAR AO CARRINHO
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
    public void createDishes() {
        Dish restaurant = new Dish("name", "description",  5.5);
        dishList.add(restaurant);
        Dish restaurant2 = new Dish("name", "description",  5.5);
        dishList.add(restaurant2);
        Dish restaurant3 = new Dish("name", "description",  5.5);
        dishList.add(restaurant3);
        Dish restaurant4 = new Dish("name", "description", 5.5);
        dishList.add(restaurant4);
    }
}
