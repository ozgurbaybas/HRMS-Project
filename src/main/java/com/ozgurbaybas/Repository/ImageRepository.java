package com.ozgurbaybas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    Image getByUser_Id(int userId);

}