package cn.com.infosouth.arj21.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.com.infosouth.arj21.domain.ShowMultiCsv;

@Mapper
public interface ShowMultiCsvMapper {

	int selectCount(ShowMultiCsv showMultiCsv);

	List<ShowMultiCsv> findList(int pageSize, int pageNum, ShowMultiCsv showMultiCsv);

}
