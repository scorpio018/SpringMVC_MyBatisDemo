package cn.com.enorth.cebx.service.seq;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.enorth.cebx.mapper.SeqMapper;

@Service
public class SeqService {

	@Resource
	private SeqMapper seqDao;

	public Long haveSeq(String seqName) throws Exception {
		long val = seqDao.selectCurValBySeqName(seqName);
		if (val == -1) {
			// throw new SQLException("序列"+seqName+"不存在");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("seqName", seqName);
			map.put("initValue", new Long(1000));
			map.put("curValue", new Long(1001));
			seqDao.insertSeq(map);
			long result = 1000L;
			return result;
		} else if (val == -2) {
			throw new SQLException("非法请求");
		} else {
			return val;
		}
	}
}
