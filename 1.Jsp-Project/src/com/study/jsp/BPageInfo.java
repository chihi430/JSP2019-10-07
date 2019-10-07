package com.study.jsp;

public class BPageInfo{
	
	int totalCount; // �� �Խù��� ����
	int listCount; // �� �������� ������ �Խù��� ����
	int totalPage; // �� ������
	int curPage; // ���� �信��
	int pageCount; // �ϴܿ� ������ ������ ����Ʈ�� ����
	int startPage; // ���� ������
	int endPage; // �� ������
	int totalreplyCount; // �� ���� ����
	
	public BPageInfo() {

	}
		
	
	public BPageInfo(int totalCount, int listCount, int totalPage, int curPage, int pageCount, int startPage,
			int endPage, int totalreplyCount) {
		super();
		this.totalCount = totalCount;
		this.listCount = listCount;
		this.totalPage = totalPage;
		this.curPage = curPage;
		this.pageCount = pageCount;
		this.startPage = startPage;
		this.endPage = endPage;
		this.totalreplyCount = totalreplyCount;
	}


	public int getTotalreplyCount() {
		return totalreplyCount;
	}

	public void setTotalreplyCount(int totalreplyCount) {
		this.totalreplyCount = totalreplyCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
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
	
	
}