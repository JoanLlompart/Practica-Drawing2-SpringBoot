package com.esliceu.PracticaDrawing2SpringBoot.Services;
import com.esliceu.PracticaDrawing2SpringBoot.DTO.CanvasVersionDTO;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Canvas;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Permission;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.User;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Version;
import com.esliceu.PracticaDrawing2SpringBoot.Exceptions.NotYourCanvasException;
import com.esliceu.PracticaDrawing2SpringBoot.Repository.CanvasRepo;
import com.esliceu.PracticaDrawing2SpringBoot.Repository.UserRepo;
import com.esliceu.PracticaDrawing2SpringBoot.Repository.VersionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CanvasServices {
    @Autowired
    CanvasRepo canvasRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    VersionRepo versionRepo;
    @Autowired
    CanvasVersionDTO canvasVersionDTO;
    public void newCanvas(String strokesJson, String figureJson, String email, String nameCanvas, String esPub) {

        try {
            Canvas canvas = new Canvas();
            canvas.setNameCanvas(nameCanvas);
            User u = userRepo.findByEmail(email);
            canvas.setUser_email(u.getEmail());
            if (esPub == null) {
                canvas.setPublicDraw(false);
            }else if (esPub.equals("on")) {
                canvas.setPublicDraw(true);
            }
            System.out.println(canvas.isPublicDraw());
            //No esta a la paperera.
            canvas.setTrash(false);
            System.out.println("Nom de el canvas" +canvas.getNameCanvas());
            //parsearObjectes(strokesJson,figureJson);
            canvasRepo.saveCanvas(canvas,strokesJson,figureJson);
        } catch (Exception e) {
            System.err.println("Error en el al fer el nou canvas a CanvasService" + e.getMessage() + e.getLocalizedMessage() + e.getCause());
        }
    }
    public List<Canvas> showMyCanvas(String email) {
        User u = userRepo.findByEmail(email);
        //torna la llista amb els canvas que pertanyen a el usuari
        return canvasRepo.findByUser(u);
    }
    //genera un nom si el usuari no ha asignat un nom a el canvas.
    public String generarNom(String nameCanvas) {
        //a la hora de guardar hem de anar alerta que no es repetesqui el numero si
        // es repeteix generar un altre nom nou,per el usuari.
        int numAleatori = (int) (Math.random() * 99999);
        return nameCanvas = "Image" + numAleatori;
    }
    public boolean deleteCanvasById(int idDelete,String email) {
        //borrara els dibuixos i tornara true si ha anat be.
        if (canvasRepo.removeCanvas(idDelete,email)) {
            System.out.println("Eliminat canvas amb id " + idDelete);
            return true;
        }
        System.out.println("No se ha pogut eliminar canvas amb id " + idDelete);
        return false;
    }
    public boolean sendToTrash(int idCanvasTrash,String email) {
        //Amb el id i el correu de de la sessio si es el canvas de el propietari es mandara a la paperera marcannt en la base de dades
        // trash com a true.
        if (canvasRepo.goToTrash(idCanvasTrash,email)) {
            System.out.println("Mandant a paperera el  canvas amb id " + idCanvasTrash);
            return true;
        }
        System.out.println("No se ha pogut mandar a la papelera el canvas amb id " + idCanvasTrash);
        return false;
    }

    public boolean sendOutToTrash(int idCanvasTrash,String email) {
        //Amb el id i el correu de de la sessio si es el canvas de el propietari es mandara a la paperera marcannt en la base de dades
        // trash com a true.
        if (canvasRepo.sendOutOfTrash(idCanvasTrash,email)) {
            System.out.println("Mandant a defora de paperera el  canvas amb id " + idCanvasTrash);
            return true;
        }
        System.out.println("No se ha pogut mandar a la defora de papelera el canvas amb id " + idCanvasTrash);
        return false;
    }
    public CanvasVersionDTO getCanvasToModify(int id, String emailSessionUser) throws NotYourCanvasException {
        //hem de comprobar que aquest id pertany a el mateix usuari que el ha creat i que esta en la sessio.
        List<Object> canvasVersionList = canvasRepo.getCanvasById(id);
        Canvas c= (Canvas) canvasVersionList.get(0);
        Version v =(Version) canvasVersionList.get(1);


        canvasVersionDTO.setNameCanvas(c.getNameCanvas());
        canvasVersionDTO.setFigures(v.getFigures());
        canvasVersionDTO.setStrokes(v.getStrokes());
        canvasVersionDTO.setNumberObject(v.getNumberObject());
        canvasVersionDTO.setVersion(v.getIdVersion());
        canvasVersionDTO.setDataCreacio( c.getDataCreacio());
        canvasVersionDTO.setDateLastModified(v.getDateLastModified());
        canvasVersionDTO.setUser_email(c.getUser_email());
        canvasVersionDTO.setIdObjectes(id);

        //email de el pintor de el dibuix
        String emailPainter = c.getUser_email();

        /*
        //PROBAM A NO COMPROBAR AQUI.
        if (emailPainter.equals(emailSessionUser)) {
            //si el email de el pintor coincideix amb el de el user de la sessio tornara el canvas.
            System.out.println("Print a canvasServices modify" + c.toString());
            return canvasVersionDTO;
        } else {
            throw new NotYourCanvasException("No eres el propietario de este Canvas!");
        }
         */
        return canvasVersionDTO;
    }
    public List<CanvasVersionDTO> showAllCanvas(String email) {
        List<Object[]> listOb = canvasRepo.showAllCanvas(email);
        List<Object[]> lisPermisCanv =canvasRepo.showCanvasUserHavePermission(email);
        List<CanvasVersionDTO> canvasVersionDTOList = new ArrayList<>();

        for (Object[] ob: lisPermisCanv) {
            Canvas canvas = (Canvas) ob[0];
            Version version = (Version) ob[1];
            Permission permission = (Permission) ob[2];
            CanvasVersionDTO dto = new CanvasVersionDTO();
            dto.setIdObjectes(canvas.getIdObjectes());
            dto.setNameCanvas(canvas.getNameCanvas());
            dto.setUser_email(canvas.getUser_email());
            dto.setDataCreacio(canvas.getDataCreacio());
            dto.setNumberObject(version.getNumberObject());
            dto.setFigures(version.getFigures());
            dto.setStrokes(version.getStrokes());
            dto.setTrash(canvas.isTrash());
            dto.setDateLastModified(version.getDateLastModified());
            dto.setVersion(version.getIdVersion());
            dto.setPermissionType(permission.getPermissionType());
            canvasVersionDTOList.add(dto);
        }


     /*   for (Object ob:lisPermisCanv) {
            CanvasVersionDTO dto = new CanvasVersionDTO();
            if (ob instanceof Canvas canvas) {
                dto.setIdObjectes(canvas.getIdObjectes());
                dto.setNameCanvas(canvas.getNameCanvas());
                dto.setUser_email(canvas.getUser_email());
                dto.setDataCreacio(canvas.getDataCreacio());
                dto.setTrash(canvas.isTrash());
            } else if (ob instanceof Version version) {
                dto.setNumberObject(version.getNumberObject());
                dto.setDateLastModified(version.getDateLastModified());
                dto.setVersion(version.getIdVersion());
            } else if (ob instanceof Permission permission) {
                dto.setPermissionType(permission.getPermissionType());

            }
            canvasVersionDTOList.add(dto);
        }

      */


      /*  System.out.println("-----------");
        for (CanvasVersionDTO dto: canvasVersionDTOList) {
            System.out.println(dto.toString());
        }
        System.out.println("-----------");
       */

        //listOb.addAll(lisPermisCanv);
        //List<CanvasVersionDTO> canvasVersionDTOList = new ArrayList<>();
        for (Object[] objects : listOb) {
            Canvas canvas = (Canvas) objects[0];
            Version version = (Version) objects[1];
            CanvasVersionDTO canvasVersionDTO = new CanvasVersionDTO();
            canvasVersionDTO.setIdObjectes(canvas.getIdObjectes());
            canvasVersionDTO.setNameCanvas(canvas.getNameCanvas());
            canvasVersionDTO.setUser_email(canvas.getUser_email());
            canvasVersionDTO.setDataCreacio(canvas.getDataCreacio());
            canvasVersionDTO.setNumberObject(version.getNumberObject());
            canvasVersionDTO.setFigures(version.getFigures());
            canvasVersionDTO.setStrokes(version.getStrokes());
            canvasVersionDTO.setTrash(canvas.isTrash());
            canvasVersionDTO.setDateLastModified(version.getDateLastModified());
            canvasVersionDTO.setVersion(version.getIdVersion());
            canvasVersionDTOList.add(canvasVersionDTO);
        }
        return canvasVersionDTOList;
    }
    public Canvas getCanvas(int id,String email) {
        List<Object> list =canvasRepo.getCanvasById(id);
        Canvas c = (Canvas) list.get(0);
        System.out.println("Probant que torna la llista de objectes " +c);
        //return canvasRepo.getCanvasById(id);
        return c;
    }
    //per la versio
    public CanvasVersionDTO getVersion(int id,String email) {
        List<Object> list =canvasRepo.getCanvasById(id);
        Canvas c = (Canvas) list.get(0);
        Version v = (Version) list.get(1);
        canvasVersionDTO.setNameCanvas(c.getNameCanvas());
        canvasVersionDTO.setFigures(v.getFigures());
        canvasVersionDTO.setStrokes(v.getStrokes());
        canvasVersionDTO.setNumberObject(v.getNumberObject());
        canvasVersionDTO.setVersion(v.getIdVersion());
        canvasVersionDTO.setDataCreacio(c.getDataCreacio());
        canvasVersionDTO.setDateLastModified(v.getDateLastModified());
        canvasVersionDTO.setUser_email(c.getUser_email());
        canvasVersionDTO.setIdObjectes(c.getIdObjectes());
        System.out.println("Probant que torna la llista de objectes " +c);
        return canvasVersionDTO;
    }
    public List<CanvasVersionDTO> showMyTrash(String emailSession) {
        List<Object[]> listOb = canvasRepo.showMyTrash(emailSession);
        List<CanvasVersionDTO> canvasVersionDTOList = new ArrayList<>();
        for (Object[] objects : listOb) {
            Canvas canvas = (Canvas) objects[0];
            Version version = (Version) objects[1];
            CanvasVersionDTO canvasVersionDTO = new CanvasVersionDTO();
            canvasVersionDTO.setIdObjectes(canvas.getIdObjectes());
            canvasVersionDTO.setNameCanvas(canvas.getNameCanvas());
            canvasVersionDTO.setUser_email(canvas.getUser_email());
            canvasVersionDTO.setDataCreacio(canvas.getDataCreacio());
            canvasVersionDTO.setNumberObject(version.getNumberObject());
            canvasVersionDTO.setFigures(version.getFigures());
            canvasVersionDTO.setStrokes(version.getStrokes());
            canvasVersionDTO.setTrash(canvas.isTrash());
            canvasVersionDTO.setDateLastModified(version.getDateLastModified());
            canvasVersionDTO.setVersion(version.getIdVersion());
            canvasVersionDTOList.add(canvasVersionDTO);
        }
        return canvasVersionDTOList;
    }

    public boolean isCanvasPublic(int idObjectes) {
        return canvasRepo.visibilityCanvas(idObjectes);
    }


    public boolean createCanvasCopy(CanvasVersionDTO canVerDTOCopy) {
        //String strokesJson, String figureJson, String email, String nameCanvas, String esPub
        String strokesJson = canVerDTOCopy.getStrokes();
        String figureJson = canVerDTOCopy.getFigures();
        String nameCopy = "Copy_"+ canVerDTOCopy.getNameCanvas();

        Canvas copyC = new Canvas();
        copyC.setUser_email(canVerDTOCopy.getUser_email());
        copyC.setNameCanvas(nameCopy);
        copyC.setPublicDraw(canVerDTOCopy.isPublic());
        copyC.setTrash(false);
        try {
            canvasRepo.saveCanvas(copyC,strokesJson,figureJson);
        } catch (Exception e) {
            throw new RuntimeException("Error al realitzar la copia de el canvas" +e.getCause()  +  e.getLocalizedMessage());
        }
        return true;
    }
}
