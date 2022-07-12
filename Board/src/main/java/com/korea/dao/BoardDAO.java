package com.korea.dao;

import com.korea.dto.BoardDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    
        //DB연결
        private String url = "jdbc:oracle:thin:@localhost:1521:xe";
        private String id = "book_ex";
        private String pw = "1234";

        private Connection conn = null;
        private PreparedStatement pstmt = null;
        private ResultSet rs = null;

        //SingleTon Pattern
        private static BoardDAO instance;
        public static BoardDAO getInstance() {
            if(instance==null)
                instance = new BoardDAO();
            return instance;
        }
        private BoardDAO() {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conn = DriverManager.getConnection(url,id,pw);
                System.out.println("DBConnected...");

            }catch(Exception e) {e.printStackTrace();}
        }

        // 시작페이지, 끝페이지 번호 받아서 조회
        public List<BoardDTO> Select(int start, int end)
        {
            ArrayList<BoardDTO> list = new ArrayList();
            BoardDTO dto = null;
            try {

                String sql =
                        "select rownum rn, no , title, content, writer, regdate, pwd, count, ip, filename, filesize"
                                + " from"
                                + "("
                                + "    select /*+ INDEX_DESC (tbl_board PK_NO) */"
                                + "    rownum rn, no , title, content, writer, regdate, pwd, count, ip, filename, filesize "
                                + "    from tbl_board where rownum <= ?"
                                + ")"
                                + " where rn >= ?";

                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, end);         // 마지막 rownum
                pstmt.setInt(2, start);       // 시작 rownum
                rs=pstmt.executeQuery();

                while(rs.next())
                {
                    dto=new BoardDTO();
                    dto.setNo(rs.getInt("no"));
                    dto.setTitle(rs.getNString("title"));
                    dto.setContent(rs.getNString("content"));
                    dto.setWriter(rs.getString("writer"));
                    dto.setRegdate(rs.getString("regdate"));
                    dto.setPwd(rs.getString("pwd"));
                    dto.setIp(rs.getString("ip"));
                    dto.setFilename(rs.getString("filename"));
                    dto.setFilesize(rs.getString("filesize"));
                    dto.setCount(rs.getInt("count"));
                    list.add(dto);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {rs.close();} catch (Exception e) {e.printStackTrace();}
                try {pstmt.close();} catch (Exception e) {e.printStackTrace();}
            }
            return list;
        }
        
        // 모든 게시물개수 조회 (overloading)
        public int getTotalCount() {
        	int result=0;
        	try {
				pstmt = conn.prepareStatement("select count(*) from tbl_board");
				rs = pstmt.executeQuery();
				rs.next();
				result = rs.getInt(1);
        		
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				   try {rs.close();} catch (Exception e) {e.printStackTrace();}
	               try {pstmt.close();} catch (Exception e) {e.printStackTrace();}
			}
        	return result;
        }
}

