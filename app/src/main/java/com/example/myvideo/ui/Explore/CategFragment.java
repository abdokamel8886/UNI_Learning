package com.example.myvideo.ui.Explore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myvideo.R;
import com.example.myvideo.adapters.ArticlesRecyclerAdapter;
import com.example.myvideo.adapters.CatsRecyclerAdapter;
import com.example.myvideo.databinding.FragmentCategBinding;
import com.example.myvideo.models.ArticleModel;
import com.example.myvideo.ui.Explore.Article.ArticleFragment;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class CategFragment extends Fragment {

    FragmentCategBinding binding;
    BottomNavigationView nav;
    ExploreViewModel viewModel;

    CatsRecyclerAdapter adapter = new CatsRecyclerAdapter();
    ArticlesRecyclerAdapter articlesRecyclerAdapter = new ArticlesRecyclerAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categ, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentCategBinding.bind(view);
        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.VISIBLE);
        viewModel = new ViewModelProvider(this).get(ExploreViewModel.class);

        getCoursesCats();
        onClicks();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void onClicks(){

        binding.Courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCoursesCats();
            }
        });

        binding.books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBooksCats();
            }
        });

        binding.articles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getArticles();
            }
        });

    }


    private void getCoursesCats(){
        binding.books.setBackgroundColor(getResources().getColor(R.color.white));
        binding.Courses.setBackgroundColor(getResources().getColor(R.color.color1));
        binding.articles.setBackgroundColor(getResources().getColor(R.color.white));

        binding.books.setTextColor(getResources().getColor(R.color.black));
        binding.articles.setTextColor(getResources().getColor(R.color.black));
        binding.Courses.setTextColor(getResources().getColor(R.color.white));
        viewModel.getCourses_Cats();

        viewModel.catslist.observe(getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                adapter.setList(strings);
                binding.recycler.setAdapter(adapter);

                adapter.setOnItemClick(new CatsRecyclerAdapter.OnItemClick() {
                    @Override
                    public void OnClick(String category) {
                        SharedModel.setExplore_item("course");
                        SharedModel.setExplore_cat(category);
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new ExploreFragment() ,"e")
                                .addToBackStack("e").commit();
                    }
                });
            }
        });
    }

    private void getBooksCats(){

        binding.books.setBackgroundColor(getResources().getColor(R.color.color1));
        binding.Courses.setBackgroundColor(getResources().getColor(R.color.white));
        binding.articles.setBackgroundColor(getResources().getColor(R.color.white));

        binding.books.setTextColor(getResources().getColor(R.color.white));
        binding.articles.setTextColor(getResources().getColor(R.color.black));
        binding.Courses.setTextColor(getResources().getColor(R.color.black));

        viewModel.getBooks_Cats();

        viewModel.catslist.observe(getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                adapter.setList(strings);
                binding.recycler.setAdapter(adapter);

                adapter.setOnItemClick(new CatsRecyclerAdapter.OnItemClick() {
                    @Override
                    public void OnClick(String category) {
                        SharedModel.setExplore_item("book");
                        SharedModel.setExplore_cat(category);
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new ExploreFragment() ,"e")
                                .addToBackStack("e").commit();
                    }
                });

            }
        });
    }

    private void getArticles(){

        binding.books.setBackgroundColor(getResources().getColor(R.color.white));
        binding.Courses.setBackgroundColor(getResources().getColor(R.color.white));
        binding.articles.setBackgroundColor(getResources().getColor(R.color.color1));

        binding.books.setTextColor(getResources().getColor(R.color.black));
        binding.articles.setTextColor(getResources().getColor(R.color.white));
        binding.Courses.setTextColor(getResources().getColor(R.color.black));


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
}