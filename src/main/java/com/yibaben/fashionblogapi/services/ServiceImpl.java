package com.yibaben.fashionblogapi.services;

import com.yibaben.fashionblogapi.dto.*;
import com.yibaben.fashionblogapi.exceptions.PostAlreadyLikedException;
import com.yibaben.fashionblogapi.exceptions.PostNotFoundException;
import com.yibaben.fashionblogapi.exceptions.UserNotFoundException;
import com.yibaben.fashionblogapi.models.Comment;
import com.yibaben.fashionblogapi.models.Like;
import com.yibaben.fashionblogapi.models.Post;
import com.yibaben.fashionblogapi.models.User;
import com.yibaben.fashionblogapi.repository.CommentRepository;
import com.yibaben.fashionblogapi.repository.LikeRepository;
import com.yibaben.fashionblogapi.repository.PostRepository;
import com.yibaben.fashionblogapi.repository.UserRepository;
import com.yibaben.fashionblogapi.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
public class ServiceImpl implements Services{

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    @Autowired
    public ServiceImpl(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository, LikeRepository likeRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }

    @Override
    public RegisterResponse register(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        userRepository.save(user);
        return new RegisterResponse("Registration Successful", LocalDateTime.now(), user);
    }

    @Override
    public LoginResponse login(LoginDto loginDto) {
        User guestUser = findUserByEmail(loginDto.getEmail());
        LoginResponse loginResponse = null;
        if(guestUser != null){
            if(guestUser.getPassword().equals(loginDto.getPassword()));
            loginResponse = new LoginResponse("Successfully Login in", LocalDateTime.now());
        }else {
            loginResponse = new LoginResponse("Password Not Correct", LocalDateTime.now());
        }
        return loginResponse;
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(()-> new UserNotFoundException("User With email: " + email + " Not Found "));
    }

    @Override
    public CreatePostResponse createPost(PostDto postDto) {
        Post post = new Post();
        User user = findUserById(postDto.getUser_id());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setSlug(makeSlug(postDto.getTitle()));
        post.setPostImage(postDto.getPostImage());
        post.setUser(user);
        postRepository.save(post);
        return new CreatePostResponse("Post Created successfully" , LocalDateTime.now() , post);

    }

    @Override
    public CommentResponse comment(int user_id, int post_id, CommentDto commentDto) {
        User user = findUserById(user_id);
        Post post = findPostById(post_id);
        Comment comment = new Comment();
        comment.setComment(commentDto.getComment());
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
        return new CommentResponse("success" , LocalDateTime.now() , comment , post);

    }

    @Override
    public LikeResponse like(int user_id, int post_id, LikeDto likeDto) {
        Like like = new Like();
        User user = findUserById(user_id);
        Post post = findPostById(post_id);
        LikeResponse likeResponse = null;
        Like duplicateLike = likeRepository.findLikeByUserIdAndPostId(user_id , post_id);
        if (duplicateLike == null){
            like.setLiked(likeDto.isLiked());
            like.setUser(user);
            like.setPost(post);
            likeRepository.save(like);
            List<Like> likeList = likeRepository.likeList(post_id);
            likeResponse = new LikeResponse("success" , LocalDateTime.now() , like , likeList.size());
        }else {
            likeRepository.delete(duplicateLike);
            throw  new PostAlreadyLikedException("This post has been liked, It is now Unliked :(");
        }
        return likeResponse;
    }

    @Override
    public SearchCommentResponse searchComment(String keyword) {
        List<Comment> commentList = commentRepository.findByCommentContainingIgnoreCase(keyword);
        return new SearchCommentResponse("Comment Found" , LocalDateTime.now() , commentList);

    }

    @Override
    public SearchPostResponse searchPost(String keyword) {
        List<Post> postList = postRepository.findByTitleContainingIgnoreCase(keyword);
        return new SearchPostResponse("Post Found" , LocalDateTime.now() , postList);
    }

    @Override
    public Post findPostById(int id) {
        return postRepository.findById(id).orElseThrow(()-> new PostNotFoundException("Post With ID: " + id + " Not Found "));
    }

    public User findUserById(int id){
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User With ID: " + id + " Not Found "));
    }

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public String makeSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }
}
