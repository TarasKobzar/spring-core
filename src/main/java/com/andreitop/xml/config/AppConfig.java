package com.andreitop.xml.config;

import com.andreitop.xml.mount.Mount;
import com.andreitop.xml.mount.Tiger;
import com.andreitop.xml.mount.Wolf;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Configuration
@ComponentScan(basePackages = "com.andreitop.xml")
public class AppConfig {

    @Bean
    public Tiger shadowTiger(){
        return new Tiger();
    }

    @Bean
    public Wolf frostWolf(){
        return new Wolf();
    }

    @Bean
    public List<Mount> listOfMounts(){
        ArrayList<Mount> listOfMounts = new ArrayList<>();
        listOfMounts.add(frostWolf());
        listOfMounts.add(null);
        listOfMounts.add(shadowTiger());
        return listOfMounts;
    }

    @Bean
    public Map<String, Mount> trollMountMap(){
        HashMap<String, Mount> trollMountMap = new HashMap<>();
        trollMountMap.put("m1", frostWolf());
        trollMountMap.put("m2", shadowTiger());
        return trollMountMap;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        PropertySourcesPlaceholderConfigurer placeHolderConfigurer = new PropertySourcesPlaceholderConfigurer();
        placeHolderConfigurer.setLocation(new ClassPathResource("config/heroes.properties"));
        return placeHolderConfigurer;
    }

    @Bean
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("dd-mm-yyyy");
    }

    @Value("${character.created}")
    private String characterCreated;

    @Bean
    public Date date() throws ParseException {
        return simpleDateFormat().parse(characterCreated);
    }
}
