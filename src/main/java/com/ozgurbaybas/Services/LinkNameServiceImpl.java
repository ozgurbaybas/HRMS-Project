package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessDataResult;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.LinkName;
import com.ozgurbaybas.Repository.LinkNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkNameServiceImpl implements LinkNameService {

    private LinkNameRepository linkNameRepository;

    @Autowired
    public LinkNameServiceImpl(LinkNameRepository linkNameRepository) {
        this.linkNameRepository = linkNameRepository;
    }

    @Override
    public Result add(LinkName linkName) {
        linkNameRepository.save(linkName);
        return new SuccessResult("Added connection name.");
    }

    @Override
    public Result update(LinkName linkName) {
        linkNameRepository.save(linkName);
        return new SuccessResult("The link name has been updated.");
    }

    @Override
    public Result delete(int id) {
        linkNameRepository.deleteById(id);
        return new SuccessResult("The connection name has been deleted.");
    }

    @Override
    public DataResult<List<LinkName>> getAll() {
        return new SuccessDataResult<List<LinkName>>(linkNameRepository.findAll());
    }

    @Override
    public DataResult<LinkName> getById(int id) {
        return new SuccessDataResult<LinkName>(linkNameRepository.getById(id));
    }

}