package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessDataResult;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.Link;
import com.ozgurbaybas.Repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {

    private LinkRepository linkRepository;

    @Autowired
    public LinkServiceImpl(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public Result add(Link link) {

        linkRepository.save(link);
        return new SuccessResult("Link eklendi.");
    }

    @Override
    public Result update(Link link) {

        linkRepository.save(link);
        return new SuccessResult("Link güncellendi.");
    }

    @Override
    public Result delete(Link link) {

        linkRepository.delete(link);
        return new SuccessResult("Link silindi.");
    }

    @Override
    public DataResult<List<Link>> getAll() {
        return new SuccessDataResult<List<Link>>(linkRepository.findAll());
    }

    @Override
    public DataResult<Link> getById(int id) {
        return new SuccessDataResult<Link>(linkRepository.getById(id));
    }

}