package com.estepper.estepper.service;

import org.python.core.Py;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.FactoryBean;

public class PythonServiceFactory implements FactoryBean<PythonService> {

    @Override
    public PythonService getObject() throws Exception {
        PySystemState systemState = Py.getSystemState();
        systemState.path.append(new PyString("C:\\jython\\Lib"));
        systemState.path.append(new PyString("C:\\jython\\Lib\\site-packages"));

        try (
                PythonInterpreter interpreter = new PythonInterpreter()) {
            interpreter.execfile("src\\main\\java\\com\\estepper\\estepper\\service\\PythonServiceImpl.py");
            PyObject buildingObject = interpreter.get("PythonServiceImpl").__call__();

            return (PythonService) buildingObject.__tojava__(PythonService.class);
        }
    }

    @Override
    public Class<?> getObjectType() {
        return PythonService.class;
    }

}
