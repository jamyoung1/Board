package com.korea.service;

import java.util.List;

import org.mindrot.bcrypt.BCrypt;

import com.korea.dao.BoardDAO;
import com.korea.dto.BoardDTO;

public class BoardService {
	
	BoardDAO dao = BoardDAO.getInstance();

	
	// 싱글 톤 패턴
		private static BoardService instance=null;
		public static BoardService getInstance() {
			if(instance==null)
				instance = new BoardService();
			return instance;
		}
		
		private BoardService() {}
		
		//컨트롤러가 준다.
		public List<BoardDTO> getBoardList(int start,int end) {
			return dao.Select(start, end);
		}
		
		public int getTotalCnt() {
			return dao.getTotalCount();
		}
}

