package com.example.myvideo.ui.Explore.Article;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myvideo.R;
import com.example.myvideo.adapters.ArticlesRecyclerAdapter;
import com.example.myvideo.adapters.CatsRecyclerAdapter;
import com.example.myvideo.adapters.PostsRecyclerAdapter;
import com.example.myvideo.databinding.FragmentBlogBinding;
import com.example.myvideo.models.ArticleModel;
import com.example.myvideo.models.PostModel;
import com.example.myvideo.ui.Explore.ExploreViewModel;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;


public class BlogFragment extends Fragment {


    FragmentBlogBinding binding;
    BottomNavigationView nav;
    ExploreViewModel viewModel;
    ArticlesRecyclerAdapter articlesRecyclerAdapter = new ArticlesRecyclerAdapter();
    PostsRecyclerAdapter postsRecyclerAdapter = new PostsRecyclerAdapter();


    Button articles , news , posts , events;
    BottomSheetDialog bottomSheetDialog;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentBlogBinding.bind(view);
        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.VISIBLE);
        viewModel = new ViewModelProvider(this).get(ExploreViewModel.class);
        getArticles();
        onClicks();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void onClicks(){

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "add post clicked", Toast.LENGTH_SHORT).show();
            }
        });

        binding.adjust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });
    }

    private void showdialog(){
        bottomSheetDialog = new BottomSheetDialog(getContext() , R.style.BottomSheetTheme);
        bottomSheetDialog.setContentView(R.layout.adjust);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        articles = bottomSheetDialog.findViewById(R.id.articles_btn);
        news = bottomSheetDialog.findViewById(R.id.news_btn);
        events = bottomSheetDialog.findViewById(R.id.events_btn);
        posts = bottomSheetDialog.findViewById(R.id.posts_btn);

        articles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getArticles();
                bottomSheetDialog.dismiss();
            }
        });

        posts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPosts();
                bottomSheetDialog.dismiss();

            }
        });

    }

    private void getArticles(){
        binding.txt.setText("Articles");
        binding.add.setVisibility(View.GONE);
        viewModel.getArticles();
        viewModel.articles.observe(getViewLifecycleOwner(), new Observer<ArrayList<ArticleModel>>() {
            @Override
            public void onChanged(ArrayList<ArticleModel> articleModels) {
                articlesRecyclerAdapter.setList(articleModels);
                binding.recycler.setAdapter(articlesRecyclerAdapter);

                articlesRecyclerAdapter.setOnItemClick(new ArticlesRecyclerAdapter.OnItemClick() {
                    @Override
                    public void OnClick(ArticleModel article) {
                        SharedModel.setSelected_article(article);
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new ArticleFragment(),"a")
                                .addToBackStack("a").commit();
                    }
                });
            }
        });


    }

    private void getPosts(){
        binding.txt.setText("Posts");
        binding.add.setVisibility(View.VISIBLE);
        viewModel.getPosts();

        viewModel.posts.observe(getViewLifecycleOwner(), new Observer<ArrayList<PostModel>>() {
            @Override
            public void onChanged(ArrayList<PostModel> postModels) {
                postsRecyclerAdapter.setList(postModels);
                binding.recycler.setAdapter(postsRecyclerAdapter);
            }
        });

    }
}
