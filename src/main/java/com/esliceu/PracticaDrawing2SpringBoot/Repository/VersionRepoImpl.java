package com.esliceu.PracticaDrawing2SpringBoot.Repository;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VersionRepoImpl implements VersionRepo{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean newVersionOfCanvas(String nameCanvas, Version version) {
        return false;
    }
}
