//package com.javatechie.demo.service;
//
//import com.javatechie.demo.entity.User;
//import com.javatechie.demo.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@RequiredArgsConstructor
//@Service("userDetailsService")
//public class UserServiceImp implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        User user = userRepository.findByEmail(email);
//        if (user == null){
//            throw new UsernameNotFoundException("USER DOES NOT EXIST");
//        }
//        return new CustomUserDetails(user);
//    }
//}
