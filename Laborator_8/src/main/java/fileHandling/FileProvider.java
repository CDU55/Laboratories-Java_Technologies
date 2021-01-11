package fileHandling;

import annotations.AllFiles;
import interfaces.IFileService;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Provider;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

public class FileProvider implements Provider<ArrayList<UserFile>>, Serializable {

    @Inject
    private IFileService service;

    @Override
    @Produces
    @AllFiles
    public ArrayList<UserFile> get() {
            return service.getAll();
    }
}
