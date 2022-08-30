package community.model;

import java.sql.Date;

public class CommunityDTO {
	
	private int cum_Id;
	private String cum_Main;
	private String cum_Title;
	private String cum_Content;
	private Date cum_WriteDay;
	private int cum_Like;
	private int cum_View;
	private int cum_Report;
	
	public int getCum_Id() {
		return cum_Id;
	}
	
	public void setCum_Id(int cum_Id) {
		this.cum_Id = cum_Id;
	}
	
	public String getCum_Main() {
		return cum_Main;
	}
	
	public void setCum_Main(String cum_Main) {
		this.cum_Main = cum_Main;
	}
	
	public String getCum_Title() {
		return cum_Title;
	}
	
	public void setCum_Title(String cum_Title) {
		this.cum_Title = cum_Title;
	}
	
	public String getCum_Content() {
		return cum_Content;
	}
	
	public void setCum_Content(String cum_Content) {
		this.cum_Content = cum_Content;
	}
	
	public Date getCum_WriteDay() {
		return cum_WriteDay;
	}
	
	public void setCum_WriteDay(Date cum_WriteDay) {
		this.cum_WriteDay = cum_WriteDay;
	}
	
	public int getCum_Like() {
		return cum_Like;
	}
	
	public void setCum_Like(int cum_Like) {
		this.cum_Like = cum_Like;
	}
	
	public int getCum_View() {
		return cum_View;
	}
	
	public void setCum_View(int cum_View) {
		this.cum_View = cum_View;
	}
	
	public int getCum_Report() {
		return cum_Report;
	}
	
	public void setCum_Report(int cum_Report) {
		this.cum_Report = cum_Report;
	}

	@Override
	public String toString() {
		return "CommunityDTO [cum_Id=" + cum_Id + ", cum_Main=" + cum_Main + ", cum_Title=" + cum_Title
				+ ", cum_Content=" + cum_Content + ", cum_WriteDay=" + cum_WriteDay + ", cum_Like=" + cum_Like
				+ ", cum_View=" + cum_View + ", cum_Report=" + cum_Report + "]";
	}
	
	
	
	
}


