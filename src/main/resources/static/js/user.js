/**
 *  user.js
 */
let index = {
	
	init: function(){
		// function(){}를  ()=> {}로 표현한 이유는 function(){}을 그냥 사용하면 펑션 내부의 
		// this가 윈도우  this를 가리키게 된다.
		// 굳이 function(){}을 사용하려면  init펑션 밖에다 let _this = this; 를 선언해 주고 
		// _this.save();로 사용해야 save펑션이 제대로 호출된다.
		$("#btn-save").on("click", ()=>{   
			this.save();
		} );
		
		$("#btn-login").on("click", ()=>{   
			this.login();
		} );
	},
		
    save: function() {
        //alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		
		//console.log(data);
		
		//ajax호출 시 default가 비동기 호출
		//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여  insert 요청
		//ajax통신 성공 후 서버가 json을 리턴해 주면 dataType을 정의해 주지 않더라도 
		//자동으로 자바오브젝트로 변환해 준다
		$.ajax({
			type: "POST",
			url: "/api/user/join",
			data: JSON.stringify(data),   //http body 데이터
			contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지 설정 (MIME)
			dataType: "json" //서버로 부터 응답이 왔을때 기본적으로 문자열인데 (생긴게 json이라면) => javascript object로 변경해 준다.
			
		}).done(function(resp){   //요청이 정상처리 되어서 끝나는때 수행
			alert("회원가입이 완료되었습니다.");
			//alert(resp);
			//console.log(resp);
			location.href = "/";
			
		}).fail(function(){  //요청이 실패 시 수행
			alert(JSON.stringify(error));
			
		});  
		 
    },
   
    login: function() {
        //alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
		};
		
		//console.log(data);
		
		//ajax호출 시 default가 비동기 호출
		//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여  insert 요청
		//ajax통신 성공 후 서버가 json을 리턴해 주면 dataType을 정의해 주지 않더라도 
		//자동으로 자바오브젝트로 변환해 준다
		$.ajax({
			type: "POST",
			url: "/api/user/login",
			data: JSON.stringify(data),   //http body 데이터
			contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지 설정 (MIME)
			dataType: "json" //서버로 부터 응답이 왔을때 기본적으로 문자열인데 (생긴게 json이라면) => javascript object로 변경해 준다.
			
		}).done(function(resp){   //요청이 정상처리 되어서 끝나는때 수행
			alert("로그인이 완료되었습니다.");
			//alert(resp);
			//console.log(resp);
			location.href = "/";
			
		}).fail(function(){  //요청이 실패 시 수행
			alert(JSON.stringify(error));
			
		});  
		 
    }	   
   
   
   
}

index.init();

