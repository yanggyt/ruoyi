package cn.com.infosouth.arj21.service;


import cn.com.infosouth.arj21.domain.ShowMultiCsv;
import cn.com.infosouth.arj21.utils_oneselef.Page;

public interface IShowMultiCsvService {


	Page<ShowMultiCsv> findPage(int i, int j, ShowMultiCsv showMultiCsv);

}
