package com.example.festus.resttuitorial.reposlist;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.festus.resttuitorial.R;
import com.example.festus.resttuitorial.main.MainActivity;
import com.example.festus.resttuitorial.viewmodel.RepositoryListItem;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ListActivity extends AppCompatActivity implements ViewInterface, View.OnClickListener {

    private static final String STRING_USERNAME = "STRING_USERNAME";
    private static final String BUNDLE_EXTRA = "BUNDLE_EXTRA";

    private List<RepositoryListItem> listOfData;

    private LayoutInflater layoutInflater;
    RecyclerView recyclerView;
    private CustomAdapter adapter;
    private Toolbar toolbar;
    private String user;
     ContentLoadingProgressBar progressBar;

    @Inject
    ListPresenter listPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView =  findViewById(R.id.rec_list_activity);
        layoutInflater = getLayoutInflater();

        toolbar =  findViewById(R.id.tlb_list_activity);
        toolbar.setTitle(R.string.title_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setTitleMarginStart(72);
        toolbar.setNavigationOnClickListener(this);

        progressBar = findViewById(R.id.pgb_list_activity);

        Bundle extras = getIntent().getBundleExtra(BUNDLE_EXTRA);
        if (extras != null) {
            user = extras.getString(STRING_USERNAME);
        } else {
            Toast.makeText(this, "Extras were null.", Toast.LENGTH_SHORT).show();
            startMainActivity();
        }

    }

    @Override
    public void onClick(View v) {
        startMainActivity();


    }

    @Override
    public void setUpAdapterAndView(List<RepositoryListItem> listOfDat) {
        Log.d("returned list data  ui", "got to this method");

        this.listOfData = listOfDat;

        progressBar.hide();
        recyclerView.setVisibility(View.VISIBLE);



        Log.d("returned list data  ui", String.valueOf(listOfDat.size()));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);


        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                layoutManager.getOrientation()
        );

        itemDecoration.setDrawable(
                ContextCompat.getDrawable(
                        ListActivity.this,
                        R.drawable.divider_white
                )
        );

        recyclerView.addItemDecoration(
                itemDecoration
        );

    }

    @Override
    public void showErrorMessage(String error) {

        Toast.makeText(this, error, Toast.LENGTH_LONG).show();

    }

    @Override
    public void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void showLoadingIndicator() {
        progressBar.show();
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        listPresenter.stop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        listPresenter.start(user);

    }


    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {



        @Override
        public CustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.item_data, parent, false);

            return new CustomViewHolder(v);
        }


        @Override
        public void onBindViewHolder(CustomAdapter.CustomViewHolder holder, int position) {

            RepositoryListItem repo = listOfData.get(position);

            Glide.with(ListActivity.this)
                    .load(repo.getRepoAvatarUrl())

                    .into(holder.userAvatar)            ;

            holder.description.setText(
                    repo.getRepoDescription()
            );

            holder.creationDate.setText(
                    repo.getRepoCreationDate()
            );
        }

        @Override
        public int getItemCount() {


            return listOfData.size();
        }


        class CustomViewHolder extends RecyclerView.ViewHolder {

            private ImageView userAvatar;
            private TextView creationDate;
            private TextView description;

            public CustomViewHolder(View itemView) {
                super(itemView);
                this.userAvatar = (ImageView) itemView.findViewById(R.id.imv_list_item);
                this.creationDate = (TextView) itemView.findViewById(R.id.lbl_date_and_time);
                this.description = (TextView) itemView.findViewById(R.id.lbl_message);
            }
        }
    }
}
