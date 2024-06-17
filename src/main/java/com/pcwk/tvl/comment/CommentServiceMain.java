package com.pcwk.tvl.comment;

public class CommentServiceMain {
	CommentService service;
	CommentDTO comment;
	public CommentServiceMain() {
		service = new CommentService();
		comment = new CommentDTO(1, 1, "1kqD993", "댓글내용", "사용X", "사용X");
	}
	
	public static void main(String[] args) {
	
	}

}
