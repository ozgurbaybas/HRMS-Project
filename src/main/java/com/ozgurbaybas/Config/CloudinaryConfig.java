package com.ozgurbaybas.Config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ozgurbaybas.Services.Adapters.Cloudinary.CloudStorageService;
import com.ozgurbaybas.Services.Adapters.Cloudinary.CloudinaryServiceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinaryAccount() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "",
                "api_key", "",
                "api_secret", ""
        ));
    }

    @Bean
    public CloudStorageService cloudStorageService() {
        return new CloudinaryServiceAdapter(cloudinaryAccount());
    }

}