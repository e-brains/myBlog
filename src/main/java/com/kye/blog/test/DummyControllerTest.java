package com.kye.blog.test;

import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kye.blog.model.RoleType;
import com.kye.blog.model.User;
import com.kye.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired //의존성 주입
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/del/{id}")
	public String delete(@PathVariable int id) {
		
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. id : " +id;
		}
				
		return "삭제되었습니다.  id : " + id;
	}
			
	//email, password를 update하는 경우
	//put, post, delete는 UI에서 form을 사용해서 호출한다 
	//@RequestBody를 사용하여 json데이터를 받는다. (메시지 컨버터가 자동으로 변환해서 줌)
	@Transactional // updateUser()메소드가 시작할 때 트랜잭션이 시작되고 메소드가 종료할 때 자동으로 commit과 함께 트랜잭션 종료
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser ) {
		
		System.out.println("id : "+id);
		System.out.println("password : "+requestUser.getPassword());
		System.out.println("email : "+requestUser.getEmail());
				
		//save는 원래 insert용인데 키값인 id를 같이 넘겨주면 update로 사용할 수 있으나
		//requestUser를 바로 넘기면 null값이 넘 많아서 오류가 발생한다 (createdate, username이 null)
		//그래서 id를 가지고 먼저 해당 데이터를 DB로 부터 읽은 다음 객체에 담고 여기에  수정하기 위한 
		//항목만 set해서 그 객체를 save()에 넘긴다.
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다. id : "+id);
		});
		
		//user는 null이 없다.
		//여기서 업데이트 하고자 하는 값만 변경이 된다
		user.setPassword(requestUser.getPassword()); 
		user.setEmail(requestUser.getEmail());
		
		//save()는 id를 전달하지 않으면 insert 를 하고 id를 전달하면 update를 한다.
		//id가 전달되더라도 해당 id에 대한 데이터가 없으면 insert를 한다.
		//userRepository.save(user); 
		
		//@Transactional을 붙이면 save()를 호출하지 않아도 update가  수행된다. 이를 더티 체킹이라 한다.
		//앞으로는 업데이트 시에는 save()를 사용하지 않는다.
		//더티 체킹 : save()시 객체의 변경사항을 체크한 뒤 변경되었으면 자동으로 update수행
				
		return null;
	}
		
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		//user 4번 요청이 왔을때 데이터베이스에서 못찾게 되면 user가 null이 됨
		//그럼 null을 리턴하게 되는데 문제가 발생 할 수 있음
		//그래서 Optional로 User객체를 감싸서 줄테니 null인지 아닌지 판단해서 return해라
		//null이면 orElseThrow()이하를 타게 되고 아니면 정상 리턴한다.
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				// 나중에 AOP개념을 이용해 적당한 에러 메시지 처리할 예정
				return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
			}
			
		});
		
		//Supplier타입은 익명이기 때문에 람다식으로 아래와 같이 고칠 수 있다.
//		User user = userRepository.findById(id).orElseThrow( ()->{
//			return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
//		} ); 
		
		//객체로 리턴하고 있다 .
		//스프링은 응답 시 메시지 컨버터가 자동으로 동작하여 json으로 변환해서 전달함
		return user;
		
	}
	
	
	// http://localhost:8000/blog/dummy/join (요청)
	// @RequestParam("username") String usrNm @RequestParam을 사용해서 인자값을 선언하면 usrNm처럼 변수명을 맘대로 정할 수 있다
	// @RequestParam을 사용하지 않으면 username, password 처럼 변수명을 UI단과 동일하게 선언해 줘야 한다.
	@GetMapping("/dummy/join")
	public String join(String username, String password, String email) {

		System.out.println("username="+username);
		System.out.println("password="+password);
		System.out.println("email="+email);
		
		return "회원가입이 완료되었습니다.";
	}
	
	// 모델의 User오브젝트를 사용해서 받을 수 있다.
	// @PostMapping도 동일함
	@GetMapping("/dummy/joinObj")
	public String joinObj(User user) { 
		
		System.out.println("user.getUsername()="+user.getUsername());
		System.out.println("user.getPassword()="+user.getPassword());
		System.out.println("user.getEmail()="+user.getEmail());

		user.setRole(RoleType.USER); //RoleType이라는 Enum클래스를 만든 경우 이와 같이 처리하여 값을 강제한다.(실수 방지)
		userRepository.save(user);
		
		return "회원가입이 완료되었습니다.";
	}
		
		
}
