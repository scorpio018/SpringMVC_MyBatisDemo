package cn.com.enorth.cebx.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface SeqMapper {

	public long selectCurValBySeqName(String seqName) throws Exception;
	
	public void updateBySeqName(String seqName) throws Exception;
	
	public void insertSeq(Map<String, Object> map) throws Exception;
}
