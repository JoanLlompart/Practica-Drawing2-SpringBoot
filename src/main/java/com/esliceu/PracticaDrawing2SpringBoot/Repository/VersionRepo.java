package com.esliceu.PracticaDrawing2SpringBoot.Repository;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.Version;

public interface VersionRepo {
    boolean newVersionOfCanvas(String nameCanvas, Version version);
}
