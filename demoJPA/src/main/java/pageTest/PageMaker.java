package pageTest;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageMaker {
	private int totalRowsCount; // 출력대상 전체 row 갯수
	private int displayPageNo = 3; // 1 Page 당 표시할 PageNo 갯수
	private int spageNo; // View에 표시할 첫 PageNo : 계산 필요
	private int epageNo; // View에 표시할 끝 PageNo : 계산 필요
	private int lastPageNo; // 출력 가능한 마지막 PageNo : 계산 필요
	private boolean prev; // 이전 PageBlock으로 이동
	private boolean next; // 다음 PageBlock으로 이동

	// bCheckList 또는 bPageList 등 요청명에 해당하는 url을 만들수 있도록 하기 위함
	private String mappingName;

	SearchCriteria cri;

// 필요값 set & 계산
// 1) Criteria
	public void setCri(SearchCriteria cri) {
		this.cri = cri;
	}

	public void setMappingName(String mappingName) {
		this.mappingName = mappingName;
	}

// 2 totalRowsCount
	// 전체 Rows 갯수 : Read from DB
	// 이 값을 이용해서 나머지 필요한 값 계산
	public void setTotalRowsCount(int totalRowsCount) {
		this.totalRowsCount = totalRowsCount;
		calcData();
	}

// 3 나머지 필요한 값 계산
	public void calcData() {
		// 3-1 spageNo, epageNo
		this.epageNo = (int) Math.ceil(cri.getCurrPage() / (double) displayPageNo) * displayPageNo;
		this.spageNo = (this.epageNo - displayPageNo) + 1;

		// 3-2 lastPageNo 계산 & epageNo의 적합성
		this.lastPageNo = (int) Math.ceil(this.totalRowsCount / (double) cri.getRowsPerPage());
		if (this.epageNo > this.lastPageNo)
			this.epageNo = this.lastPageNo;

		// 3-3 prev, next
		prev = this.spageNo == 1 ? false : true;
		next = this.epageNo == this.lastPageNo ? false : true;
	} // calcData

// 4) QueryString 자동 만들기
// ** ver01
// => QueryString 자동생성
// ?currPage=4&rowsPerPage=3
	public String makeQuery(int currPage) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("currPage", currPage)
				.queryParam("rowsPerPage", cri.getRowsPerPage()).build();
		return this.mappingName + uriComponents.toString();
	} // makeQuery

// ** ver02
// => makeQuery + search 조건 추가 (Paging 시에도 조건이 유지되도록 해줘야함)
// => ?currPage=1&rowsPerPage=5&searchType=title&keyword=Java
	public String searchQuery(int currPage) {
		// ** check 처리
		// => 배열 -> MultiValueMap 으로 -> UriComponents 의 queryParams에 적용
		// => MultiValueMap 생성
		MultiValueMap<String, String> checkMap = new LinkedMultiValueMap<String, String>();

		// => check에 선택값이 있는경우에만
		// 배열 check의 원소들을 checkMap 으로
		if (cri.getCheck() != null && cri.getCheck().length > 0) {
			for (String c : cri.getCheck()) {
				checkMap.add("check", c);
			} // for
		} else
			checkMap = null;

		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("currPage", currPage)
				.queryParam("rowsPerPage", cri.getRowsPerPage()).queryParam("searchType", cri.getSearchType())
				.queryParam("keyword", cri.getKeyword())
				// checkMap이 null이면 안만들어짐
				.queryParams(checkMap).build();
		return this.mappingName + uriComponents.toString();
	} // searchQuery

} // class
