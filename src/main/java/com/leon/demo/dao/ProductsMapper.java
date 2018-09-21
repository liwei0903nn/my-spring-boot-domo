package com.leon.demo.dao;

import com.leon.demo.entity.Products;
import com.leon.demo.entity.ProductsExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface ProductsMapper {
    @SelectProvider(type=ProductsSqlProvider.class, method="countByExample")
    long countByExample(ProductsExample example);

    @DeleteProvider(type=ProductsSqlProvider.class, method="deleteByExample")
    int deleteByExample(ProductsExample example);

    @Delete({
        "delete from products",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into products (id, created_at, ",
        "updated_at, deleted_at, ",
        "code, price)",
        "values (#{id,jdbcType=INTEGER}, #{createdAt,jdbcType=TIMESTAMP}, ",
        "#{updatedAt,jdbcType=TIMESTAMP}, #{deletedAt,jdbcType=TIMESTAMP}, ",
        "#{code,jdbcType=VARCHAR}, #{price,jdbcType=INTEGER})"
    })
    int insert(Products record);

    @InsertProvider(type=ProductsSqlProvider.class, method="insertSelective")
    int insertSelective(Products record);

    @SelectProvider(type=ProductsSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="deleted_at", property="deletedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.INTEGER)
    })
    List<Products> selectByExample(ProductsExample example);

    @Select({
        "select",
        "id, created_at, updated_at, deleted_at, code, price",
        "from products",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="deleted_at", property="deletedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.INTEGER)
    })
    Products selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ProductsSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Products record, @Param("example") ProductsExample example);

    @UpdateProvider(type=ProductsSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Products record, @Param("example") ProductsExample example);

    @UpdateProvider(type=ProductsSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Products record);

    @Update({
        "update products",
        "set created_at = #{createdAt,jdbcType=TIMESTAMP},",
          "updated_at = #{updatedAt,jdbcType=TIMESTAMP},",
          "deleted_at = #{deletedAt,jdbcType=TIMESTAMP},",
          "code = #{code,jdbcType=VARCHAR},",
          "price = #{price,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Products record);
}