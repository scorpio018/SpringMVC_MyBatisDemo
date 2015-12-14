package cn.com.enorth.cebx.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.com.enorth.cebx.consts.SystemConst;
import cn.com.enorth.cebx.service.dir.DirService;
import cn.com.enorth.cebx.service.file.FileService;
import cn.com.enorth.cebx.service.seq.SeqService;
import cn.com.enorth.cebx.util.CommonUtils;
import cn.com.enorth.cebx.vo.DirVo;
import cn.com.enorth.cebx.vo.FileVo;
import cn.com.enorth.cebx.vo.Page;

@Controller
public class CebxController {
	
	@Resource
	private SeqService seqService;
	
	@Resource
	private FileService fileService;
	
	@Resource
	private DirService dirService;
	
	@RequestMapping(value="/cebx/upload")
	public String uploadCebx(HttpServletRequest request, HttpServletResponse response,FileVo fileVo, @RequestParam(value = "cebxFile", required = false) MultipartFile cebxFile) throws Exception {
		String result = checkCanUpload(cebxFile);
		if (result != null) {
			request.setAttribute("error", result);
			return prepareUploadCebx(request, response);
		}
		// 将信息保存在
		fileVo.setFileId(seqService.haveSeq("sn_file_id").intValue());
		fileVo.setFileName(cebxFile.getOriginalFilename());
		fileVo.setUploadDate(new Timestamp(new Date().getTime()));
		fileVo.setUploadIp(CommonUtils.getIp(request));
		fileVo.setState(SystemConst.STATE_DEFAULT);
		fileService.addFile(fileVo, cebxFile);
		return "redirect:/cebx/showCebxList";
	}
	
	@RequestMapping(value="/cebx/prepareUpload")
	public String prepareUploadCebx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<DirVo> dirVos = dirService.getDirVos();
		request.setAttribute("dirVos", dirVos);
		return "uploadCebx";
	}
	
	@RequestMapping(value="/cebx/showCebxList")
	public String showCebxList(HttpServletRequest request, HttpServletResponse response, Page<FileVo> page, FileVo fileVo) throws Exception {
		fileVo.setState(SystemConst.STATE_DEFAULT);
		page.setVo(fileVo);
		List<FileVo> resultVos = null;
		try {
			resultVos = fileService.getFileVosByPage(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		page.setResults(resultVos);
		request.setAttribute("page", page);
		return "cebx";
	}
	
	@RequestMapping(value="/cebx/deleteCebx")
	public String deleteCebx(HttpServletRequest request, HttpServletResponse response, Page page, int fileId) throws Exception {
		FileVo fileVo = new FileVo();
		fileVo.setFileId(fileId);
		fileVo.setState(SystemConst.STATE_DELETE);
		fileService.deleteFile(fileVo);
		return showCebxList(request, response, page, new FileVo());
	}
	
	private String checkCanUpload(MultipartFile file) throws Exception {
		if (file == null) {
			return "没有找到上传文件，请重试";
		}
		
		if (!CommonUtils.isValidInfoFile(file.getOriginalFilename())) {
			return "文件扩展名有误，请重新上传";
		}
		
		return null;
	}
}
