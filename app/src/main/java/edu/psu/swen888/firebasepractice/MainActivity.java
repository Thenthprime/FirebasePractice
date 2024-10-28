package edu.psu.swen888.firebasepractice;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    TextView txtUserName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            String userName = intent.getStringExtra("email");
            navigationView.getMenu().add("Welcome " + userName);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AllTeamsFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_soccer);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_soccer:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AllTeamsFragment()).commit();
                break;
            case R.id.nav_standings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddATeamFragment()).commit();
                break;
            case R.id.nav_my_account:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TopTeamsFragment()).commit();
                break;
        }
        return true;
    }
}
