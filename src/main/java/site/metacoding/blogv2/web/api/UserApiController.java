package site.metacoding.blogv2.web.api;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.domain.user.User;
import site.metacoding.blogv2.service.UserService;
import site.metacoding.blogv2.web.api.dto.ResponseDto;
import site.metacoding.blogv2.web.api.dto.user.JoinDto;
import site.metacoding.blogv2.web.api.dto.user.LoginDto;
import site.metacoding.blogv2.web.api.dto.user.UpdateDto;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;
    private final HttpSession session;

    // password, email, addr
    @PutMapping("/s/api/user/{id}")
    public ResponseDto<?> update(@PathVariable Integer id, @RequestBody UpdateDto updateDto) {
        userService.회원수정(id, updateDto);
        return new ResponseDto<>(1, "성공", null);
    }

    // 우리 웹브라우저에서는 현재 사용안함. 추후 앱에서 요청시에 사용할 예정
    @GetMapping("/s/api/user/{id}")
    public ResponseDto<?> userInfo(@PathVariable Integer id) {
        User userEntity = userService.회원정보(id);
        return new ResponseDto<>(1, "성공", userEntity);
    }

    // 회원가입 페이지주세요, 회원가입할게요, 로그인 페이지주세요, 로그인할게요
    // 로그아웃할게요
    @GetMapping("/logout")
    public ResponseDto<?> logout() {
        session.invalidate();
        return new ResponseDto<>(1, "성공", null);
    }

    @PostMapping("/join")
    public ResponseDto<?> join(@RequestBody JoinDto joinDto) {

        userService.회원가입(joinDto);

        return new ResponseDto<>(1, "회원가입성공", null);
    }

    @PostMapping("/login")
    public ResponseDto<?> login(HttpServletResponse response, @RequestBody LoginDto loginDto) {

        User userEntity = userService.로그인(loginDto);

        if (userEntity != null) {
            if (loginDto.getRemember().equals("on")) {
                response.addHeader("Set-Cookie", "remember=" + loginDto.getUsername() + "; Path=/");
                // response.addHeader("Set-Cookie", "remember=" + loginDto.getUsername() + ";
                // Path=/; HttpOnly = true");
            }
            session.setAttribute("principal", userEntity);

            return new ResponseDto<>(1, "로그인성공", null);
        } else {
            return new ResponseDto<>(-1, "로그인실패", null);
        }

    }

}
