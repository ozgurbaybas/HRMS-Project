package com.ozgurbaybas.Repository;


import com.ozgurbaybas.Models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Image getByUser_Id(int userId);
    void delete(com.ozgurbaybas.Models.Image image);
}