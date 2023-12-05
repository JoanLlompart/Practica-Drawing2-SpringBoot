package com.esliceu.PracticaDrawing2SpringBoot.Repository;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Version;
import java.util.List;

public interface VersionRepo {
    boolean newVersionOfCanvas(String nameCanvas, Version version,boolean isPublic);
    List<Version> getVersionsByIdDraw(int idDraw);
    boolean verifyUserCanWrite(Version version);
    boolean verifyUserCanRead(Version version);
}
