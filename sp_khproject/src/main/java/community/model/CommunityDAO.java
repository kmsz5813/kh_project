package community.model;

import org.apache.ibatis.session.SqlSession;

public class CommunityDAO {
	private SqlSession session = null;
	private String mapper = "communityMapper.%s";
	
	public CommunityDAO() {
		this.session = DBConn.getSqlSession();
	}
	
	public int getNextSeq() {
		String mapperId = String.format(mapper, "getNextSeq");
		int seq = session
	}
	
	
}

