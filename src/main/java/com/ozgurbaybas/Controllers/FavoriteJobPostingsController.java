package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.FavoriteJobPosting;
import com.ozgurbaybas.Services.FavoriteJobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoriteJobPostings")
@CrossOrigin
public class FavoriteJobPostingsController {

    private FavoriteJobPostingService favoriteJobPostingService;

    @Autowired
    public FavoriteJobPostingsController(FavoriteJobPostingService favoriteJobPostingService) {
        this.favoriteJobPostingService = favoriteJobPostingService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody FavoriteJobPosting favoriteJobPosting) {
        return favoriteJobPostingService.add(favoriteJobPosting);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody FavoriteJobPosting favoriteJobPosting) {
        return favoriteJobPostingService.delete(favoriteJobPosting);
    }

    @GetMapping("/getAll")
    public DataResult<List<FavoriteJobPosting>> getAll() {
        return favoriteJobPostingService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<FavoriteJobPosting> getById(@RequestParam int id) {
        return favoriteJobPostingService.getById(id);
    }

    @GetMapping("/getAllByCandidateId")
    public DataResult<List<FavoriteJobPosting>> getAllByCandidateId(@RequestParam int candidateId) {
        return favoriteJobPostingService.getAllByCandidateId(candidateId);
    }

    @GetMapping("/getAllByCandidateIdSortedByDateOfAddToFavorites")
    public DataResult<List<FavoriteJobPosting>> getAllByCandidateIdSortedByDateOfAddToFavorites(@RequestParam int candidateId) {
        return favoriteJobPostingService.getAllByCandidateIdSortedByDateOfAddToFavorites(candidateId);
    }

}