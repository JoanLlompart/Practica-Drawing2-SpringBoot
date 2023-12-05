package com.esliceu.PracticaDrawing2SpringBoot.RowMapper;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.Version;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
public class VersionRowMapper implements RowMapper<Version> {
    @Override
    public Version mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Version version = new Version();
        version.setIdVersion(resultSet.getInt("idVersion"));
        version.setIdDraw(resultSet.getInt("idDraw"));
        version.setNumberObject(resultSet.getInt("numberObject"));
        version.setFigures(resultSet.getString("figuresJSON"));
        version.setStrokes(resultSet.getString("strokesJSON"));
        version.setDateLastModified(resultSet.getDate("dateLastModified").toInstant());
        version.setUser_email(resultSet.getString("user_email"));
        return version;
    }

    @Override
    public int[] getRowsForPaths(TreePath[] path) {
        return new int[0];
    }
}

 */
