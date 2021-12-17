package kr.co.mvc.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kr.co.mvc.admin.vo.OtherImgVO;
import kr.co.mvc.admin.vo.RoomVO;
import kr.co.mvc.user.vo.ImagesVO;
import kr.co.mvc.user.vo.UserMemberVO;
import kr.co.mvc.user.vo.UserRoomVO;


@Component
public class UserReservationDAO {

	@Autowired(required = false)
	private JdbcTemplate jt;
	
	/**
	 * ��ϵ� ��� room ������ ��ȸ
	 * @param rName
	 * @param rStatus
	 * @return
	 * @throws DataAccessException
	 */
	public List<RoomVO> selectRoomInfo(String rName, String rStatus) throws DataAccessException {
		List<RoomVO> roomList = null;

		StringBuilder select = new StringBuilder("select * from room");
		
		//�Ķ���Ͱ� ������ �� ���ǹ� �߰� 
		if(rName!=null && rStatus !=null){
			select.append("		where	r_name='")
					.append(rName)
					.append("'	and	")
					.append("r_status='")
					.append(rStatus)
					.append("'");
		}//end if
		
		if (rName != null && rStatus == null) { 
			select.append("		where	r_name='")
			.append(rName)
			.append("'");
		} // end if
		
		if (rName == null && rStatus != null) { 
			select.append("		where	r_status='")
			.append(rStatus)
			.append("'");
		} // end if

		roomList = jt.query(select.toString(), new selectRoomInfo());



		//room_no��� ����
		if(roomList!=null) {
		Collections.sort(roomList, new CompareRNoAsc());
		}
		
		return roomList;
	}// selectRoomInfo
	
	/**
	 * List<RoomVO>���� room no ������� �����ϴ� inner Class
	 * @author user
	 */
	public class CompareRNoAsc implements Comparator<RoomVO> {
		@Override
		public int compare(RoomVO o1, RoomVO o2) {
			return o1.getRoomNum().compareTo(o2.getRoomNum());
		}
	}// class
	
	/* selectRoomInfo���� ��ȸ�� ���� ������ ���� inner class
	 * @author user
	 */
	public class selectRoomInfo implements RowMapper<RoomVO> {
		public RoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			RoomVO rmVO = new RoomVO();
			rmVO.setrStatus(rs.getString("r_status"));
			rmVO.setRoomNum(rs.getString("room_no"));
			rmVO.setRoomName(rs.getString("r_name"));
			rmVO.setMainDesc(rs.getString("description"));
			rmVO.setType(rs.getString("bed_type"));
			rmVO.setRoomSize(rs.getString("r_size"));
			rmVO.setView(rs.getString("r_view"));
			rmVO.setChkIn(rs.getString("chkin_time"));
			rmVO.setChkOut(rs.getString("chkout_time"));
			rmVO.setSpecialServ(rs.getString("service"));
			rmVO.setGeneralAmn(rs.getString("amnt_gen"));
			rmVO.setBathAmn(rs.getString("amnt_bath"));
			rmVO.setOtherAmn(rs.getString("amnt_other"));
			rmVO.setMoreInfo(rs.getString("more_info"));
			rmVO.setImg(rs.getString("main_img"));
			rmVO.setInputDate(rs.getString("input_date"));
			String price = new DecimalFormat("#,###").format(rs.getInt("price"));
			rmVO.setPrice(price);
			rmVO.setGuestNum(rs.getString("max_guest"));
			
			return rmVO;
		}// mapRow

	}// selectRes
	

//-----------------------------------------------------------------------------	
	/**
	 * images ���̺��� �뺰 �̹��� ��ȸ
	 * @param rName
	 * @return
	 * @throws DataAccessException
	 */
	public List<OtherImgVO> selectOtherImg(String rName) throws DataAccessException {
		List<OtherImgVO> imgList = null;

		StringBuilder select = new StringBuilder();

		select.append(" 	select * 	from   images")
				.append(" 	where  room_no = (select room_no")
									.append(" 	 from room	")
									.append(" 	 where r_name=?)");	
		
			imgList = jt.query(select.toString(), new Object[] {rName}, 
					new RowMapper<OtherImgVO>() {
						public OtherImgVO mapRow(ResultSet rs, int rowNum) throws SQLException  {
							OtherImgVO imgVO = new OtherImgVO();
							imgVO.setImgNo(rs.getInt("img_no"));
							imgVO.setRoomNo(rs.getInt("room_no"));
							imgVO.setImgSrc(rs.getString("img_src"));
							return imgVO;
						}//mapRow
			});

		return imgList;
	}// selectOtherImg
	
//--------------------------------------------------------------------------------------------------
	
	public List<UserRoomVO> selectAvaileReserve(String start_date, String end_date, int adult, int child)
			throws SQLException{
		
		List<UserRoomVO> rList = null;
	
				   
		//��������
		StringBuilder selectAvailReserve = new StringBuilder();
		selectAvailReserve
		.append("	select 	room_no, room.r_status, room.r_name, room.max_guest, room.description, room.price, room.main_img	")
		.append("	from room	")
		.append("	where room_no not in ")
		.append("	(select room.room_no	")
		.append("	from  room room, reservation res	")
		.append("	where (res.room_no=room.room_no)	")
		.append("	and (  (to_date(chkin_date,'yyyy.mm.dd')<=to_date(?,'yyyy.mm.dd')	")
		.append("	and to_date(chkout_date,'yyyy.mm.dd') >= to_date(?,'yyyy.mm.dd')))	")
		.append(" 	)	")
		.append("	and ((to_number(?) + nvl(to_number(?),0) ) <= room.max_guest)	")
		.append("   and(room.r_status = 'Y')"	);
		// ��¥�� �ش��ϴ� ���� ����,
		// �ִ� �ο������� �۰ų� ���� ��
		// adult, child �Ķ���͸� String������ �޾ƿ��� ������, ���갡���� number�� ����ȯ�Ѵ�.
		// ��� 0 ���϶��� ����Ͽ� nvl ���
		
		
		rList = jt.query(selectAvailReserve.toString(), new Object[] { start_date, end_date, adult, child },
				new RowMapper<UserRoomVO>() {

					@Override
					public UserRoomVO mapRow(ResultSet rs, int rowCnt) throws SQLException {
						UserRoomVO rVO = new UserRoomVO();
						rVO.setR_name(rs.getString("r_name"));
						rVO.setRoom_no(rs.getInt("room_no"));
						rVO.setR_status(rs.getString("r_status"));
						rVO.setMax_guest(rs.getInt("max_guest"));
						rVO.setDescription(rs.getString("description"));
						rVO.setPrice(rs.getInt("price"));
						rVO.setMain_img(rs.getString("main_img"));
						return rVO;
					}
		});

		return rList;	
	}//selectAvailReserve
	
	
	/**
	 * �ϳ��� �������� ������ �ϳ��� ���������ε� �� List�� ��ȯ���̿���? �������� �־�� room_no = ?�� PK�ƴϿ���? �½����� �׷� ��ȸ����� �ϳ��� ������
	 * @param room_no
	 * @return
	 * @throws SQLException
	 */
	public UserRoomVO selectRoomInfo( int room_no ) throws SQLException{

		UserRoomVO rList = null;
		
		// 3. ���� ����
		String select = "select r_name, description, bed_type, max_guest, r_size, chkin_time, chkout_time, r_view, "
				+ "amnt_gen, amnt_bath, amnt_other, main_img, price from room where room_no = ?";
		
		// interface�� anonymous inner class�� �����Ͽ� �� �ȿ��� ��ȸ����� VO�� �Ҵ�
		rList = jt.queryForObject(select, new Object[] { room_no }, 
				new RowMapper<UserRoomVO>() {
				public UserRoomVO mapRow(ResultSet rs, int rowNum) throws SQLException{
					UserRoomVO rv = new UserRoomVO();
						// ResultSet�� ����Ͽ� ��ȸ����� VO�� ����
						rv.setR_name(rs.getString("r_name"));
						rv.setDescription(rs.getString("description"));
						rv.setBed_type(rs.getString("bed_type"));
						rv.setMax_guest(rs.getInt("max_guest"));
						rv.setR_view(rs.getString("r_view"));
						rv.setR_size(rs.getString("r_size"));
						rv.setChkin_time(rs.getString("chkin_time"));
						rv.setChkout_time(rs.getString("chkout_time"));
						rv.setAmnt_gen(rs.getString("amnt_gen"));
						rv.setAmnt_bath(rs.getString("amnt_bath"));
						rv.setAmnt_other(rs.getString("amnt_other"));
						rv.setMain_img(rs.getString("main_img"));
						rv.setPrice(rs.getInt("price"));

						// ��ȸ����� ������ dv ��ȯ
						return rv;
					}
				});
		rList.setRoom_no(room_no);

		return rList;
	}//selectRoomInfo
	
	
	/**
	 * ��� ��ϵ� ���� ���� 
	 * ĳ���� �ε������� ���� �� ���
	 * @param room_no
	 * @return
	 * @throws EmptyResultDataAccessException
	 * @throws IncorrectResultSizeDataAccessException
	 * @throws BadSqlGrammarException
	 */
	public int selectCountImg( int room_no ) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException, BadSqlGrammarException{
		int cnt = 0;

		String selectCountImg = "select count(img_src) from images where room_no = ? ";
		
		cnt = jt.queryForObject(selectCountImg, new Object[] { room_no }, int.class);

		return cnt;
	}//selectCountImg
	
	/**
	 * ��ѹ��� �����̹����� ��������(ĳ����)
	 * @param room_no
	 * @return
	 * @throws SQLException
	 */
	public List<ImagesVO> selectSubImages( int room_no ) throws SQLException {
		List<ImagesVO> list = null;
		
		// 3. ��������
		StringBuilder selectImg = new StringBuilder();
		selectImg.append
		("	select img_src").append("	from images	");
		
		// Dynamic query
		if( room_no != 0) {
			selectImg.append("	where room_no = ?	");
		}//end if
			
		if( room_no == 0) {
			
			list = jt.query(selectImg.toString(), new SelectImg() );
		}else {
			
			list = jt.query(selectImg.toString(), new Object[] { Long.valueOf(room_no) }, new SelectImg() );
		}//end else

		return list;
	}//selectImages
	
	/////////////////////////////////////////////////
	public class SelectImg implements RowMapper<ImagesVO>{
		public ImagesVO mapRow(ResultSet rs, int rowNum) throws SQLException{
			ImagesVO iv = new ImagesVO();
			iv.setImg_src(rs.getString("img_src"));
			return iv;
		}//mapRow
	}

	
//--------------------------------------------------------------------------------
	public UserMemberVO selectMemAllInfo (String id) throws SQLException{
		UserMemberVO mv = null;
		
		String selectMem = "select * from member where id = ?";
		
		// interface�� anonymous inner class�� �����Ͽ� �� �ȿ��� ��ȸ����� VO�� �Ҵ�
		mv = jt.queryForObject(selectMem, new Object[] { id }, 
			new RowMapper<UserMemberVO>() {
				public UserMemberVO mapRow(ResultSet rs, int rowNum) throws SQLException{
					UserMemberVO mv = new UserMemberVO();
					mv.setId(rs.getString("id"));
					mv.setEmail(rs.getString("email"));
					mv.setPass(rs.getString("pass"));
					mv.setEname_fst(rs.getString("ename_fst"));
					mv.setEname_lst(rs.getString("ename_lst"));
					mv.setKname(rs.getString("kname"));
					mv.setBirth_year(rs.getString("birth_year"));
					mv.setTel(rs.getString("tel"));
					mv.setReq_agree(rs.getString("req_agree"));
					mv.setOpt_agree(rs.getString("opt_agree"));
					// ��ȸ����� ��ȯ
					return mv;
				}
			});
		return mv;
	}//selectMemInfo
	
}//class
