package com.example.demo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CityMapper {

    @Select("SELECT id, name, state, country FROM city WHERE state = #{state}")
    City findByState(String state);

    @Insert("INSERT into city(id, name, state, country) VALUES(#{id}, #{name}, #{state}, #{country})")
    void insert(City city);

    void batchInsert(List<City> cityList);

}