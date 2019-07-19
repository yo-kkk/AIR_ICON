package search.models;

import java.sql.SQLException;
import java.util.List;

import models.dto.PageDTO;
import models.dto.SearchDTO;
import models.dto.SearchListDTO;

public interface SearchDAO {
	//public List<ImagesDTO> getImages 
	List<SearchListDTO> searchResult(PageDTO pageDTO, SearchDTO searchDTO) throws SQLException;
	List<SearchListDTO> searchNewResult_no(PageDTO pageDTO, SearchDTO searchDTO) throws SQLException;
	long getTotalCount(SearchDTO searchDTO) throws SQLException;
	long getTotalCountRe(SearchDTO searchDTO) throws SQLException;
	
	//검색바 필터 메소드
	long getTotalCountReFilter(SearchDTO searchDTO, String col) throws SQLException;
	List<SearchListDTO> searchNewResult_noFilter(PageDTO pageDTO, SearchDTO searchDTO, String col) throws SQLException;
	
	//메인 필터 메소드
	long getTotalCountFilter(SearchDTO searchDTO, String col) throws SQLException;
	List<SearchListDTO> searchResultFilter(PageDTO pageDTO, SearchDTO searchDTO, String col) throws SQLException;

}
