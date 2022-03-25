package site.metacoding.blogv2.web.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.domain.user.User;
import site.metacoding.blogv2.service.UserService;
import site.metacoding.blogv2.web.api.dto.ResponseDto;
import site.metacoding.blogv2.web.api.dto.user.JoinDto;
import site.metacoding.blogv2.web.api.dto.user.LoginDto;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;
    private final HttpSession session;

    @PostMapping("/join")
    public ResponseDto<String> join(@RequestBody JoinDto joinDto) {

        userService.회원가입(joinDto);

        return new ResponseDto<String>(1, "회원가입성공", null);
    }

    @PostMapping("/login")
    public ResponseDto<String> login(HttpServletResponse response, @RequestBody LoginDto loginDto) {

        User userEntity = userService.로그인(loginDto);

        if (userEntity != null) {
            if (loginDto.getRemember().equals("on")) {
                response.addHeader("Set-Cookie", "remember=" + loginDto.getUsername() + "; Path=/");
                // response.addHeader("Set-Cookie", "remember=" + loginDto.getUsername() + ";
                // Path=/; HttpOnly = true");
            }
            session.setAttribute("principal", userEntity);

            return new ResponseDto<String>(1, "로그인성공", null);
        } else {
            return new ResponseDto<String>(-1, "로그인실패", null);
        }

    }

}
