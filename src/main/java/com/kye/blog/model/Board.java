package com.kye.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder // 빌더패턴 사용
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Board {
	
	@Id //Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) // DB auto-increment
	private int id; 
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터 처리에 사용 
	private String content; //섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨 
	
	@ColumnDefault("0") // 스트링이 아니고 정수이기 때문에  홑따옴표 없이 0을 입력
	private String count; // 조회수 
	
	// DB는 오브젝트를 저장할 수 없기 때문에 FK등으로 연관 관계를 설정하나 자바는 오브젝트를 저장할 수 있기 때문에 FK를 사용하지 않음
	// 아래 어노테이션을 사용하여 ORM에서는 객체를 저장하는 방식으로 사용
	@ManyToOne // 연관 관계 설정 , Board = Many  , User = One , 한명의 User는 여러개의 게시글을 쓸 수 있다는 의미  
	@JoinColumn(name = "userId") // DB생성하면서 userId라는 FK 생성 
	private User user;  // ORM에서는 객체를 저장하는 방식으로 사용
	
	// Board = One, Reply = Many , mappedBy는 FK로 쓰인게 아니기 때문에 DB에 컬럼을 만들지 말라는 의미
	// FK는 Reply테이블에 있는 boareId이고 이를 참조하라는 의미
	// "board"는 Reply클래스에 있는 보드객체의 Join컬럼에 설정된 boardId의 오브젝트 선언 변수를 쓰면됨
	//@ManyToOne의 default전략은 (fetch=FetchType.EAGER)로 즉시 조인해서 가져오라는 의미 (명시하지 않으면 default로 작동)
	//@OneToMany의 default전략은 (fetch=FetchType.LAZY)로 나중에 필요한때에 조인해서 가져오라는 의미 (명시하지 않으면 default로 작동)
	@OneToMany(mappedBy = "board", fetch=FetchType.EAGER)  
	private List<Reply> reply;  // 단순히 게시글을 조회할때 하위에 딸려 있는 답변글을 가져오기 위한 코드다.
	
	@CreationTimestamp // 시간이 자동 입력된다.
	private Timestamp createDate;  //java.sql의 Timestamp사용
	

	
	
	
	
}
