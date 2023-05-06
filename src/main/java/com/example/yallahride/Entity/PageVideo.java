package com.example.yallahride.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Page_Video")
public class PageVideo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_video_pk")
    private Long id;
    @Column(name = "video_path")
    @NonNull
    private String videoPath;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "page_fk", referencedColumnName = "page_pk")
    @ToString.Exclude
    private Page page;

    @PreRemove
    private void deleteVideoFromPage() {
        if (page != null)
            page.deleteVideo(this);
    }
}
