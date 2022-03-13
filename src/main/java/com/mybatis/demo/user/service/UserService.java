package com.mybatis.demo.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybatis.demo.user.mapper.UserMapper;
import com.mybatis.demo.user.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{

	private final UserMapper userMapper;
	
	@Transactional
    public int signUp(User user) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.insert(user);
    }

//	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		
//		Optional<User> userEntity = userMapper.selectById(id);
//		User user = userEntity.get();
//		
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		 
//		if(("admin").equals(id)) {
//			authorities.add(new SimpleGrantedAuthority("ADMIN"));
//		} else if(("member").equals(id)) {
//			authorities.add(new SimpleGrantedAuthority("MEMBER"));
//		}
		return null;
		
	}
}
