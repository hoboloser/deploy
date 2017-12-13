package com.zhicall.op.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zhicall.op.entity.DeployInfo;

public interface DeployMapper {

	@Select("select * from op_config")
	List<DeployInfo> getList();

	@Select("select * from op_config where uuid=#{uuid}")
	DeployInfo getDetail(String uuid);

	@Insert("insert into op_config (uuid,name,ip,password,tname,sdir,"
			+ "tdir,bdir,wdir,javaHomePath,zhicallConfig,wname,lname,logpath,type) "
			+ "values"
			+ " (#{uuid},#{name},#{ip},#{password},"
			+ "#{tname},#{sdir},#{tdir},#{bdir},#{wdir},#{javaHomePath},#{zhicallConfig},#{wname},#{lname},#{logpath},#{type})")
	void insert(DeployInfo deployInfo);
	
	@Delete("delete op_config where uuid=#{uuid} ")
	void delete(String uuid);

	@Update("update op_config set name=#{name},ip=#{ip},password=#{password},tname=#{tname},sdir=#{sdir},"
			+ "tdir=#{tdir},bdir=#{bdir},wdir=#{wdir},javaHomePath=#{javaHomePath},zhicallConfig=#{zhicallConfig},wname=#{wname}"
			+ ",lname=#{lname},logpath#{logpath},type=#{type}  where uuid=#{uuid} ")
	void update(DeployInfo deployInfo);
}