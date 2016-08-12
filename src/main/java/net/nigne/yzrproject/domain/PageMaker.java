package net.nigne.yzrproject.domain;

public class PageMaker {
	
	Long totalArticles = 0L;
	int startPage;
	int endPage;
	int totalPage;
	
	int pagePerBlock = 10;
	
	boolean next = false;
	boolean prev = false;
	
	Criteria criteria;
	
	public PageMaker(Criteria cri, Long total){
		criteria = cri;
		totalArticles = total;
		process();
	}
	
	public Long getTotalArticles() {
		return totalArticles;
	}

	public void setTotalArticles(Long totalArticles) {
		this.totalArticles = totalArticles;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPagePerBlock() {
		return pagePerBlock;
	}

	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	private void process(){
		// 전체 페이지개수
		int totalPages = (int)Math.ceil(totalArticles / (double)criteria.getArticlePerPage());
		
		// 요청한 페이지
		int currentPage = criteria.getPage(); // 11
		
		// 페이지 번호가 끝나는 숫자
		endPage = (int)(Math.ceil(currentPage / (double)pagePerBlock) * pagePerBlock);	// 10 , 20(totlaPages)
		
		// 페이지 번호가 시작되는 숫자
		startPage = endPage - pagePerBlock + 1;	// 1 , 11 , 21 , 31
		
		if(endPage > totalPages){
			endPage = totalPages;
		}
		
		// 이전 페이지 버튼 유무
		if(startPage > 1){
			prev = true;
		}
		
		// 다음 페이지 버튼 유무
		if(endPage < totalPages){
			next = true;
		}
		
	}
}
