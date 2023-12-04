package com.esliceu.PracticaDrawing2SpringBoot.Repository;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.Version;

import java.util.List;

public interface VersionRepo {
    boolean newVersionOfCanvas(String nameCanvas, Version version);
    List<Version> getVersionsByIdDraw(int idDraw);
}
