package cn.com.enorth.cebx.service.dir;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.enorth.cebx.mapper.DirMapper;
import cn.com.enorth.cebx.vo.DirVo;

@Service
public class DirService {

	@Resource
	private DirMapper dirMapper;
	
	public void addDir(DirVo dirVo) throws Exception {
		dirMapper.addDir(dirVo);
	}
	
	public DirVo getDirById(int dirId) throws Exception {
		return dirMapper.getDirById(dirId);
	}
	
	public List<DirVo> getDirVos() throws Exception {
		return dirMapper.getDirVos();
	}
}
