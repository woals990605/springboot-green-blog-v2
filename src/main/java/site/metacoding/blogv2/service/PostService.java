package site.metacoding.blogv2.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.domain.post.Post;
import site.metacoding.blogv2.domain.post.PostRepository;

// 웹브라우저 -> 컨트롤러 -> 서비스 -> 레포지토리 -> 영속성컨텍스트 -> DB

@Service // 컨포넌트 스캔시에 IoC 컨테이너에 등록됨 // 트랜잭션 관리하는 오브젝트임. (기능모임)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void 글쓰기(Post post) {
        postRepository.save(post);

    }

    public Page<Post> 글목록(Integer page) {
        PageRequest pr = PageRequest.of(page, 3, Sort.by(Direction.DESC, "id"));
        return postRepository.findAll(pr);
    }
}
