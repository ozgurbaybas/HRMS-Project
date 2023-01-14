package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.Image;
import com.ozgurbaybas.Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/images")
@CrossOrigin
public class ImagesController {

    private ImageService imageService;

    @Autowired
    public ImagesController(ImageService imageService) {
        this.imageService = imageService;
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody Image image) {
        return imageService.delete(image);
    }

    @GetMapping("/getAll")
    public DataResult<List<com.ozgurbaybas.Models.Image>> getAll() {
        return imageService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<com.ozgurbaybas.Models.Image> getById(@RequestParam int id) {
        return imageService.getById(id);
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam int userId, @RequestParam MultipartFile file) {
        return imageService.upload(userId, file);
    }

    @GetMapping("/getByUserId")
    public DataResult<Image> getByUserId(@RequestParam int userId) {
        return imageService.getById(userId);
    }

}