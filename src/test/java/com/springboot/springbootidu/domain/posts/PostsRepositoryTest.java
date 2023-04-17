package com.springboot.springbootidu.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;
    @AfterAll//1
    public void cleanUp(){
        postsRepository.deleteAll();
    }

    @Test
    public void loadAndSaveContent(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder().title(title).content(content).author("kyuyoungk@naver.com").build());
        //2

        //when
        List<Posts> postsList = postsRepository.findAll();
        //3

        //then
        Posts posts = postsList.get(0);
        Assertions.assertThat(posts.getTitle()).isEqualTo(title);
        Assertions.assertThat(posts.getContent()).isEqualTo(content);
    }
}

/**
 * 1. AfterAll
 * Junit 에서 단위 테스트가 끝날 때마다 수항되는 메소드를 지정
 *
 * 2. postsRepository.save
 * 테이블 posts 에 insert/update 쿼리를 실행한다
 * id 값이 있으면 update 없으면 insert 를 실행
 *
 * 3. postRepository.findAll
 * 테이블 posts 에 있는 모든 데이터를 조회하는 메소드
 */