package com.example.festus.resttuitorial.reposlist;

import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.festus.resttuitorial.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ListActivity extends AppCompatActivity implements ViewInterface, View.OnClickListener {

    private static final String STRING_USERNAME = "STRING_USERNAME";
    private static final String BUNDLE_EXTRA = "BUNDLE_EXTRA";

    private List<RepositoryListItem> listOfData;

    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private Toolbar toolbar;
    private String user;
    private ContentLoadingProgressBar progressBar;

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

    }

    @Override
    public void setUpAdapterAndView(List<RepositoryListItem> listOfData) {

    }

    @Override
    public void showErrorMessage(String error) {

    }

    @Override
    public void startMainActivity() {

    }

    @Override
    public void showLoadingIndicator() {

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
