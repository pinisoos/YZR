package net.nigne.yzrproject.domain;

public class Criteria {
	
	int page;
	int articlePerPage = 10;
	
	public int getStartPage(){
		return (page-1) * articlePerPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getArticlePerPage() {
		return articlePerPage;
	}

	public void setArticlePerPage(int articlePerPage) {
		this.articlePerPage = articlePerPage;
	}
	
}
