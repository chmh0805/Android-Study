package com.example.mvvmex1.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmex1.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostViewModel extends ViewModel {

    // LiveData(불변 데이터), MutableLiveData(가변 데이터)
    // LiveData를 변경하려면 DB에 변경요청 후 Data를 다시 받아야 한다.
    // MutableLiveData는 Data를 직접 변경할 수 있다. (DB는 별도로 변경요청을 해야한다.)
    private MutableLiveData<List<Post>> mtPosts = new MutableLiveData<>();

    public MutableLiveData<List<Post>> 구독() {
        return mtPosts;
    }

    public void 포스트한건추가(Post post) {
        List<Post> posts = mtPosts.getValue();
        posts.add(post);
        mtPosts.setValue(posts);
    }

    public void 포스트변경() {
        List<Post> posts = mtPosts.getValue();
        posts.get(0).setTitle("테스트");
        mtPosts.setValue(posts);
    }

    public void 데이터초기화() {
        List<Post> posts = new ArrayList<>();
        mtPosts.setValue(posts);
    }

}
