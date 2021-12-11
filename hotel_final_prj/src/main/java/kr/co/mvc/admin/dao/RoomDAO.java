package kr.co.mvc.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kr.co.mvc.admin.vo.OtherImgVO;
import kr.co.mvc.admin.vo.RoomVO;

@Component
public class RoomDAO {

	@Autowired(required = false)
	private JdbcTemplate jt;
	
	/**
	 * 등록된 모든 room 상세정보 조회
	 */
	public List<RoomVO> selectRoomInfo(String rName) throws DataAccessException {
		List<RoomVO> roomList = null;

		StringBuilder select = new StringBuilder("select * from room");
		//파라미터가 들어왔을 때 조건문 추가 
		if (rName != null) { 
			select.append("		where room_no = (select room_no from room where r_name='")
					.append(rName)
					.append("')");
		} // end if
 
		roomList = jt.query(select.toString(), new RowMapper<RoomVO>() {
			@Override
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
				rmVO.setGuestNum(rs.getInt("max_guest"));
				return rmVO;
			}//mapRow
		});

		//room_no대로 정렬
		if(roomList != null) {
			Collections.sort(roomList, new CompareRNoAsc());
		}
		return roomList;
	}// selectRoomInfo
	
	/**
	 * List<RoomVO>에서 room no 순서대로 정렬하는 inner Class
	 * @author user
	 */
	public class CompareRNoAsc implements Comparator<RoomVO> {
		@Override
		public int compare(RoomVO o1, RoomVO o2) {
			return o1.getRoomNum().compareTo(o2.getRoomNum());
		}
	}// CompareRNoAsc
	
	
	/**
	 * images 테이블에서 룸별 이미지 조회
	 * @param rName
	 * @return
	 * @throws DataAccessException
	 */
	public List<OtherImgVO> selectOtherImg(String rName) throws DataAccessException {
		List<OtherImgVO> imgList = null;

		StringBuilder select = new StringBuilder();

		select.append(" 	select * 	from   images")
				.append(" 	where  room_no = (select room_no from room where r_name=?)");
		
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
	
	
	
}//class
