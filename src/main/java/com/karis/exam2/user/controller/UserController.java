package com.karis.exam2.user.controller;

import com.karis.exam2.user.dao.UserRepository;
import com.karis.exam2.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/usr/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("doJoin")
    @ResponseBody
    public String doJoin(String name, String email, String password) {
        if ( name == null || name.trim().length() == 0 ) {
            return "이름을 입력해주세요.";
        }

        name = name.trim();

        if ( email == null || email.trim().length() == 0 ) {
            return "이메일을 입력해주세요.";
        }

        email = email.trim();

        boolean existsByEmail = userRepository.existsByEmail(email);

        if ( existsByEmail ) {
            return "입력하신 이메일(%s)은 이미 사용중입니다.".formatted(email);
        }

        if ( password == null || password.trim().length() == 0 ) {
            return "비밀번호를 입력해주세요.";
        }

        password = password.trim();

        User user = new User();
        user.setRegDate(LocalDateTime.now());
        user.setUpdateDate(LocalDateTime.now());
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        userRepository.save(user);

        return "%d번 회원이 생성되었습니다.".formatted(user.getId());
    }
    @RequestMapping("doLogin")
    @ResponseBody
    public String doLogin(String email, String password){
        if ( email == null || email.trim().length() == 0 ) {
            return "이메일을 입력해주세요.";
        }

        email = email.trim();

        // User user = userRepository.findByEmail(email).orElse(null); // 방법1
        Optional<User> user = userRepository.findByEmail(email); // 방법2
        //대신 Optional을 사용하면 getxxx()앞에 get()을 꼭 넣어야한다.
        if ( user.isEmpty() ) {
            return "일치하는 회원이 존재하지 않습니다.";
        }

        if ( password == null || password.trim().length() == 0 ) {
            return "비밀번호를 입력해주세요.";
        }

        password = password.trim();

        if( user.get().getPassword().equals(password) == false){
            return "비밀번호가 일치하지 않습니다.";
        }

        return "%s님 환영합니다.".formatted(user.get().getName());
    }
    @RequestMapping("me")
    @ResponseBody
    public User showMe(String email, String password){
        Optional<User> user = userRepository.findById(1L);

        if(user.isEmpty()){
            return null;
        }

        return user.get();
    }

}






