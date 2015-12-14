package cn.com.enorth.cebx.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.enorth.cebx.vo.FileVo;
import cn.com.enorth.cebx.vo.Page;

@Repository
public interface FileMapper {
	
	public void addFile(FileVo fileVo) throws Exception;
	
	/**
	 * 分页查询文件列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<FileVo> getFileVosByPage(Page<FileVo> page) throws Exception;
	
	public void deleteFile(FileVo fileVo) throws Exception;
}
