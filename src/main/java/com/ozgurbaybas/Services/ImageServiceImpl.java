package com.ozgurbaybas.Services;


import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessDataResult;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.Image;
import com.ozgurbaybas.Repository.ImageRepository;
import com.ozgurbaybas.Services.Adapters.Cloudinary.CloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;
    private CloudStorageService cloudStorageService;
    private UserService userService;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, CloudStorageService cloudStorageService, UserService userService) {
        this.imageRepository = imageRepository;
        this.cloudStorageService = cloudStorageService;
        this.userService = userService;
    }

    @Override
    public Result add(Image image) {

        imageRepository.save(image);
        return new SuccessResult("Image added.");
    }

    @Override
    public Result update(Image image) {

        imageRepository.save(image);
        return new SuccessResult("Image update.");
    }

    @Override
    public Result delete(int id) {

        Image image = getById(id).getData();

        String[] splitImageUrlArray = image.getUrl().split("/");
        int indexOfExtension = splitImageUrlArray[splitImageUrlArray.length - 1].indexOf(".");
        String publicIdOfImage = splitImageUrlArray[splitImageUrlArray.length - 1].substring(0, indexOfExtension);

        cloudStorageService.delete(publicIdOfImage);

        imageRepository.deleteById(id);
        return new SuccessResult("Image delete.");
    }

    @Override
    public DataResult<List<Image>> getAll() {
        return new SuccessDataResult<List<Image>>(imageRepository.findAll());
    }

    @Override
    public DataResult<Image> getById(int id) {
        return new SuccessDataResult<Image>(imageRepository.getById(id));
    }


    @Override
    public Result upload(int userId, MultipartFile file) {
        Map<?, ?> uploadImage = (Map<?, ?>) cloudStorageService.upload(file).getData();
        Image image = new Image();
        image.setUser(userService.getById(userId).getData());
        image.setUrl(uploadImage.get("url").toString());
        return add(image);
    }

    @Override
    public DataResult<Image> getByUserId(int userId) {
        return new SuccessDataResult<Image>(imageRepository.getByUser_Id(userId));
    }

}