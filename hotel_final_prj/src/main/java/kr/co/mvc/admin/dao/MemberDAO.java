package kr.co.mvc.admin.dao;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kr.co.mvc.admin.vo.ChkInDateVO;
import kr.co.mvc.admin.vo.MemberPagingVO;
import kr.co.mvc.admin.vo.MemberVO;
import kr.co.sist.util.cipher.DataDecrypt;

@Component
public class MemberDAO {

	@Autowired(required = false)
	private JdbcTemplate jt;

	/**
	 * ����ȸ�� ��ȸ
	 * @param id
	 * @param startNum
	 * @param endNum
	 * @return
	 * @throws DataAccessException
	 */
	public List<MemberVO> selectActiveMember(String id, int startNum, int endNum) throws DataAccessException {
		List<MemberVO> list = null;
		
		StringBuilder selectMember = new StringBuilder();
		selectMember.append("	select 	id,kname,birth_year,tel,email,ename_fst,ename_lst, to_char(join_date,'yyyy.mm.dd') join_date")
				.append("	from (")
				.append("		select 	row_number() over(order by join_date desc)r_num,")
				.append("				id,kname,birth_year,tel,email,ename_fst,ename_lst,join_date")
				.append("		from 	member		where   m_status= 'Y' ");

		if (id != null && !"".equals(id)) {// id�� �������� ���� �߰�
			selectMember.append("	and	id='").append(id).append("'");
		} // end if

		selectMember.append(") where r_num between ? and ?");

		list = jt.query(selectMember.toString(), new Object[] {startNum, endNum},new RowMapper<MemberVO>() {
			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberVO mVO = new MemberVO();
				try {
					DataDecrypt dd = new DataDecrypt("AbcdEfgHiJkLmnOpQ");
					mVO.setId(rs.getString("id"));
					mVO.setKname(dd.decryption(rs.getString("kname")));
					mVO.setBirth_year(dd.decryption(rs.getString("birth_year")));
					mVO.setTel(dd.decryption(rs.getString("tel")));
					mVO.setEmail(dd.decryption(rs.getString("email")));
					mVO.setEname_fst(dd.decryption(rs.getString("ename_fst")));
					mVO.setEname_lst(dd.decryption(rs.getString("ename_lst")));
					mVO.setJoin_date(rs.getString("join_date"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (GeneralSecurityException e) {
					e.printStackTrace();
				}//end catch
				return mVO;
			}// mapRow
		});
		return list;
	}// selectActiveMember
	
	
	/**
	 * Ż��ȸ�� ��ȸ
	 * @param id
	 * @param startNum
	 * @param endNum
	 * @return
	 * @throws DataAccessException
	 */
	public List<MemberVO> selectInactiveMember(String id,int startNum, int endNum) throws DataAccessException {
		List<MemberVO> list = null;

		StringBuilder selectMember = new StringBuilder();
		
		selectMember.append("	select 	id,kname, to_char(out_date,'yyyy.mm.dd') out_date ")
				.append("	from (")
				.append("		select 	row_number() over(order by out_date desc)r_num,")
				.append("				id,kname,out_date")
				.append("		from 	member		where   m_status= 'N' ");

		if (id != null && !"".equals(id)) {// id�� �������� ���� �߰�
			selectMember.append("	and	id='").append(id).append("'");
		} // end if

		selectMember.append(") where r_num between ? and ?");

		list = jt.query(selectMember.toString(), new Object[] {startNum, endNum},new RowMapper<MemberVO>() {

			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberVO mVO = new MemberVO();
				try {
					DataDecrypt dd = new DataDecrypt("AbcdEfgHiJkLmnOpQ");
					mVO.setId(rs.getString("id"));
					mVO.setKname(dd.decryption(rs.getString("kname")));
					mVO.setOut_date(rs.getString("out_date"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (GeneralSecurityException e) {
					e.printStackTrace();
				}//end catch
				return mVO;
			}// mapRow
		});
		return list;
	}// selectInactiveMember

	/**
	 * ���������̼ǿ� ����� ��ü ���ڵ� ��ȸ
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public int selectAllMemberCnt(String id, String status) throws DataAccessException {
		int allMemCnt = 0;
		
		StringBuilder selectMember = new StringBuilder();
		selectMember.append("		select 	count(id)")
				.append("		from 	member		where   m_status=?");

		if (id != null && !"".equals(id)) {// id�� �������� ���� �߰�
			selectMember.append("	and	id='").append(id).append("'");
		} // end if

		allMemCnt = jt.queryForObject(selectMember.toString(), new Object[] {status} ,Integer.class);
		
		return allMemCnt;
	}//selectAllMemberCnt
	
	
	public int updateMemberProcess(String id) {
		int cnt = 0;
		return cnt;
	}
}// class
