package producers;

import annotations.FileName;
import interfaces.IFileService;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Provider;
import java.io.Serializable;

public class RegistrationCodeProvider implements Provider<String>, Serializable {

    @Inject
    private IFileService service;


    @Override
    @Produces
    @FileName
    public String get() {
            return service.getNexFileName();
    }
}
