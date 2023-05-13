package com.example.yallahride.ServiceTest;

import com.example.yallahride.Entity.Page;
import com.example.yallahride.Entity.PageContent;
import com.example.yallahride.Entity.PageImage;
import com.example.yallahride.Entity.PageVideo;
import com.example.yallahride.Service.Interface.PageContentService;
import com.example.yallahride.Service.Interface.PageImageService;
import com.example.yallahride.Service.Interface.PageService;
import com.example.yallahride.Service.Interface.PageVideoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PageServiceTest {

    @Autowired
    PageService pageService;

    @Autowired
    PageContentService pageContentService;

    @Autowired
    PageImageService pageImageService;

    @Autowired
    PageVideoService pageVideoService;
    Page page;
    PageVideo pageVideo;
    PageImage pageImage;
    PageContent pageContent;
    MockMultipartFile imageObject;
    MockMultipartFile videoObject;

    @BeforeAll
    void setPage() {
        page = pageService.savePage(new Page());
    }

    @Test
    @Order(1)
    void addPage() {
        Assertions.assertThat(pageService.findPageById(page.getId())).isNotNull();
    }


    @Test
    @Order(2)
    void addPageContent() {
        pageContent = new PageContent();
        pageContent.setContent("Hello !");
        pageContent.setPage(page);
        pageContent = pageContentService.savePageContent(pageContent);
        pageService.addContent(page.getId(), pageContent);
        Assertions.assertThat(page.getPageContentSet().size() > 0);
    }

    @Test
    @Order(3)
    void addPageImage() {
        MultipartFile multipartFile = new MockMultipartFile("image1.png", "image1.png!".getBytes());
        pageImage = pageImageService.savePageImage(new PageImage(multipartFile));
        page = pageService.addImage(page.getId(), pageImage);
        Assertions.assertThat(page.getPageImageSet().size() > 0);
    }

    @Test
    @Order(4)
    void addPageVideos() {
        MultipartFile multipartFile = new MockMultipartFile("video.mp3", "video.mp3!".getBytes());
        pageVideo = pageVideoService.savePageVideo(new PageVideo(multipartFile));
        page.addVideo(pageVideo);
        page = pageService.savePage(page);
        Assertions.assertThat(page.getPageVideoSet().size() > 0);
    }

    @Test
    @Order(5)
    void getPageContents() {
        Assertions.assertThat(pageService.getPageContents(page.getId()).size() > 0);
    }

    @Test
    @Order(6)
    void getPageImages() {
        Assertions.assertThat(pageService.getPageImages(page.getId()).size() > 0);
    }

    @Test
    @Order(7)
    void getPageVideos() {
        Assertions.assertThat(pageService.getPageVideos(page.getId()).size() > 0);
    }

    @Test
    @Order(8)
    void should_not_remove_parent_when_child_removed() {
        pageVideoService.deletePageVideoById(pageVideo.getId());
        Assertions.assertThat(pageService.findPageById(page.getId()).getId() > 0);
    }

    @Test
    @Order(9)
    void should_remove_children_when_parent_removed() {
            pageService.deletePageById(page.getId());
    }

}
