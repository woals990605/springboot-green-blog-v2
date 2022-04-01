package site.metacoding.blogv2.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.domain.comment.Comment;
import site.metacoding.blogv2.domain.post.Post;
import site.metacoding.blogv2.domain.user.User;
import site.metacoding.blogv2.service.PostService;
import site.metacoding.blogv2.web.api.dto.comment.CommentResponseDto;

@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;
    private final HttpSession session;

    @GetMapping("/post/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Post postEntity = postService.글상세보기(id);
        // comment의 userId랑 세션에 id랑 비교
        User principal = (User) session.getAttribute("principal");
        List<CommentResponseDto> comments = new ArrayList<>();

        for (Comment comment : postEntity.getComments()) {
            CommentResponseDto dto = new CommentResponseDto();
            dto.setComment(comment);

            if (principal != null) { // 인증
                if (principal.getId() == comment.getUser().getId()) { // 권한
                    dto.setAuth(true); // or false
                } else {
                    dto.setAuth(false); // or false
                }
            } else {
                dto.setAuth(false);
            }

            comments.add(dto);
        }

        model.addAttribute("comments", comments);
        model.addAttribute("postId", id);

        return "post/detail";
    }

    // 페이지를 줘
    // /s 붙었으니까 자동으로 인터셉터가 인증 체크함.
    @GetMapping("/s/post/writeForm")
    public String writeForm() {
        return "post/writeForm";
    }

    @GetMapping({ "/", "/post" })
    public String home() {
        return "post/list";
    }

}
