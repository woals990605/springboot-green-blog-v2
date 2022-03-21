package site.metacoding.blogv2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestViewController {
    // 모든 페이지 잘 작동하는지 확인 후 푸시 - 2. 컨트롤러 페이지 작동 테스트

    @GetMapping("/test/post/writeForm")
    public String postWriteForm() {
        return "post/writeForm";
    }

    @GetMapping("/test/post/list")
    public String postList() {
        return "post/list";
    }

    @GetMapping("/test/post/detail")
    public String postDetail() {
        return "post/detail";
    }

    @GetMapping("/test/post/updateForm")
    public String postUpdateForm() {
        return "post/updateForm";
    }

    @GetMapping("/test/joinForm")
    public String userJoinForm() {
        return "user/joinForm";
    }

    @GetMapping("/test/loginForm")
    public String userLoginForm() {
        return "user/loginForm";
    }

    @GetMapping("/test/user")
    public String userDetail() {
        return "user/detail";
    }

    @GetMapping("/test/user/updateForm")
    public String userUpdateForm() {
        return "user/updateForm";
    }

}
