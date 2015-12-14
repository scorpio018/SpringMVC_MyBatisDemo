package cn.com.enorth.cebx.service.file;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.com.enorth.cebx.consts.SystemConst;
import cn.com.enorth.cebx.mapper.FileMapper;
import cn.com.enorth.cebx.service.dir.DirService;
import cn.com.enorth.cebx.vo.DirVo;
import cn.com.enorth.cebx.vo.FileVo;
import cn.com.enorth.cebx.vo.Page;

@Service
public class FileService {
	
	@Resource
	private FileMapper fileMapper;
	
	@Resource
	private DirService dirService;
	
	public void addFile(FileVo fileVo, MultipartFile file) throws Exception {
		DirVo dirVo = dirService.getDirById(fileVo.getDirId());
		File f = new File(SystemConst.UPLOAD_BASE_PATH + dirVo.getDirId(), fileVo.getFileId() + "." + file.getOriginalFilename().split("\\.")[1]);
		if (!f.exists()) {
			f.mkdirs();
		}
		f.createNewFile();
		file.transferTo(f);
		fileMapper.addFile(fileVo);
	}
	
	public List<FileVo> getFileVosByPage(Page<FileVo> page) throws Exception {
		return fileMapper.getFileVosByPage(page);
	}
	
	public void deleteFile(FileVo fileVo) throws Exception {
		fileMapper.deleteFile(fileVo);
	}
}
