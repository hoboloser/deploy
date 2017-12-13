package com.zhicall.op.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zhicall.op.entity.DeployJob;

public interface DeployJobMapper {

	@Select("select id,type,uuid,email,mobile,result,remark,job_time as jobTime,create_time as createTime from op_auto_job where uuid=#{uuid} order by job_time desc")
	List<DeployJob> getList(String uuid);
	
	@Select("select id,type,uuid,email,mobile,result,remark,job_time as jobTime,create_time as createTime from op_auto_job where flag=1 order by job_time desc")
	List<DeployJob> getAllList();
	
	@Select("select id,type,uuid,email,mobile,result,remark,job_time as jobTime,create_time as createTime from op_auto_job where id=#{id}")
	DeployJob getDetail(long id);
	
	@Insert("insert into op_auto_job (type,job_time,create_time,uuid,email,mobile,flag) "
			+ "values"
			+ " (#{type},#{jobTime},#{createTime},#{uuid},#{email},#{mobile},#{flag})")
	void insert(DeployJob deployJob);
	
	@Update("update op_auto_job set flag=0,update_time=#{updateTime},result=#{result},remark=#{remark} where id=#{id}")
	void update(@Param("updateTime") Date updateTime, @Param("result") String result, @Param("id") Long id, @Param("remark") String remark);
}
