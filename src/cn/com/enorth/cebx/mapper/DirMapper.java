package cn.com.enorth.cebx.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.enorth.cebx.vo.DirVo;

@Repository
public interface DirMapper {
	
	public void addDir(DirVo dirVo) throws Exception;
	
	public DirVo getDirById(int dirId) throws Exception;
	
	public List<DirVo> getDirVos() throws Exception;
}
