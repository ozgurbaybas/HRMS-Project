package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.Image;
import org.springframework.web.multipart.MultipartFile;


public interface ImageService extends BaseEntityService<Image> {

    Result upload(int userId, MultipartFile file);
    DataResult<Image> getByUserId(int userId);
}