package com.cos.photogramstart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.domain.subscribe.Subscribe;
import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

@SpringBootApplication
public class PhotogramstartApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotogramstartApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(UserRepository userRepository, ImageRepository imageRepository,  SubscribeRepository subscribeRepository, BCryptPasswordEncoder encoder) {
		String password = encoder.encode("1234");
		return args -> {
			User ssar = User.builder().username("ssar").email("ssar@nate.com").password(password).name("쌀").build();
			User cos = User.builder().username("cos").email("cos@nate.com").password(password).name("코스").build();
			userRepository.save(ssar);
			userRepository.save(cos);
			
			imageRepository.save(Image.builder().user(ssar).caption("자전거").postImageUrl("p1.jpeg").build());
			imageRepository.save(Image.builder().user(ssar).caption("오토바이").postImageUrl("p2.jpeg").build());
			imageRepository.save(Image.builder().user(ssar).caption("자동차").postImageUrl("p3.jpeg").build());
			imageRepository.save(Image.builder().user(ssar).caption("비행기").postImageUrl("p4.jpeg").build());
			imageRepository.save(Image.builder().user(cos).caption("햄버거").postImageUrl("burger.jpeg").build());
			imageRepository.save(Image.builder().user(cos).caption("커피").postImageUrl("coffee.jpeg").build());
			imageRepository.save(Image.builder().user(cos).caption("피자").postImageUrl("pizza.jpeg").build());
			
			subscribeRepository.save(Subscribe.builder().fromUser(ssar).toUser(cos).build());
			subscribeRepository.save(Subscribe.builder().fromUser(cos).toUser(ssar).build());
		};
	}

}
