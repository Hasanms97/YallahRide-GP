package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Page;
import com.example.yallahride.Entity.PageVideo;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.PageVideoRepository;
import com.example.yallahride.Service.Interface.PageVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PageVideoServiceImpl implements PageVideoService {
    @Autowired

    PageVideoRepository pageVideoRepository;



    @Override

    public PageVideo savePageVideo(PageVideo pageVideo) {
        return pageVideoRepository.save(pageVideo);
    }

    @Override
    public PageVideo findPageVideoById(Long id) {
        return unwrapPageVideo(pageVideoRepository.findById(id),id);
    }

    @Override
    public List<PageVideo> findAllPageVideos() {
        return pageVideoRepository.findAll();
    }

    @Override
    public PageVideo updatePageVideo(PageVideo pageVideo) {
        return pageVideoRepository.save(pageVideo);
    }

    @Override
    public void deleteAllPageVideos() {
        pageVideoRepository.deleteAll();
    }

    @Override
    public void deletePageVideoById(Long id) {
        pageVideoRepository.deleteById(id);
    }

    @Override
    public long getNumberOfPageVideo() {
        return pageVideoRepository.count();
    }

    static PageVideo unwrapPageVideo(Optional<PageVideo> pageVideo, Long id) {
        if (pageVideo.isPresent()) return pageVideo.get();
        else throw new EntityNotFoundException(id, PageVideo.class);
    }
}
