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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //attach views to variables
        setContentView(R.layout.main_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        //put a listener into each of the items on the list
        navigationView.setNavigationItemSelectedListener(this);

        //this puts the menu into the drawer when it is toggled
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

    //the function below allows for switching between fragments when selected on the drawer.
    // note that the view causes an error saying it must be a constant and this can be solved by adding android.nonFinalResIds=false to the gradle-properties file
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
