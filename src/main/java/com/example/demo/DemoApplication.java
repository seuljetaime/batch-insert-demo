package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    private final CityMapper cityMapper;

    public DemoApplication(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner sampleCommandLineRunner() {

        long start = System.currentTimeMillis();

        batchInsert();

        long end = System.currentTimeMillis();
        return (args) -> System.out.println("插入1w耗时：" + (end - start));
    }

    // 插入1w耗时：354748
    void normalInsert() {
        // 执行循环插入
        City city = new City();
        for (int i = 1; i < 10000; i++) {
            city.setId(Long.valueOf(i));
            city.setName(i + "");
            city.setState(i + "");
            city.setCountry(i + "");
            cityMapper.insert(city);
        }
    }

    void batchInsert() {
        List<City> cityList = new ArrayList<>();

        for (int i = 1; i < 10000; i++) {
            City city = new City();
            city.setId(Long.valueOf(i));
            city.setName(i + "");
            city.setState(i + "");
            city.setCountry(i + "");
            cityList.add(city);
        }
        cityMapper.batchInsert(cityList);
    }
}
