package com.leon.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.leon.demo.entity.SysUser;
import com.leon.demo.entity.SysUserExample;

public interface SysUserMapper {
	@SelectProvider(type = SysUserSqlProvider.class, method = "countByExample")
	long countByExample(SysUserExample example);

	@DeleteProvider(type = SysUserSqlProvider.class, method = "deleteByExample")
	int deleteByExample(SysUserExample example);

	@Delete({ "delete from sys_user", "where id = #{id,jdbcType=INTEGER}" })
	int deleteByPrimaryKey(Integer id);

	@Insert({ "insert into sys_user (id, username, ", "`password`)",
			"values (#{id,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ", "#{password,jdbcType=VARCHAR})" })
	@Options(useGeneratedKeys = true, keyProperty = "id") // 增加这个注解 可以在插入之后 获取到插入的数据的主键
	int insert(SysUser record);

	@InsertProvider(type = SysUserSqlProvider.class, method = "insertSelective")
	@Options(useGeneratedKeys = true, keyProperty = "id") // 增加这个注解 可以在插入之后 获取到插入的数据的主键
	int insertSelective(SysUser record);

	@SelectProvider(type = SysUserSqlProvider.class, method = "selectByExample")
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
			@Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR) })
	List<SysUser> selectByExample(SysUserExample example);

	@Select({ "select", "id, username, `password`", "from sys_user", "where id = #{id,jdbcType=INTEGER}" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
			@Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR) })
	SysUser selectByPrimaryKey(Integer id);

	@UpdateProvider(type = SysUserSqlProvider.class, method = "updateByExampleSelective")
	int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

	@UpdateProvider(type = SysUserSqlProvider.class, method = "updateByExample")
	int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

	@UpdateProvider(type = SysUserSqlProvider.class, method = "updateByPrimaryKeySelective")
	int updateByPrimaryKeySelective(SysUser record);

	@Update({ "update sys_user", "set username = #{username,jdbcType=VARCHAR},",
			"`password` = #{password,jdbcType=VARCHAR}", "where id = #{id,jdbcType=INTEGER}" })
	int updateByPrimaryKey(SysUser record);
}