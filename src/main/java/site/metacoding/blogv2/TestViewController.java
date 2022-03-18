package site.metacoding.blogv2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestViewController {
    // 모든 페이지 잘 작동하는지 확인 후 푸시 - 2. 컨트롤러 페이지 작동 테스트

    @GetMapping("/post/writeForm")
    public String postWriteForm() {
        return "post/writeForm";
    }

    @GetMapping("/post/list")
    public String postList() {
        return "post/list";
    }

    @GetMapping("/post/detail")
    public String postDetail() {
        return "post/detail";
    }

    @GetMapping("/post/updateForm")
    public String postUpdateForm() {
        return "post/updateForm";
    }

    @GetMapping("/joinForm")
    public String userJoinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String userLoginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user")
    public String userDetail() {
        return "user/detail";
    }

    @GetMapping("/user/updateForm")
    public String userUpdateForm() {
        return "user/updateForm";
    }

}
