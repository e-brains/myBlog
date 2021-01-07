/**
 *  board.js
 */
let index = {
	
	init: function(){
		$("#btn-save").on("click", ()=>{   
			this.save();
		} );
		
		$("#btn-delete").on("click", ()=>{   
			this.del();
		} );
		
		$("#btn-update").on("click", ()=>{   
			this.update();
		} );

		$("#btn-reply-save").on("click", ()=>{   
			this.replySave();
		} );
	},
		
    save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),   
			contentType: "application/json; charset=utf-8", 
			dataType: "json" 
			
		}).done(function(resp){   
			alert("글쓰기가 완료되었습니다.");
			location.href = "/";
			
		}).fail(function(){  //요청이 실패 시 수행
			alert(JSON.stringify(error));
			
		});   
    },
 
     update: function() {
     	let id =$("#id").val();
 
 		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		
		$.ajax({
			type: "PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data),   
			contentType: "application/json; charset=utf-8", 
			dataType: "json" 
		}).done(function(resp){   
			alert("글수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(){  //요청이 실패 시 수행
			alert(JSON.stringify(error));
			
		});  
    },
 
/* Dto 미적용 */
/* 
     replySave: function() {
		let data = {
			content: $("#reply-content").val()
		};
		
		let boardId = $("#boardId").val();
				
		// URI에 $를 사용 시 싱글 쿼테이션 사용  url: '/api/board/${boardId}/reply'  
		$.ajax({
			type: "POST",
			url: "/api/board/" + boardId + "/reply",   
			data: JSON.stringify(data),   
			contentType: "application/json; charset=utf-8", 
			dataType: "json" 
			
		}).done(function(resp){   
			alert("댓글작성이 저장되었습니다.");
			location.href = "/board/"+boardId;
			
		}).fail(function(){  //요청이 실패 시 수행
			alert(JSON.stringify(error));
			
		});  
    },*/

	/* Dto 적용 data는 ReplySaveRequestDto에 맞춰서 보낸다*/ 
     replySave: function() {
		let data = {
			userId: $("#userId").val(),
			boardId: $("#boardId").val(),
			content: $("#reply-content").val()
		};
				
		// URI에 $를 사용 시 싱글 쿼테이션 사용  url: '/api/board/${boardId}/reply'  
		$.ajax({
			type: "POST",
			url: "/api/board/" + data.boardId + "/reply",
			data: JSON.stringify(data),   
			contentType: "application/json; charset=utf-8", 
			dataType: "json" 
			
		}).done(function(resp){   
			alert("댓글작성이 저장되었습니다.");
			location.href = "/board/"+data.boardId;
			
		}).fail(function(){  //요청이 실패 시 수행
			alert(JSON.stringify(error));
		});  
    },

	/* 댓글 식제 */ 
	//replyDelete는 onClick함수이기 때문에 리스너 즉 init: 에 $("#btn-reply-save").on("click", ()=>을 만들지 않는다.
     replyDelete: function(boardId, replyId) {
		$.ajax({
			type: "DELETE",
			url: "/api/board/" + boardId + "/reply/" + replyId,
			dataType: "json" 
			
		}).done(function(resp){   // 요청 성공
			alert("선택하신 댓글이 삭제되었습니다.");
			location.href = "/board/"+ boardId;
			
		}).fail(function(){  //요청 실패 
			alert(JSON.stringify(error));
		});  
    },

	//게시글 삭제
    del: function() {
		let id =$("#id").text();
		$.ajax({
			type: "DELETE",
			url: "/api/board/"+id,
			dataType: "json" 
		}).done(function(resp){   
			alert("삭제가 완료되었습니다.");
			location.href = "/";
		}).fail(function(){  //요청이 실패 시 수행
			alert(JSON.stringify(error));
		});  
    } 
}

index.init();

