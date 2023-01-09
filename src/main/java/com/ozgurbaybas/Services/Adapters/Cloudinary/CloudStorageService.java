package com.ozgurbaybas.Services.Adapters.Cloudinary;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import org.springframework.web.multipart.MultipartFile;

public interface CloudStorageService {

    DataResult<?> upload(MultipartFile multipartFile);
    DataResult<?> delete(String publicIdOfImage);

}