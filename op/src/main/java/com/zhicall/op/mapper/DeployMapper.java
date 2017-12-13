package com.zhicall.op.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.zhicall.op.entity.DeployInfo;

public interface DeployMapper {

	@Select("select * from op_config")
	List<DeployInfo> getList();

	@Select("select * from op_config where uuid=#{uuid}")
	DeployInfo getDetail(String uuid);

	@Insert("insert into op_config (uuid,name,ip,password,tname,sdir,"
			+ "tdir,bdir,wdir,javaHomePath,zhicallConfig,wname,lname,logpath) "
			+ "values"
			+ " (#{uuid},#{name},#{ip},#{password},"
			+ "#{tname},#{sdir},#{tdir},#{bdir},#{wdir},#{javaHomePath},#{zhicallConfig},#{wname},#{lname},#{logpath})")
	void insert(DeployInfo javaDeployInfo);

}